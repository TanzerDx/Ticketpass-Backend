package com.example.individual_assignment_hristo_ganchev.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    private Long id;

    private Order order;

    private String QR;

    private String userName;

    private String venueSection;
}
