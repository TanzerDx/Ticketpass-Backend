package com.example.individual_assignment_hristo_ganchev.business.TicketsRelated;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddTicketsResponse {
    private Long orderId;
    private String artist;
}
