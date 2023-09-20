package com.example.individual_assignment_hristo_ganchev.domain.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;

    private Long concertId;

    private Long userId;

    private Date date;

    private String name;

    private String surname;

    private String address;

    private String phone;

    private Integer ticketNumber;

    private Double orderPrice;

    private String paymentMethod;
}
