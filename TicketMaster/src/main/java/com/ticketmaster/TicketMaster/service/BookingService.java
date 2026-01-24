package com.ticketmaster.TicketMaster.service;

import com.ticketmaster.TicketMaster.dto.BookingRequest;
import com.ticketmaster.TicketMaster.dto.BookingResponse;
import com.ticketmaster.TicketMaster.dto.PriceCalculationResult;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final com.icet.ticketmaster.repository.BookingRepository bookingRepository;
    private final com.icet.ticketmaster.repository.UserRepository userRepository;
    private final com.icet.ticketmaster.repository.SeatRepository seatRepository;
    private final com.icet.ticketmaster.repository.EventRepository eventRepository;
    private final PriceCalculatorService priceCalculatorService;

    public BookingService(com.icet.ticketmaster.repository.BookingRepository bookingRepository,
                          com.icet.ticketmaster.repository.UserRepository userRepository,
                          com.icet.ticketmaster.repository.SeatRepository seatRepository,
                          com.icet.ticketmaster.repository.EventRepository eventRepository,
                          PriceCalculatorService priceCalculatorService) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.seatRepository = seatRepository;
        this.eventRepository = eventRepository;
        this.priceCalculatorService = priceCalculatorService;
    }

    @Transactional
    @com.icet.ticketmaster.annotation.AuditFailure
    public BookingResponse confirmBooking(BookingRequest request) {

        com.icet.ticketmaster.entity.User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        com.icet.ticketmaster.entity.Seat seat = seatRepository.findById(request.getSeatId())
                .orElseThrow(() -> new com.icet.ticketmaster.exception.SeatNotAvailableException("Seat not found"));

        if (seat.getStatus() == com.icet.ticketmaster.enums.SeatStatus.SOLD) {
            throw new com.icet.ticketmaster.exception.SeatNotAvailableException("Seat is already sold");
        }

        if (seat.getStatus() == com.icet.ticketmaster.enums.SeatStatus.HELD) {
            if (seat.getHeldByUserId() != null && !seat.getHeldByUserId().equals(user.getId())) {
                throw new com.icet.ticketmaster.exception.SeatNotAvailableException("Seat is held by another user");
            }
            if (seat.getHoldExpiry() != null && seat.getHoldExpiry().isBefore(LocalDateTime.now())) {
                throw new com.icet.ticketmaster.exception.SeatNotAvailableException("Seat hold has expired");
            }
        }

        com.icet.ticketmaster.entity.Event event = seat.getEvent();
        PriceCalculationResult priceResult = priceCalculatorService.calculatePrice(user, event);

        seat.setStatus(com.icet.ticketmaster.enums.SeatStatus.SOLD);
        seat.setHeldByUserId(null);
        seat.setHoldExpiry(null);
        seatRepository.save(seat);

        com.icet.ticketmaster.entity.Booking booking = new com.icet.ticketmaster.entity.Booking();
        booking.setUser(user);
        booking.setSeat(seat);
        booking.setAmountPaid(priceResult.getFinalPrice());
        booking.setStatus(com.icet.ticketmaster.enums.BookingStatus.CONFIRMED);

        bookingRepository.save(booking);

        return BookingResponse.builder()
                .bookingId(booking.getId())
                .userId(user.getId())
                .seatId(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .eventName(event.getName())
                .amountPaid(priceResult.getFinalPrice())
                .priorityAccess(priceResult.getPriorityAccess())
                .status(booking.getStatus())
                .message("Booking confirmed successfully")
                .build();
    }

    public List<com.icet.ticketmaster.entity.Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}