package persistence.interfaces;

import persistence.entities.TicketEntity;

import java.util.List;

public interface TicketRepository {
    TicketEntity add(TicketEntity ticket);

    List<TicketEntity> getTickets(long orderId);

    List<TicketEntity> getTicketsByConcert(long concertId);
}
