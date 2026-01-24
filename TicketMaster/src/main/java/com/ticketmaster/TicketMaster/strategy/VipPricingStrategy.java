package com.ticketmaster.TicketMaster.strategy;


import com.icet.ticketmaster.entity.Event;
import com.icet.ticketmaster.entity.User;
import com.icet.ticketmaster.enums.UserTier;
import com.ticketmaster.TicketMaster.dto.PriceCalculationResult;
import com.ticketmaster.TicketMaster.strategy.PricingStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VipPricingStrategy implements PricingStrategy {

    private static final double VIP_DISCOUNT = 0.10;

    @Override
    public PriceCalculationResult calculatePrice(User user, Event event) {
        BigDecimal basePrice = event.getBasePrice();
        BigDecimal finalPrice = basePrice;
        double discount = 0.0;

        if (!event.getIsHighDemand()) {
            discount = VIP_DISCOUNT;
            finalPrice = basePrice.multiply(BigDecimal.valueOf(1 - VIP_DISCOUNT));
        }

        return PriceCalculationResult.builder()
                .finalPrice(finalPrice)
                .basePrice(basePrice)
                .discount(discount * 100)
                .priorityAccess(false)
                .userTier(user.getTier())
                .build();
    }

    @Override
    public boolean supports(User user) {
        return user.getTier() == UserTier.VIP;
    }
}
