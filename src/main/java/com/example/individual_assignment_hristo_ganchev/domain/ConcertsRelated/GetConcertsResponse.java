package com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated;

import com.example.individual_assignment_hristo_ganchev.domain.Objects.Concert;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetConcertsResponse {
    private List<Concert> concerts;
}
