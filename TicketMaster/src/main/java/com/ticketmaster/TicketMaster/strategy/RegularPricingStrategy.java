package com.ticketmaster.TicketMaster.strategy;

import com.icet.ticketmaster.entity.Event;
import com.icet.ticketmaster.entity.User;
import com.icet.ticketmaster.enums.UserTier;
import com.ticketmaster.TicketMaster.dto.PriceCalculationResult;
import org.springframework.stereotype.Component;

@Component
public class RegularPricingStrategy implements PricingStrategy {

    @Override
    public PriceCalculationResult calculatePrice(User user, Event event) {
        return PriceCalculationResult.builder()
                .finalPrice(event.getBasePrice())
                .basePrice(event.getBasePrice())
                .discount(0.0)
                .priorityAccess(false)
                .userTier(user.getTier())
                .build();
    }

    @Override
    public boolean supports(User user) {
        return user.getTier() == UserTier.REGULAR;
    }
}
