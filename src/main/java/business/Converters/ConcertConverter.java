package business.Converters;

import domain.Objects.Concert;
import persistence.entities.ConcertEntity;

import java.util.Optional;

public final class ConcertConverter {

    public static Concert convert(ConcertEntity concert) {
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
