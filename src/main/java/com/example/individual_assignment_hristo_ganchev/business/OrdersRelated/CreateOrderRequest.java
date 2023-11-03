package com.example.individual_assignment_hristo_ganchev.business.OrdersRelated;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotBlank
    private Long concertId;

    @NotBlank
    private Long userId;

    @NotBlank
    private String date;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @NotBlank
    private Integer ticketNumber;

    @NotBlank
    private Double orderPrice;

    @NotBlank
    private String paymentMethod;
}
