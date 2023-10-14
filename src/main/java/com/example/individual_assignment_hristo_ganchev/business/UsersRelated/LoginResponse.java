package com.example.individual_assignment_hristo_ganchev.business.UsersRelated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class LoginResponse {
    private long id;
    private String email;
}
