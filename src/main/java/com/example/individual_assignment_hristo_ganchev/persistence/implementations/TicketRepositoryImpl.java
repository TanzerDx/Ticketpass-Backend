//package com.example.individual_assignment_hristo_ganchev.persistence.implementations;
//
//import org.springframework.stereotype.Repository;
//import com.example.individual_assignment_hristo_ganchev.persistence.entities.TicketEntity;
//import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.TicketRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class TicketRepositoryImpl implements TicketRepository {
//    private static long NEXT_ID = 1;
//
//    private final List<TicketEntity> savedTickets;
//
//    public TicketRepositoryImpl() {
//        this.savedTickets = new ArrayList<>();
//    }
//
//    @Override
//    public TicketEntity add(TicketEntity ticket)
//    {
//        ticket.setId(NEXT_ID);
//        NEXT_ID++;
//        this.savedTickets.add(ticket);
//        return ticket;
//    }
//
//    @Override
//    public List<TicketEntity> getTickets(long orderId)
//    {
//        return this.savedTickets.stream()
//                .filter(ticketEntity -> ticketEntity.getOrderId().equals(orderId))
//                .toList();
//    }
//
//    @Override
//    public List<TicketEntity> getTicketsByConcert(long concertId)
//    {
//        return this.savedTickets.stream()
//                .filter(ticketEntity -> ticketEntity.getConcertId().equals(concertId))
//                .toList();
//    }
//
//}
