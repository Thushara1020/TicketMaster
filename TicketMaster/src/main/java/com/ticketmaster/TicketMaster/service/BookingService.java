package com.ticketmaster.TicketMaster.service;

import com.ticketmaster.TicketMaster.annotation.AuditFailure;
import com.ticketmaster.TicketMaster.entity.Booking;
import com.ticketmaster.TicketMaster.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @AuditFailure
    public Booking book(Long userId, Long seatId, double amount) {

        throw new RuntimeException("Simulated booking failure");
    }
}
