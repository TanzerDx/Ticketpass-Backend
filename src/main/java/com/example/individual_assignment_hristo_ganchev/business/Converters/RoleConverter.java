package com.example.individual_assignment_hristo_ganchev.business.Converters;

import com.example.individual_assignment_hristo_ganchev.domain.Role;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.RoleEntity;

public class RoleConverter {

    public RoleConverter()
    {
        throw new IllegalStateException("Role converter");
    }

    public static Role convert(RoleEntity role) {

        return  Role.builder()
                .id(role.getId())
                .role(role.getRole())
                .build();

    }

    public static RoleEntity convertToEntity(Role role) {

        return RoleEntity.builder()
                .id(role.getId())
                .role(role.getRole())
                .build();

    }
}
