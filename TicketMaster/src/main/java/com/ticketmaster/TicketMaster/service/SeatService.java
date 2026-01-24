package com.ticketmaster.TicketMaster.service;


import com.icet.ticketmaster.entity.Seat;
import com.icet.ticketmaster.enums.SeatStatus;
import com.icet.ticketmaster.exception.SeatLockedException;
import com.icet.ticketmaster.exception.SeatNotAvailableException;
import com.icet.ticketmaster.repository.SeatRepository;
import com.ticketmaster.TicketMaster.dto.SeatHoldResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class SeatService {

    private static final long HOLD_DURATION_MINUTES = 10;

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Transactional
    public SeatHoldResponse holdSeat(Long seatId, Long userId) {
        Seat seat = seatRepository.findByIdWithLock(seatId)
                .orElseThrow(() -> new SeatNotAvailableException("Seat not found"));

        LocalDateTime now = LocalDateTime.now();

        if (seat.getStatus() == SeatStatus.SOLD) {
            throw new SeatNotAvailableException("Seat is already sold");
        }

        if (seat.getStatus() == SeatStatus.HELD) {
            if (seat.getHoldExpiry() != null && seat.getHoldExpiry().isAfter(now)) {
                long remainingSeconds = ChronoUnit.SECONDS.between(now, seat.getHoldExpiry());
                throw new SeatLockedException(remainingSeconds);
            }
        }

        seat.setStatus(SeatStatus.HELD);
        seat.setHeldByUserId(userId);
        seat.setHoldExpiry(now.plusMinutes(HOLD_DURATION_MINUTES));

        seatRepository.save(seat);

        return SeatHoldResponse.builder()
                .seatId(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .status(seat.getStatus())
                .heldByUserId(userId)
                .holdExpiry(seat.getHoldExpiry())
                .message("Seat held successfully for " + HOLD_DURATION_MINUTES + " minutes")
                .build();
    }

    public List<Seat> getAvailableSeats(Long eventId) {
        return seatRepository.findByEventIdAndStatus(eventId, SeatStatus.AVAILABLE);
    }

    public Seat getSeatById(Long seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new SeatNotAvailableException("Seat not found"));
    }
}
