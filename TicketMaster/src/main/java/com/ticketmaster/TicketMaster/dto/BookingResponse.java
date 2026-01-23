package com.ticketmaster.TicketMaster.dto;

import com.icet.ticketmaster.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Long bookingId;
    private Long userId;
    private Long seatId;
    private String seatNumber;
    private String eventName;
    private BigDecimal amountPaid;
    private Double discount;
    private Boolean priorityAccess;
    private BookingStatus status;
    private String message;
}
