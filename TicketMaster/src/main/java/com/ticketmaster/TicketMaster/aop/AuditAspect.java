package com.ticketmaster.TicketMaster.aop;

import com.ticketmaster.TicketMaster.entity.AuditLog;
import com.ticketmaster.TicketMaster.repository.AuditLogRepository;
import jakarta.persistence.Entity;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {

    @Autowired
    private AuditLogRepository auditRepo;

    @AfterThrowing(pointcut = "@annotation(AuditFailure)", throwing = "ex")
    public void log(Exception ex) {
        AuditLog log = new AuditLog();
        log.setAction("BOOKING_FAILED");
        log.setDetails(ex.getMessage());
        log.setTimestamp(LocalDateTime.now());
        auditRepo.save(log);
    }
}
