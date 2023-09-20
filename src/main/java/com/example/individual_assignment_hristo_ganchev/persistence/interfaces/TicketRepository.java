package com.example.individual_assignment_hristo_ganchev.persistence.interfaces;

import com.example.individual_assignment_hristo_ganchev.persistence.entities.TicketEntity;

import java.util.List;

public interface TicketRepository {
    TicketEntity add(TicketEntity ticket);

    List<TicketEntity> getTickets(long orderId);

    List<TicketEntity> getTicketsByConcert(long concertId);
}
