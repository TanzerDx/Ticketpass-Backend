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
                .orderId(ticket.getOrderId())
                .concertId(ticket.getConcertId())
                .QR(ticket.getQR())
                .userName(ticket.getUserName())
                .concertArtist(ticket.getConcertArtist())
                .concertVenue(ticket.getConcertVenue())
                .concertDate(ticket.getConcertDate())
                .concertCity(ticket.getConcertCity())
                .venueSection(ticket.getVenueSection())
                .venueRow(ticket.getVenueRow())
                .venueSeat(ticket.getVenueSeat())
                .build();

    }
}
