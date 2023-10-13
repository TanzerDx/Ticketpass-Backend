package com.example.individual_assignment_hristo_ganchev.business.UsersRelated;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String salt;

    @NotBlank
    private String hashedPassword;

    private List<Long> orderList;

    private List<Long> orderListExpired;
}
