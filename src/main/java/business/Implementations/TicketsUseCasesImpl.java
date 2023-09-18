package business.Implementations;

import business.Converters.ConcertConverter;
import business.Converters.OrderConverter;
import business.Converters.TicketConverter;
import business.Interfaces.TicketsUseCases;
import domain.Objects.Concert;
import domain.Objects.Order;
import domain.Objects.Ticket;
import domain.TicketsRelated.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import persistence.entities.ConcertEntity;
import persistence.entities.OrderEntity;
import persistence.entities.TicketEntity;
import persistence.interfaces.ConcertRepository;
import persistence.interfaces.OrderRepository;
import persistence.interfaces.TicketRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketsUseCasesImpl implements TicketsUseCases {
    public final TicketRepository ticketRepository;
    public final OrderRepository orderRepository;
    public final ConcertRepository concertRepository;

    @Override
    public AddTicketsResponse addTickets(AddTicketsRequest request)
    {
        OrderEntity orderEntity = orderRepository.getOrder(request.getOrderId());
        Order order = OrderConverter.convert(orderEntity);

        ConcertEntity concertEntity = concertRepository.getConcert(request.getConcertId());
        Concert concert = ConcertConverter.convert(concertEntity);

        List<TicketEntity> toShow = saveTickets(request, order, concert);

        return AddTicketsResponse.builder()
                .orderId(order.getId())
                .artist(concert.getArtist())
                .build();
    }

    @Override
    public List<Ticket> getTickets(long orderId)
    {
        List<Ticket> tickets= ticketRepository.getTickets(orderId)
                .stream()
                .map(TicketConverter::convert)
                .toList();

        return tickets;
    }

    @Override
    public void updateTickets(UpdateTicketsRequest request)
    {
        ConcertEntity concertEntity = concertRepository.getConcert(request.getConcertId());
        Concert concert = ConcertConverter.convert(concertEntity);

        List<Ticket> toUpdate = ticketRepository.getTicketsByConcert(request.getConcertId())
                        .stream()
                        .map(TicketConverter::convert)
                        .toList();

        updateFields(toUpdate, concert);
    }


    private void updateFields(List<Ticket> toUpdate, Concert concert) {
        for (Ticket ticket : toUpdate) {
            ticket.setConcertVenue(concert.getVenue());
            ticket.setConcertDate(concert.getDate());
            ticket.setConcertCity(concert.getCity());
        }

    }

    private List<TicketEntity> saveTickets(AddTicketsRequest request, Order order, Concert concert)
    {
        List<TicketEntity> currentTickets = new ArrayList<TicketEntity>();

        for(int i = 1; i < order.getTicketNumber() ; i++) {
            TicketEntity newTicket = TicketEntity.builder()
                    .orderId(order.getId())
                    .concertId(concert.getId())
                    .QR("QR")
                    .userName(order.getName() + " " + order.getSurname())
                    .concertArtist(concert.getArtist())
                    .concertVenue(concert.getVenue())
                    .concertDate(concert.getDate())
                    .concertCity(concert.getCity())
                    .section("Standing")
                    .row(null)
                    .seat(null)
                    .build();

            ticketRepository.add(newTicket);
            currentTickets.add(newTicket);
        }

        return currentTickets;
    }
}
