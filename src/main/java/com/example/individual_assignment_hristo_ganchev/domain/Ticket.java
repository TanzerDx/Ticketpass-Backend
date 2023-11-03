package com.example.individual_assignment_hristo_ganchev.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    private Long id;

    private Long orderId;

    private Long concertId;

    private String QR;

    private String userName;

    private String concertArtist;

    private String concertVenue;

    private Date concertDate;

    private String concertCity;

    private String venueSection;

    private Integer venueRow;

    private Integer venueSeat;
}
