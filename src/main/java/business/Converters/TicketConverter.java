package business.Converters;

import domain.Objects.Ticket;
import persistence.entities.TicketEntity;

import java.util.Date;
import java.util.Optional;

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
