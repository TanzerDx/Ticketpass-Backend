package com.example.individual_assignment_hristo_ganchev.business.Converters;

import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;

public final class UserConverter {

    private UserConverter()
    {
        throw new IllegalStateException("User converter");
    }

    public static User convert(UserEntity user) {

        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .encodedPassword(user.getEncodedPassword())
                .role(user.getRole())
                .build();

    }

    public static UserEntity convertToEntity(User user) {

        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .encodedPassword(user.getEncodedPassword())
                .role(user.getRole())
                .build();

    }
}
