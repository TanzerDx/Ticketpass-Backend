package com.example.individual_assignment_hristo_ganchev.business.Converters;

import com.example.individual_assignment_hristo_ganchev.domain.Ticket;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.TicketEntity;

public final class TicketConverter {

    private TicketConverter()
    {
        throw new IllegalStateException("Ticket converter");
    }

    public static Ticket convert(TicketEntity ticket) {

        return Ticket.builder()
                .id(ticket.getId())
                .order(OrderConverter.convert(ticket.getOrder()))
                .QR(ticket.getQR())
                .userName(ticket.getUserName())
                .venueSection(ticket.getVenueSection())
                .venueRow(ticket.getVenueRow())
                .venueSeat(ticket.getVenueSeat())
                .build();

    }

    public static TicketEntity convertToEntity(Ticket ticket) {

        return TicketEntity.builder()
                .id(ticket.getId())
                .order(OrderConverter.convertToEntity(ticket.getOrder()))
                .QR(ticket.getQR())
                .userName(ticket.getUserName())
                .venueSection(ticket.getVenueSection())
                .venueRow(ticket.getVenueRow())
                .venueSeat(ticket.getVenueSeat())
                .build();

    }
}
