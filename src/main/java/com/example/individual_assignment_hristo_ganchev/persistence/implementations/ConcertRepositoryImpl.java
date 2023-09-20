package com.example.individual_assignment_hristo_ganchev.persistence.implementations;

import org.springframework.stereotype.Repository;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.ConcertRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ConcertRepositoryImpl implements ConcertRepository {
    private static long NEXT_ID = 1;

    private final List<ConcertEntity> savedConcerts;

    public ConcertRepositoryImpl(){
        this.savedConcerts = new ArrayList<>();
    }

    @Override
    public ConcertEntity addConcert(ConcertEntity concert)
    {
        concert.setId(NEXT_ID);
        NEXT_ID++;
        this.savedConcerts.add(concert);
        return concert;
    }

    @Override
    public List<ConcertEntity> getAll()
    {
        return Collections.unmodifiableList(this.savedConcerts);
    }

    @Override
    public List<ConcertEntity> getByGenre(String genre)
    {
        return this.savedConcerts.stream()
                .filter(concertEntity -> concertEntity.getMusicGenre().equals(genre))
                .toList();
    }

    @Override
    public List<ConcertEntity> getByArtist(String artist)
    {
        return this.savedConcerts.stream()
                .filter(concertEntity -> concertEntity.getArtist().equals(artist))
                .toList();
    }

    @Override
    public List<ConcertEntity> getByCity(String city)
    {
        return this.savedConcerts.stream()
                .filter(concertEntity -> concertEntity.getCity().equals(city))
                .toList();
    }

    @Override
    public ConcertEntity getConcert(Long id)
    {
        return this.savedConcerts.stream()
                .filter(concertEntity -> concertEntity.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
