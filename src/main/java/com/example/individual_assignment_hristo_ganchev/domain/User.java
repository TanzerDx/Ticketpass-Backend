package com.example.individual_assignment_hristo_ganchev.domain;

import com.example.individual_assignment_hristo_ganchev.persistence.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String email;

    private String encodedPassword;

}
