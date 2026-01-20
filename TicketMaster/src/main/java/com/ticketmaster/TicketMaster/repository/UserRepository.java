package com.ticketmaster.TicketMaster.repository;

import com.ticketmaster.TicketMaster.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}