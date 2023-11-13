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
public class Order {

    private Long id;

    private Concert concert;

    private User user;

    private Date date;

    private String name;

    private String surname;

    private String address;

    private String phone;

    private Integer ticketNumber;

    private Double orderPrice;

    private String paymentMethod;
}
