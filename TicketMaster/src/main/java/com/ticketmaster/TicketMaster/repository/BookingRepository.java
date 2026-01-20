package com.ticketmaster.TicketMaster.repository;

import com.ticketmaster.TicketMaster.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {}