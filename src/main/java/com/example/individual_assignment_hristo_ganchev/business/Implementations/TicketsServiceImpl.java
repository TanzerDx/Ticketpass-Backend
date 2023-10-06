package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.ConcertConverter;
import com.example.individual_assignment_hristo_ganchev.business.Converters.OrderConverter;
import com.example.individual_assignment_hristo_ganchev.business.Converters.TicketConverter;
import com.example.individual_assignment_hristo_ganchev.business.Interfaces.TicketsService;
import com.example.individual_assignment_hristo_ganchev.domain.Objects.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.Objects.Order;
import com.example.individual_assignment_hristo_ganchev.domain.Objects.Ticket;
import com.example.individual_assignment_hristo_ganchev.domain.TicketsRelated.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.TicketEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.ConcertRepository;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.OrderRepository;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.TicketRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketsServiceImpl implements TicketsService {
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

        List<TicketEntity> toUpdate = ticketRepository.getTicketsByConcert(request.getConcertId());

        updateFields(toUpdate, concert);
    }


    private void updateFields(List<TicketEntity> toUpdate, Concert concert) {

        SimpleDateFormat originalSdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        SimpleDateFormat targetSdf = new SimpleDateFormat("yyyy/MM/dd");

        try
        {
            for (TicketEntity ticket : toUpdate) {
                java.util.Date originalDate = originalSdf.parse(String.valueOf(concert.getDate()));
                String reformattedDate = targetSdf.format(originalDate);
                java.util.Date formattedDate = targetSdf.parse(reformattedDate);

                ticket.setConcertVenue(concert.getVenue());
                ticket.setConcertDate(formattedDate);
                ticket.setConcertCity(concert.getCity());
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private List<TicketEntity> saveTickets(AddTicketsRequest request, Order order, Concert concert)
    {
        List<TicketEntity> currentTickets = new ArrayList<TicketEntity>();

        for(int i = 0; i != order.getTicketNumber() ; i++) {
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
