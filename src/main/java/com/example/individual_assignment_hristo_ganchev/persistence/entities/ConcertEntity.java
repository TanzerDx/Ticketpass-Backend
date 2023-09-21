package com.example.individual_assignment_hristo_ganchev.persistence.entities;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConcertEntity {

    private Long id;

    private String artist;

    private String musicGenre;

    private String venue;

    private Date date;

    private String city;

    private String desc;

    private String photoURL;

    private Double price;

    private Long ticketsRemaining;
}
