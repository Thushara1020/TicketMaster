package com.ticketmaster.TicketMaster.exception;

import com.ticketmaster.TicketMaster.exception.SeatLockedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SeatLockedException.class)
    public ResponseEntity<String> handleSeatLocked(SeatLockedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}