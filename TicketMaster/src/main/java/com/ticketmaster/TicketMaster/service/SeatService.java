package com.ticketmaster.TicketMaster.service;

import com.ticketmaster.TicketMaster.entity.Seat;
import com.ticketmaster.TicketMaster.enums.SeatStatus;
import com.ticketmaster.TicketMaster.exception.SeatLockedException;
import com.ticketmaster.TicketMaster.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepo;

    @Transactional
    public Seat holdSeat(Long seatId, Long userId) {

        Seat seat = seatRepo.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getStatus() == SeatStatus.HELD &&
                seat.getHoldExpiry() != null &&
                seat.getHoldExpiry().isAfter(LocalDateTime.now())) {

            long seconds = Duration.between(
                    LocalDateTime.now(), seat.getHoldExpiry()).getSeconds();

            throw new SeatLockedException("Seat locked for " + seconds + " seconds");
        }

        seat.setStatus(SeatStatus.HELD);
        seat.setHeldByUserId(userId);
        seat.setHoldExpiry(LocalDateTime.now().plusMinutes(10));

        return seatRepo.save(seat);
    }

    public List<Seat> getAllSeats() {
        return seatRepo.findAll();
    }

    public Seat getSeatById(Long id) {
        return seatRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat with ID " + id + " not found"));
    }
}