package com.example.individual_assignment_hristo_ganchev.business.Interfaces;

import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.AddConcertRequest;
import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.AddConcertResponse;
import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.UpdateConcertRequest;
import com.example.individual_assignment_hristo_ganchev.domain.Objects.Concert;

import java.util.List;

public interface ConcertsService {
    AddConcertResponse addConcert(AddConcertRequest request);

    Concert getConcert(Long id);

    List<Concert> getAllConcerts();

    void updateConcert(UpdateConcertRequest request);
}
