package com.icet.ticketmaster.exception;

public class SeatLockedException extends RuntimeException {
    private final long remainingSeconds;

    public SeatLockedException(long remainingSeconds) {
        super("Seat is currently locked. Try again in " + remainingSeconds + " seconds.");
        this.remainingSeconds = remainingSeconds;
    }

    public long getRemainingSeconds() {
        return remainingSeconds;
    }
}
