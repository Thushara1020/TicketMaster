package com.ticketmaster.TicketMaster.controller;

import com.icet.ticketmaster.entity.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final com.ticketmaster.TicketMaster.service.BookingService bookingService;

    public BookingController(com.ticketmaster.TicketMaster.service.BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<com.ticketmaster.TicketMaster.dto.BookingResponse> confirmBooking(@RequestBody com.ticketmaster.TicketMaster.dto.BookingRequest request) {
        com.ticketmaster.TicketMaster.dto.BookingResponse response = bookingService.confirmBooking(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }
}
