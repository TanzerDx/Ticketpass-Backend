package com.example.individual_assignment_hristo_ganchev.controller;

import com.example.individual_assignment_hristo_ganchev.business.Interfaces.ConcertsUseCases;
import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.AddConcertRequest;
import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.AddConcertResponse;
import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.GetConcertsResponse;
import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.UpdateConcertRequest;
import com.example.individual_assignment_hristo_ganchev.domain.Objects.Concert;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/concerts")
@AllArgsConstructor
public class ConcertController {
    private final ConcertsUseCases concertsUseCases;

    @PostMapping()
    public ResponseEntity<AddConcertResponse> addConcert(@Valid @RequestBody AddConcertRequest request) {
        AddConcertResponse response = concertsUseCases.addConcert(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<GetConcertsResponse> getConcerts() {
        return ResponseEntity.ok(concertsUseCases.getAllConcerts());
    }


    @GetMapping("{id}")
    public ResponseEntity<Concert> getConcert(@PathVariable("id") long id) {
        final Concert concert = concertsUseCases.getConcert(id);
        return ResponseEntity.ok().body(concert);
    }

    @PutMapping("{id}")
    public ResponseEntity<Concert> updateConcert(@PathVariable("id") long id,
     @RequestBody @Valid UpdateConcertRequest request) {
        concertsUseCases.updateConcert(request);

        Concert concert = concertsUseCases.getConcert(id);

        return ResponseEntity.ok().body(concert);
    }
}
