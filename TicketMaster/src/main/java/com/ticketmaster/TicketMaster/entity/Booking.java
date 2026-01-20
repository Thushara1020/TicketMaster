package com.ticketmaster.TicketMaster.entity;

import com.ticketmaster.TicketMaster.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long seatId;
    private double amountPaid;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}