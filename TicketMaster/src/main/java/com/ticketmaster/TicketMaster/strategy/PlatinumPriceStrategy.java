package com.ticketmaster.TicketMaster.strategy;

import com.ticketmaster.TicketMaster.dto.PriceResponse;
import com.ticketmaster.TicketMaster.entity.Event;
import com.ticketmaster.TicketMaster.entity.User;

public class PlatinumPriceStrategy implements PriceStrategy {

    @Override
    public PriceResponse calculate(User user, Event event) {
        return new PriceResponse(event.getBasePrice(), true);
    }
}