package com.example.individual_assignment_hristo_ganchev.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "concerts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConcertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Length(min = 1, max = 20)
    private String artist;

    @NotEmpty
    @Length(min = 1, max = 20)
    private String musicGenre;

    @NotEmpty
    @Length(min = 1, max = 20)
    private String venue;

    @NotNull
    private Date date;

    @NotEmpty
    private String city;

    @NotEmpty
    @Length(min = 50, max = 250)
    private String description;

    @NotEmpty
    private String photoURL;

    @NotNull
    private Double price;

    @NotNull
    private Integer ticketsRemaining;

    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL)
    private List<OrderEntity> orders;
}
