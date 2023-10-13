package com.example.individual_assignment_hristo_ganchev.business.TicketsRelated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketsRequest {

    private Long concertId;


}
