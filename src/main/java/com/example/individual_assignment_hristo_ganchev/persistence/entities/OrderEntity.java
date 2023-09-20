package com.example.individual_assignment_hristo_ganchev.persistence.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OrderEntity {

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
