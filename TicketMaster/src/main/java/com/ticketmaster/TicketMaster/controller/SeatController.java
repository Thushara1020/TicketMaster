package com.ticketmaster.TicketMaster.controller;

import com.ticketmaster.TicketMaster.entity.Seat;
import com.ticketmaster.TicketMaster.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/{id}/hold")
    public Seat holdSeat(@PathVariable Long id, @RequestParam Long userId) {
        return seatService.holdSeat(id, userId);
    }
}
