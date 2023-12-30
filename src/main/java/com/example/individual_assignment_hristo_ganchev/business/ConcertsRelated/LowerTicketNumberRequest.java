package com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated;

import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LowerTicketNumberRequest {
    @NotBlank
    private Long concertId;

    @NotNull
    private Integer ticketNumber;
}
