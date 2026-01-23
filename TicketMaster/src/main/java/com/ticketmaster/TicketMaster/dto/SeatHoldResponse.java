package com.ticketmaster.TicketMaster.dto;

import com.icet.ticketmaster.enums.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatHoldResponse {
    private Long seatId;
    private String seatNumber;
    private SeatStatus status;
    private Long heldByUserId;
    private LocalDateTime holdExpiry;
    private String message;
}
