package com.ticketmaster.TicketMaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; // මම මේ පේළිය වෙනස් කළා
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private Long userId;
    private String details;
    private LocalDateTime timestamp;

}