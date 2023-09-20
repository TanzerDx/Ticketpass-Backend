package com.example.individual_assignment_hristo_ganchev.business.Converters;

import com.example.individual_assignment_hristo_ganchev.domain.Objects.Concert;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;

public final class ConcertConverter {

    public static Concert convert(ConcertEntity concert) {
        if(concert == null) return null;

        return Concert.builder()
                .id(concert.getId())
                .artist(concert.getArtist())
                .musicGenre(concert.getMusicGenre())
                .venue(concert.getVenue())
                .date(concert.getDate())
                .city(concert.getCity())
                .desc(concert.getDesc())
                .photoURL(concert.getPhotoURL())
                .price(concert.getPrice())
                .ticketsRemaining(concert.getTicketsRemaining())
                .build();
    }
}
