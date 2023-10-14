package com.example.individual_assignment_hristo_ganchev.business.UsersRelated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest {
    private String email;
    private String password;
}
