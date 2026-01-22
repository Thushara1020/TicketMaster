package com.ticketmaster.TicketMaster.controller;

import com.icet.ticketmaster.dto.SeatHoldRequest;

import com.icet.ticketmaster.entity.Seat;
import com.ticketmaster.TicketMaster.dto.SeatHoldResponse;
import com.ticketmaster.TicketMaster.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/{id}/hold")
    public ResponseEntity<SeatHoldResponse> holdSeat(
            @PathVariable Long id,
            @RequestBody SeatHoldRequest request) {
        SeatHoldResponse response = seatService.holdSeat(id, request.getUserId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/event/{eventId}/available")
    public ResponseEntity<List<Seat>> getAvailableSeats(@PathVariable Long eventId) {
        List<Seat> seats = seatService.getAvailableSeats(eventId);
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long id) {
        Seat seat = seatService.getSeatById(id);
        return ResponseEntity.ok(seat);
    }
}
