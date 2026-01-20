package com.ticketmaster.TicketMaster.strategy;

import com.ticketmaster.TicketMaster.dto.PriceResponse;
import com.ticketmaster.TicketMaster.entity.Event;
import com.ticketmaster.TicketMaster.entity.User;

public class RegularPriceStrategy implements PriceStrategy {

    @Override
    public PriceResponse calculate(User user, Event event) {
        double price = event.getBasePrice();
        return new PriceResponse(price, false);
    }
}