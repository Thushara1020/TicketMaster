package com.ticketmaster.TicketMaster.util;

import com.ticketmaster.TicketMaster.entity.Seat;
import com.ticketmaster.TicketMaster.entity.User;
import com.ticketmaster.TicketMaster.enums.SeatStatus;
import com.ticketmaster.TicketMaster.repository.EventRepository;
import com.ticketmaster.TicketMaster.repository.SeatRepository;
import com.ticketmaster.TicketMaster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private SeatRepository seatRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private EventRepository eventRepository;

    @Override
    public void run(String... args) {

        seatRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setName("Test User");
        userRepository.save(user);

        Seat seat = new Seat();
        seat.setSeatNumber("A5");
        seat.setStatus(SeatStatus.AVAILABLE);
        seatRepository.save(seat);

        System.out.println("✅ Test Data Created Successfully!");
    }
}