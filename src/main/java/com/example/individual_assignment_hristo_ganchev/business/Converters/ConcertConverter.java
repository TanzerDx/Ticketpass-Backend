package com.example.individual_assignment_hristo_ganchev.business.Converters;

import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ConcertConverter {

    private ConcertConverter()
    {
        throw new IllegalStateException("Concert converter");
    }

    public static Concert convert(ConcertEntity concert) {
        if(concert == null) return null;

        return Concert.builder()
                .id(concert.getId())
                .artist(concert.getArtist())
                .musicGenre(concert.getMusicGenre())
                .venue(concert.getVenue())
                .date(concert.getDate())
                .city(concert.getCity())
                .description(concert.getDescription())
                .photoURL(concert.getPhotoURL())
                .price(concert.getPrice())
                .ticketsRemaining(concert.getTicketsRemaining())
                .build();
    }

    public static ConcertEntity convertToEntity(Concert concert) {
        if(concert == null) return null;

        return ConcertEntity.builder()
                .id(concert.getId())
                .artist(concert.getArtist())
                .musicGenre(concert.getMusicGenre())
                .venue(concert.getVenue())
                .date(concert.getDate())
                .city(concert.getCity())
                .description(concert.getDescription())
                .photoURL(concert.getPhotoURL())
                .price(concert.getPrice())
                .ticketsRemaining(concert.getTicketsRemaining())
                .build();
    }
}
