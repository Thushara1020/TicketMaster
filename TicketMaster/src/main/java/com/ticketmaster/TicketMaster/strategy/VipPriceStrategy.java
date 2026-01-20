package com.ticketmaster.TicketMaster.strategy;

import com.ticketmaster.TicketMaster.dto.PriceResponse;
import com.ticketmaster.TicketMaster.entity.Event;
import com.ticketmaster.TicketMaster.entity.User;

public class VipPriceStrategy implements PriceStrategy {

    @Override
    public PriceResponse calculate(User user, Event event) {

        double finalPrice;

        boolean isHighDemand = false;

        if (isHighDemand) {
            finalPrice = event.getBasePrice();
        } else {
            finalPrice = event.getBasePrice() * 0.9;
        }

        return new PriceResponse(finalPrice, isHighDemand);
    }
}