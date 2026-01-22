package com.ticketmaster.TicketMaster.aspect;



import com.ticketmaster.TicketMaster.dto.BookingRequest;
import com.ticketmaster.TicketMaster.entity.AuditLog;
import com.ticketmaster.TicketMaster.repository.AuditLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {

    private static final Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    private final AuditLogRepository auditLogRepository;

    public AuditAspect(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @AfterThrowing(
            pointcut = "@annotation(com.icet.ticketmaster.annotation.AuditFailure)",
            throwing = "exception"
    )
    public void auditFailure(JoinPoint joinPoint, Throwable exception) {
        String action = joinPoint.getSignature().getName();
        Long userId = null;

        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof BookingRequest) {
            BookingRequest request = (BookingRequest) args[0];
            userId = request.getUserId();
        }

        String details = String.format(
                "Method: %s, Exception: %s, Message: %s",
                action,
                exception.getClass().getSimpleName(),
                exception.getMessage()
        );

        AuditLog auditLog = new AuditLog();
        auditLog.setAction(action);
        auditLog.setUserId(userId);
        auditLog.setDetails(details);
        auditLog.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(auditLog);

        logger.warn("Audit Log Created - Action: {}, UserId: {}, Details: {}",
                action, userId, details);
    }
}
