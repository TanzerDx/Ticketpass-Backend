package business.Interfaces;

import domain.Objects.Ticket;
import domain.TicketsRelated.*;

import java.util.List;

public interface TicketsUseCases {
    AddTicketsResponse addTickets(AddTicketsRequest request);

    List<Ticket> getTickets(long orderId);

    void updateTickets(UpdateTicketsRequest request);
}
