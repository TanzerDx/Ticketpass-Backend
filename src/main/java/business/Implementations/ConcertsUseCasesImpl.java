package business.Implementations;

import business.Converters.ConcertConverter;
import business.Interfaces.ConcertsUseCases;
import domain.ConcertsRelated.AddConcertRequest;
import domain.ConcertsRelated.AddConcertResponse;
import domain.ConcertsRelated.GetConcertsResponse;
import domain.ConcertsRelated.UpdateConcertRequest;
import domain.Objects.Concert;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import persistence.entities.ConcertEntity;
import persistence.interfaces.ConcertRepository;
import java.util.List;


@Service
@AllArgsConstructor
public class ConcertsUseCasesImpl implements ConcertsUseCases {

    private final ConcertRepository concertRepository;

    @Override
    public AddConcertResponse addConcert(AddConcertRequest request) {

        ConcertEntity savedConcert = saveNewConcert(request);

        return AddConcertResponse.builder()
                .id(savedConcert.getId())
                .build();
    }

    @Override
    public Concert getConcert(long id){

        ConcertEntity concertEntity = concertRepository.getConcert(id);

        Concert concert = ConcertConverter.convert(concertEntity);

        return concert;
    }

    @Override
    public GetConcertsResponse getAllConcerts(){
        List<Concert> concerts = concertRepository.getAll()
                .stream()
                .map(ConcertConverter::convert)
                .toList();

        return GetConcertsResponse.builder()
                .concerts(concerts)
                .build();
    }

    @Override
    public void updateConcert(UpdateConcertRequest request){
        ConcertEntity concertEntity = concertRepository.getConcert(request.getId());

        updateFields(request, concertEntity);
    }


    private void updateFields(UpdateConcertRequest request, ConcertEntity concert) {
        concert.setArtist(request.getArtist());
        concert.setMusicGenre(request.getMusicGenre());
        concert.setVenue(request.getVenue());
        concert.setDate(request.getDate());
        concert.setCity(request.getCity());
        concert.setDesc(request.getDesc());
        concert.setPhotoURL(request.getPhotoURL());
        concert.setPrice(request.getPrice());
        concert.setTicketsRemaining(request.getTicketsRemaining());

    }

    private ConcertEntity saveNewConcert(AddConcertRequest request) {
        ConcertEntity newConcert = ConcertEntity.builder()
                .artist(request.getArtist())
                .musicGenre(request.getMusicGenre())
                .venue(request.getVenue())
                .date(request.getDate())
                .city(request.getCity())
                .desc(request.getDesc())
                .photoURL(request.getPhotoURL())
                .price(request.getPrice())
                .ticketsRemaining(request.getTicketsRemaining())
                .build();

        return concertRepository.addConcert(newConcert);
    }
}
