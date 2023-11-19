package com.example.individual_assignment_hristo_ganchev.business.Converters;

import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class UserConverter {

    public UserConverter()
    {
        throw new IllegalStateException("User converter");
    }

    public static User convert(UserEntity user) {

        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .encodedPassword(user.getEncodedPassword())
                .build();

    }

    public static UserEntity convertToEntity(User user) {

        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .encodedPassword(user.getEncodedPassword())
                .build();

    }
}
