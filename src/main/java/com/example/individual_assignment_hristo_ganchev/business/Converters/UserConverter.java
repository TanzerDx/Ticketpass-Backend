package com.example.individual_assignment_hristo_ganchev.business.Converters;

import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;

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
                .salt(user.getSalt())
                .hashedPassword(user.getHashedPassword())
                .isAdmin(user.getIsAdmin())
                .upcomingConcerts(user.getUpcomingConcerts()
                        .stream()
                        .map(OrderConverter::convert)
                        .toList())
                .expiredConcerts(user.getExpiredConcerts()
                        .stream()
                        .map(OrderConverter::convert)
                        .toList())
                .build();

    }

    public static UserEntity convertToEntity(User user) {

        List<Order> upcomingConcerts = user.getUpcomingConcerts();
        List<Order> expiredConcerts = user.getExpiredConcerts();

        if (upcomingConcerts == null)
        {
            upcomingConcerts = Collections.emptyList();
        }

        if (expiredConcerts == null)
        {
            expiredConcerts = Collections.emptyList();
        }

        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .salt(user.getSalt())
                .hashedPassword(user.getHashedPassword())
                .isAdmin(user.getIsAdmin())
                .upcomingConcerts(upcomingConcerts
                        .stream()
                        .map(OrderConverter::convertToEntity)
                        .toList())
                .expiredConcerts(expiredConcerts
                        .stream()
                        .map(OrderConverter::convertToEntity)
                        .toList())
                .build();

    }
}
