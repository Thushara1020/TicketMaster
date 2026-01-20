package com.ticketmaster.TicketMaster.strategy;

public class PriceResponse {

    private double price;
    private boolean priorityAccess;

    public PriceResponse(double price, boolean priorityAccess) {
        this.price = price;
        this.priorityAccess = priorityAccess;
    }

    public double getPrice() {
        return price;
    }

    public boolean isPriorityAccess() {
        return priorityAccess;
    }
}
