package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.ConcertConverter;
import com.example.individual_assignment_hristo_ganchev.business.Converters.OrderConverter;
import com.example.individual_assignment_hristo_ganchev.business.Converters.TicketConverter;
import com.example.individual_assignment_hristo_ganchev.business.Interfaces.TicketsService;
import com.example.individual_assignment_hristo_ganchev.business.TicketsRelated.AddTicketsRequest;
import com.example.individual_assignment_hristo_ganchev.business.TicketsRelated.AddTicketsResponse;
import com.example.individual_assignment_hristo_ganchev.business.TicketsRelated.UpdateTicketsRequest;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.domain.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.TicketEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.ConcertRepository;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.OrderRepository;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.TicketRepository;

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
        OrderEntity orderEntity = orderRepository.getById(request.getOrderId());
        Order order = OrderConverter.convert(orderEntity);

        ConcertEntity concertEntity = concertRepository.getById(request.getConcertId());
        Concert concert = ConcertConverter.convert(concertEntity);


        return AddTicketsResponse.builder()
                .tickets(
                saveTickets(order, concert)
                    .stream()
                    .map(TicketConverter::convert)
                    .toList())
                .build();
    }

    @Override
    public List<Ticket> getTickets(long orderId)
    {
        return ticketRepository.getByOrderId(orderId)
                .stream()
                .map(TicketConverter::convert)
                .toList();
    }

    @Override
    public void updateTickets(UpdateTicketsRequest request)
    {
        ConcertEntity concertEntity = concertRepository.getById(request.getConcertId());
        Concert concert = ConcertConverter.convert(concertEntity);

        List<TicketEntity> toUpdate = ticketRepository.getByConcertId(request.getConcertId());

        updateFields(toUpdate, concert);
    }


    private void updateFields(List<TicketEntity> toUpdate, Concert concert) {

            for (TicketEntity ticket : toUpdate) {

                ticket.setConcertVenue(concert.getVenue());
                ticket.setConcertDate(concert.getDate());
                ticket.setConcertCity(concert.getCity());

                ticketRepository.save(ticket);
            }

    }

    private List<TicketEntity> saveTickets(Order order, Concert concert)
    {

        List<TicketEntity> currentTickets = new ArrayList<>();

            for (int i = 0; i != order.getTicketNumber(); i++) {
                TicketEntity newTicket = TicketEntity.builder()
                        .orderId(order.getId())
                        .concertId(concert.getId())
                        .QR("QR")
                        .userName(order.getName() + " " + order.getSurname())
                        .concertArtist(concert.getArtist())
                        .concertVenue(concert.getVenue())
                        .concertDate(concert.getDate())
                        .concertCity(concert.getCity())
                        .venueSection("Standing")
                        .venueRow(null)
                        .venueSeat(null)
                        .build();

                ticketRepository.save(newTicket);
                currentTickets.add(newTicket);
            }
        return currentTickets;
    }
}
