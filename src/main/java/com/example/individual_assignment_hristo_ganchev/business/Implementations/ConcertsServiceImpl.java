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
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.ConcertRepository;

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
        return ConcertConverter.convert(concertRepository.getById(id));
    }


    @Override
    public List<Concert> getAllConcerts(){
        return concertRepository.findAll()
                .stream()
                .map(ConcertConverter::convert)
                .toList();
    }

    @Override
    public void updateConcert(UpdateConcertRequest request) {
        ConcertEntity concert = concertRepository.getById(request.getId());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try
        {
            concert.setArtist(request.getArtist());
            concert.setMusicGenre(request.getMusicGenre());
            concert.setVenue(request.getVenue());
            concert.setDate(sdf.parse(String.valueOf(request.getDate())));
            concert.setCity(request.getCity());
            concert.setDescription(request.getDescription());
            concert.setPhotoURL(request.getPhotoURL());
            concert.setPrice(request.getPrice());
            concert.setTicketsRemaining(request.getTicketsRemaining());

            concertRepository.save(concert);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    protected ConcertEntity saveNewConcert(AddConcertRequest request) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");

        ConcertEntity newConcert = null;
        try
        {
            newConcert = ConcertEntity.builder()
                    .artist(request.getArtist())
                    .musicGenre(request.getMusicGenre())
                    .venue(request.getVenue())
                    .date(sdf.parse(String.valueOf(request.getDate())))
                    .city(request.getCity())
                    .description(request.getDescription())
                    .photoURL(request.getPhotoURL())
                    .price(request.getPrice())
                    .ticketsRemaining(request.getTicketsRemaining())
                    .build();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        return concertRepository.save(newConcert);
    }
}
