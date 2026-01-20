package com.ticketmaster.TicketMaster.repository;

import com.ticketmaster.TicketMaster.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {}