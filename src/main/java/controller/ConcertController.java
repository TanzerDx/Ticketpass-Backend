package controller;

import business.Interfaces.ConcertsUseCases;
import domain.ConcertsRelated.AddConcertRequest;
import domain.ConcertsRelated.AddConcertResponse;
import domain.ConcertsRelated.GetConcertsResponse;
import domain.ConcertsRelated.UpdateConcertRequest;
import domain.Objects.Concert;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/concerts")
@AllArgsConstructor
public class ConcertController {
    private final ConcertsUseCases concertsUseCases;

    @PostMapping()
    public ResponseEntity<AddConcertResponse> addConcert(@RequestBody  AddConcertRequest request) {
        AddConcertResponse response = concertsUseCases.addConcert(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<GetConcertsResponse> getConcerts() {
        return ResponseEntity.ok(concertsUseCases.getAllConcerts());
    }


    @GetMapping("{id}")
    public ResponseEntity<Concert> getConcert(@RequestParam(name = "id", required = false) final long id) {
        final Concert concert = concertsUseCases.getConcert(id);
        return ResponseEntity.ok().body(concert);
    }

    @PutMapping("{id}")
    public ResponseEntity<Concert> updateConcert(@PathVariable("id") long id,
     @RequestBody @Valid UpdateConcertRequest request) {
        request.setId(id);
        concertsUseCases.updateConcert(request);

        Concert concert = concertsUseCases.getConcert(id);

        return ResponseEntity.ok().body(concert);
    }
}
