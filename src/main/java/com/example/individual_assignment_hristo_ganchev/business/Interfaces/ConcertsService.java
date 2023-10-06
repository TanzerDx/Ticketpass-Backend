package com.example.individual_assignment_hristo_ganchev.business.Interfaces;

import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.AddConcertRequest;
import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.AddConcertResponse;
import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.GetConcertsResponse;
import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.UpdateConcertRequest;
import com.example.individual_assignment_hristo_ganchev.domain.Objects.Concert;

public interface ConcertsService {
    AddConcertResponse addConcert(AddConcertRequest request);

    Concert getConcert(Long id);

    GetConcertsResponse getAllConcerts();

    void updateConcert(UpdateConcertRequest request);
}
