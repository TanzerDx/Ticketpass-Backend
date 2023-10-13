package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.ConcertConverter;
import com.example.individual_assignment_hristo_ganchev.business.Interfaces.ConcertsService;
import com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated.AddConcertRequest;
import com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated.AddConcertResponse;
import com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated.UpdateConcertRequest;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.ConcertRepository;

import java.text.SimpleDateFormat;
import java.util.List;


@Service
@AllArgsConstructor
public class ConcertsServiceImpl implements ConcertsService {

    private final ConcertRepository concertRepository;

    @Override
    public AddConcertResponse addConcert(AddConcertRequest request) {

        ConcertEntity savedConcert = saveNewConcert(request);

        return AddConcertResponse.builder()
                .id(savedConcert.getId())
                .build();
    }

    @Override
    public Concert getConcert(Long id){

        ConcertEntity concertEntity = concertRepository.getConcert(id);

        Concert concert = ConcertConverter.convert(concertEntity);

        return concert;
    }


    @Override
    public List<Concert> getAllConcerts(){
        List<Concert> concerts = concertRepository.getAll()
                .stream()
                .map(ConcertConverter::convert)
                .toList();

        return concerts;
    }

    @Override
    public void updateConcert(UpdateConcertRequest request){
        ConcertEntity concertEntity = concertRepository.getConcert(request.getId());

        updateFields(request, concertEntity);
    }


    private void updateFields(UpdateConcertRequest request, ConcertEntity concert) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        try
        {
            concert.setArtist(request.getArtist());
            concert.setMusicGenre(request.getMusicGenre());
            concert.setVenue(request.getVenue());
            concert.setDate(sdf.parse(String.valueOf(request.getDate())));
            concert.setCity(request.getCity());
            concert.setDesc(request.getDesc());
            concert.setPhotoURL(request.getPhotoURL());
            concert.setPrice(request.getPrice());
            concert.setTicketsRemaining(request.getTicketsRemaining());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private ConcertEntity saveNewConcert(AddConcertRequest request) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        ConcertEntity newConcert = null;
        try
        {
            newConcert = ConcertEntity.builder()
                    .artist(request.getArtist())
                    .musicGenre(request.getMusicGenre())
                    .venue(request.getVenue())
                    .date(sdf.parse(String.valueOf(request.getDate())))
                    .city(request.getCity())
                    .desc(request.getDesc())
                    .photoURL(request.getPhotoURL())
                    .price(request.getPrice())
                    .ticketsRemaining(request.getTicketsRemaining())
                    .build();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        return concertRepository.addConcert(newConcert);
    }
}
