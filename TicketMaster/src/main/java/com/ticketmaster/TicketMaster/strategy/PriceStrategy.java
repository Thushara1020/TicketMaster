package com.ticketmaster.TicketMaster.strategy;

import com.ticketmaster.TicketMaster.dto.PriceResponse;
import com.ticketmaster.TicketMaster.entity.Event;
import com.ticketmaster.TicketMaster.entity.User;

public interface PriceStrategy {
    PriceResponse calculate(User user, Event event);
}
