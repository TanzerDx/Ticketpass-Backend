package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.OrderConverter;
import com.example.individual_assignment_hristo_ganchev.business.Converters.TicketConverter;
import com.example.individual_assignment_hristo_ganchev.business.Interfaces.TicketsService;
import com.example.individual_assignment_hristo_ganchev.business.TicketsRelated.AddTicketsRequest;
import com.example.individual_assignment_hristo_ganchev.business.TicketsRelated.AddTicketsResponse;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.domain.Ticket;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessToken;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.TicketEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.OrderRepository;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.TicketRepository;


import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketsServiceImpl implements TicketsService {

    private final TicketRepository ticketRepository;
    private final OrderRepository orderRepository;
    private AccessToken requestAccessToken;


    @Override
    public AddTicketsResponse addTickets(AddTicketsRequest request)
    {
        OrderEntity orderEntity = OrderConverter.convertToEntity(request.getOrder());


        return AddTicketsResponse.builder()
                .tickets(
                saveTickets(orderEntity)
                    .stream()
                    .map(TicketConverter::convert)
                    .toList())
                .build();
    }

    @Override
    public List<Ticket> getTickets(long orderId)
    {
        OrderEntity order = orderRepository.getById(orderId);

        if (!requestAccessToken.hasRole("admin") && !requestAccessToken.getUserId().equals(order.getUser().getId()))
        {
            throw new AccessDeniedException("Unauthorized access");
        }

        return ticketRepository.getByOrderId(orderId)
                .stream()
                .map(TicketConverter::convert)
                .toList();
    }

    private List<TicketEntity> saveTickets(OrderEntity order)
    {

        List<TicketEntity> currentTickets = new ArrayList<>();

            for (int i = 0; i != order.getTicketNumber(); i++) {
                TicketEntity newTicket = TicketEntity.builder()
                        .order(order)
                        .QR("QR")
                        .userName(order.getName() + " " + order.getSurname())
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
