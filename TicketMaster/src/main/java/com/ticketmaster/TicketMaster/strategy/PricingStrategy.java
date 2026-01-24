package com.ticketmaster.TicketMaster.strategy;


import com.icet.ticketmaster.entity.Event;
import com.icet.ticketmaster.entity.User;
import com.ticketmaster.TicketMaster.dto.PriceCalculationResult;

public interface PricingStrategy {
    PriceCalculationResult calculatePrice(User user, Event event);
    boolean supports(User user);
}
