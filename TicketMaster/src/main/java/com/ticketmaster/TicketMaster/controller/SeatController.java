package com.ticketmaster.TicketMaster.controller;

import com.ticketmaster.TicketMaster.entity.Seat;
import com.ticketmaster.TicketMaster.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }


    @GetMapping("/{id}")
    public Seat getSeatById(@PathVariable Long id) {
        return seatService.getSeatById(id);
    }
    @PostMapping("/{seatId}/hold")
    public ResponseEntity<?> holdSeat(@PathVariable Long seatId, @RequestParam Long userId) {
        try {
            Seat updatedSeat = seatService.holdSeat(seatId, userId);
            return ResponseEntity.ok(updatedSeat);
        } catch (RuntimeException e) {

            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Internal error occurred"));
        }
    }
}