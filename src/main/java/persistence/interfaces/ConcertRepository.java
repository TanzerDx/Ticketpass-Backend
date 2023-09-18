package persistence.interfaces;

import persistence.entities.ConcertEntity;

import java.util.List;
import java.util.Optional;

public interface ConcertRepository {
    ConcertEntity addConcert(ConcertEntity concert);

    List<ConcertEntity> getAll();

    List<ConcertEntity> getByGenre(String genre);

    List<ConcertEntity> getByArtist(String artist);

    List<ConcertEntity> getByCity(String city);

    ConcertEntity getConcert(long id);
}
