package com.example.individual_assignment_hristo_ganchev.controller;

import com.example.individual_assignment_hristo_ganchev.business.Interfaces.ConcertsService;
import com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated.AddConcertRequest;
import com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated.AddConcertResponse;
import com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated.UpdateConcertRequest;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concerts")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173" , "http://localhost:4173"})
public class ConcertController {
    private final ConcertsService concertsService;

    @RolesAllowed({"admin", "manager"})
    @PostMapping()
    public ResponseEntity<AddConcertResponse> addConcert(@Valid @RequestBody AddConcertRequest request) {
        AddConcertResponse response = concertsService.addConcert(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Concert>> getConcerts() {
        return ResponseEntity.ok(concertsService.getAllConcerts());
    }


    @GetMapping(value = "filter")
    public ResponseEntity<List<Concert>> filterConcerts(@RequestParam(name = "keyword") String keyword) {
        return ResponseEntity.ok(concertsService.filterConcerts(keyword));
    }


    @GetMapping("{id}")
    public ResponseEntity<Concert> getConcert(@PathVariable("id") long id) {
        final Concert concert = concertsService.getConcert(id);
        return ResponseEntity.ok().body(concert);
    }

    @RolesAllowed({"admin", "manager"})
    @PutMapping("{id}")
    public ResponseEntity<Concert> updateConcert(@PathVariable("id") long id,
     @RequestBody @Valid UpdateConcertRequest request) {
        concertsService.updateConcert(request);

        Concert concert = concertsService.getConcert(id);

        return ResponseEntity.ok().body(concert);
    }
}
