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

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long concertId;

    @NotNull
    private Long userId;

    @NotNull
    private Date date;

    @NotEmpty
    @Length(min = 1, max = 20)
    private String name;

    @NotEmpty
    @Length(min = 1, max = 20)
    private String surname;

    @NotEmpty
    @Length(min = 1, max = 50)
    private String address;

    @NotEmpty
    @Length(min = 1, max = 15)
    private String phone;

    @NotNull
    private Integer ticketNumber;

    @NotNull
    private Double orderPrice;

    @NotEmpty
    private String paymentMethod;

}
