package com.ticketmaster.TicketMaster.entity;

import com.ticketmaster.TicketMaster.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private Long heldByUserId;
    private LocalDateTime holdExpiry;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Version
    private Integer version;
}