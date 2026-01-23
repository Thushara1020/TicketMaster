package com.ticketmaster.TicketMaster.dto;

import com.icet.ticketmaster.enums.UserTier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceCalculationResult {
    private BigDecimal finalPrice;
    private BigDecimal basePrice;
    private Double discount;
    private Boolean priorityAccess;
    private UserTier userTier;
}
