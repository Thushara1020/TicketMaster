package com.ticketmaster.TicketMaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double basePrice;
    public boolean isHighDemand;
    private LocalDate eventDate;

    public double getBasePrice() {
        return 0;
    }
}
