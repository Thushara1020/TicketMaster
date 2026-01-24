package com.ticketmaster.TicketMaster.service;

import com.icet.ticketmaster.entity.Event;
import com.icet.ticketmaster.entity.User;
import com.ticketmaster.TicketMaster.dto.PriceCalculationResult;
import com.ticketmaster.TicketMaster.strategy.PricingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {

    private final List<PricingStrategy> pricingStrategies;

    public PriceCalculatorService(List<PricingStrategy> pricingStrategies) {
        this.pricingStrategies = pricingStrategies;
    }

    public PriceCalculationResult calculatePrice(User user, Event event) {
        return pricingStrategies.stream()
                .filter(strategy -> strategy.supports(user))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No pricing strategy found for user tier: " + user.getTier()))
                .calculatePrice(user, event);
    }
}
