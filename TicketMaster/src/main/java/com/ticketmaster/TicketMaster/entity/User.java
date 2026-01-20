package com.ticketmaster.TicketMaster.entity;

import com.ticketmaster.TicketMaster.enums.Tier;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "app_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;
}