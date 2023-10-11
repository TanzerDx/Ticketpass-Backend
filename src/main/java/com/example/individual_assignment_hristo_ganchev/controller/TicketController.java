package com.example.individual_assignment_hristo_ganchev.controller;


import com.example.individual_assignment_hristo_ganchev.business.Interfaces.TicketsService;
import com.example.individual_assignment_hristo_ganchev.domain.Objects.Ticket;
import com.example.individual_assignment_hristo_ganchev.domain.TicketsRelated.AddTicketsRequest;
import com.example.individual_assignment_hristo_ganchev.domain.TicketsRelated.AddTicketsResponse;
import com.example.individual_assignment_hristo_ganchev.domain.TicketsRelated.UpdateTicketsRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173" , "http://localhost:4173"})
public class TicketController {

    private final TicketsService ticketsService;

    @PostMapping()
    public ResponseEntity<AddTicketsResponse> addTickets(@RequestBody AddTicketsRequest request) {
        AddTicketsResponse response = ticketsService.addTickets(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Ticket>> getTickets(@PathVariable("id") Long orderId) {
        List<Ticket> tickets = ticketsService.getTickets(orderId);

        return ResponseEntity.ok().body(tickets);
    }

    @PutMapping()
    public ResponseEntity<Void> updateTickets(@RequestBody @Valid UpdateTicketsRequest request) {
        ticketsService.updateTickets(request);

        return ResponseEntity.noContent().build();
    }
}
