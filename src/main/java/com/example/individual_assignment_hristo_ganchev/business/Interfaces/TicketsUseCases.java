package com.example.individual_assignment_hristo_ganchev.business.Interfaces;

import com.example.individual_assignment_hristo_ganchev.domain.Objects.Ticket;
import com.example.individual_assignment_hristo_ganchev.domain.TicketsRelated.*;

import java.util.List;

public interface TicketsUseCases {
    AddTicketsResponse addTickets(AddTicketsRequest request);

    List<Ticket> getTickets(long orderId);

    void updateTickets(UpdateTicketsRequest request);
}
