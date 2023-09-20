package com.example.individual_assignment_hristo_ganchev.business.Converters;

import com.example.individual_assignment_hristo_ganchev.domain.Objects.Ticket;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.TicketEntity;

public final class TicketConverter {
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
                .section(ticket.getSection())
                .row(ticket.getRow())
                .seat(ticket.getSeat())
                .build();

    }
}
