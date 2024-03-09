package com.example.individual_assignment_hristo_ganchev.business.OrdersRelated;

import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.User;
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
    private Concert concert;

    @NotBlank
    private User user;

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
