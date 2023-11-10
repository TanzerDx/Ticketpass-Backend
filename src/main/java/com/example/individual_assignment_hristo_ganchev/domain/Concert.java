package com.example.individual_assignment_hristo_ganchev.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Concert {

    private Long id;

    private String artist;

    private String musicGenre;

    private String venue;

    private Date date;

    private String city;

    private String description;

    private String photoURL;

    private Double price;

    private Integer ticketsRemaining;

    private List<Order> orders;
}
