package com.example.individual_assignment_hristo_ganchev.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


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

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @NotEmpty
    private String QR;

    @NotEmpty
    @Length(min = 2, max = 50)
    private String userName;

    @NotEmpty
    private String venueSection;

    private Integer venueRow;

    private Integer venueSeat;
}
