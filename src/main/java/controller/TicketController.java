package controller;

import business.Converters.TicketConverter;
import business.Interfaces.ConcertsUseCases;
import business.Interfaces.TicketsUseCases;
import domain.ConcertsRelated.AddConcertRequest;
import domain.ConcertsRelated.AddConcertResponse;
import domain.ConcertsRelated.GetConcertsResponse;
import domain.ConcertsRelated.UpdateConcertRequest;
import domain.Objects.Concert;
import domain.Objects.Ticket;
import domain.TicketsRelated.AddTicketsRequest;
import domain.TicketsRelated.AddTicketsResponse;
import domain.TicketsRelated.UpdateTicketsRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persistence.entities.TicketEntity;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
public class TicketController {

    private final TicketsUseCases ticketsUseCases;

    @PostMapping()
    public ResponseEntity<AddTicketsResponse> addTickets(@RequestBody AddTicketsRequest request) {
        AddTicketsResponse response = ticketsUseCases.addTickets(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Ticket>> getTickets(@PathVariable("id") Long orderId) {
        List<Ticket> tickets = ticketsUseCases.getTickets(orderId);

        return ResponseEntity.ok().body(tickets);
    }

    @PutMapping()
    public ResponseEntity<Void> updateTickets(@RequestBody @Valid UpdateTicketsRequest request) {
        ticketsUseCases.updateTickets(request);

        return ResponseEntity.noContent().build();
    }
}
