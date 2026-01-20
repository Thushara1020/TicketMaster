package com.ticketmaster.TicketMaster.entity;

import com.ticketmaster.TicketMaster.enums.Tier;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    private String email;

}