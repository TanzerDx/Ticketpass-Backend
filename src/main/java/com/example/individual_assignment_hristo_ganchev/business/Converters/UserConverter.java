package com.example.individual_assignment_hristo_ganchev.business.Converters;

import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;

public final class UserConverter {

    public UserConverter()
    {
        throw new IllegalStateException("User converter");
    }

    public static User convert(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .salt(user.getSalt())
                .hashedPassword(user.getHashedPassword())
                .build();

    }
}
