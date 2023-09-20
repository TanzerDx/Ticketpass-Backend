package com.example.individual_assignment_hristo_ganchev.domain.TicketsRelated;

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

    @NotBlank
    private String QR;

    @NotBlank
    private String section;

    private Optional<Integer> row;

    private Optional<String> seat;
}
