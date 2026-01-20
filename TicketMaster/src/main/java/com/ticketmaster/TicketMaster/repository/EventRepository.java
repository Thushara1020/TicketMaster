package com.ticketmaster.TicketMaster.repository;

import com.ticketmaster.TicketMaster.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {}