package com.example.individual_assignment_hristo_ganchev.business.TicketsRelated;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddTicketsRequest {

    private Long orderId;

    private Long concertId;

    private String QR;

    private String venueSection;

    private Integer venueRow;

    private Integer venueSeat;
}
