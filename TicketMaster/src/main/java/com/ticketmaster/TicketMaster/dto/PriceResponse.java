package com.ticketmaster.TicketMaster.dto;

public class PriceResponse {
    private double price;
    private boolean priorityAccess;

    public PriceResponse(double price, boolean priorityAccess) {
        this.price = price;
        this.priorityAccess = priorityAccess;
    }
}
