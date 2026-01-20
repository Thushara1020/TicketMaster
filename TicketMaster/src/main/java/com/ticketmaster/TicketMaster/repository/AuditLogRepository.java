package com.ticketmaster.TicketMaster.repository;

import com.ticketmaster.TicketMaster.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {}