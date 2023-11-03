package com.example.individual_assignment_hristo_ganchev.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "tickets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long orderId;

    @NotNull
    private Long concertId;

    @NotEmpty
    private String QR;

    @NotEmpty
    @Length(min = 2, max = 50)
    private String userName;

    @NotEmpty
    @Length(min = 1, max = 20)
    private String concertArtist;

    @NotEmpty
    @Length(min = 1, max = 20)
    private String concertVenue;

    @NotNull
    private Date concertDate;

    @NotEmpty
    private String concertCity;

    @NotEmpty
    private String venueSection;

    private Integer venueRow;

    private Integer venueSeat;
}
