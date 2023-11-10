package com.example.individual_assignment_hristo_ganchev.business.Interfaces;

import com.example.individual_assignment_hristo_ganchev.business.TicketsRelated.AddTicketsRequest;
import com.example.individual_assignment_hristo_ganchev.business.TicketsRelated.AddTicketsResponse;
import com.example.individual_assignment_hristo_ganchev.domain.Ticket;

import java.util.List;

public interface TicketsService {
    AddTicketsResponse addTickets(AddTicketsRequest request);

    List<Ticket> getTickets(long orderId);

}
