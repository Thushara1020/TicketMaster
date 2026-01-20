package com.ticketmaster.TicketMaster.exception;

public class SeatLockedException extends RuntimeException {
    public SeatLockedException(String message) {
        super(message);
    }
}
