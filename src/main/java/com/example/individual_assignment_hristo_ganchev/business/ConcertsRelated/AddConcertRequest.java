package com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated;

import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddConcertRequest {

    @NotBlank
    private String artist;

    @NotBlank
    private String musicGenre;

    @NotBlank
    private String venue;

    @NotBlank
    private String date;

    @NotBlank
    private String city;

    @NotBlank
    private String description;

    @NotBlank
    private String photoURL;

    @NotNull
    private Double price;

    @NotNull
    private Integer ticketsRemaining;
}
