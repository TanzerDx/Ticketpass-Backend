package business.Interfaces;

import domain.TicketsRelated.*;

public interface TicketsUseCases {
    AddTicketsResponse addTickets(AddTicketsRequest request);

    GetTicketsResponse getTickets(GetTicketsRequest request);

    void updateTickets(UpdateTicketsRequest request);
}
