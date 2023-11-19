package com.example.individual_assignment_hristo_ganchev.business.RolesRelated;

import com.example.individual_assignment_hristo_ganchev.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleRequest {

    @NotBlank
    private User user;

    @NotBlank
    private String role;

}
