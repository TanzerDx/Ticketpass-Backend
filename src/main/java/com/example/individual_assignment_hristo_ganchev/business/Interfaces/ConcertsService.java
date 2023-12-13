package com.example.individual_assignment_hristo_ganchev.business.Interfaces;

import com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated.AddConcertRequest;
import com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated.AddConcertResponse;
import com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated.UpdateConcertRequest;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;

import java.util.List;

public interface ConcertsService {
    AddConcertResponse addConcert(AddConcertRequest request);

    Concert getConcert(Long id);

    List<Concert> getAllConcerts();

    List<Concert> filterConcerts(String keyword);

    void updateConcert(UpdateConcertRequest request);
}
