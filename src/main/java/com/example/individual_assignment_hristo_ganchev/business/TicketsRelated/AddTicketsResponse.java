package com.example.individual_assignment_hristo_ganchev.business.TicketsRelated;

import com.example.individual_assignment_hristo_ganchev.domain.Ticket;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AddTicketsResponse {
    private List<Ticket> tickets;
}
