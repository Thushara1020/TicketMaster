package com.ticketmaster.TicketMaster.service;

import com.ticketmaster.TicketMaster.dto.PriceResponse;
import com.ticketmaster.TicketMaster.entity.Event;
import com.ticketmaster.TicketMaster.entity.User;
import com.ticketmaster.TicketMaster.strategy.PlatinumPriceStrategy;
import com.ticketmaster.TicketMaster.strategy.PriceStrategy;
import com.ticketmaster.TicketMaster.strategy.RegularPriceStrategy;
import com.ticketmaster.TicketMaster.strategy.VipPriceStrategy;
import org.springframework.stereotype.Service;

import static com.ticketmaster.TicketMaster.enums.Tier.PLATINUM;
import static com.ticketmaster.TicketMaster.enums.Tier.VIP;

@Service
public class PriceCalculatorService {

    public PriceResponse calculate(User user, Event event) {
        PriceStrategy strategy;

        switch (user.getTier()) {
            case VIP:
                strategy = new VipPriceStrategy();
                break;
            case PLATINUM:
                strategy = new PlatinumPriceStrategy();
                break;
            default:
                strategy = new RegularPriceStrategy();
                break;
        }

        return strategy.calculate(user, event);
    }
}