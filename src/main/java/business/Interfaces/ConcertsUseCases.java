package business.Interfaces;

import domain.ConcertsRelated.AddConcertRequest;
import domain.ConcertsRelated.AddConcertResponse;
import domain.ConcertsRelated.GetConcertsResponse;
import domain.ConcertsRelated.UpdateConcertRequest;
import domain.Objects.Concert;

public interface ConcertsUseCases {
    AddConcertResponse addConcert(AddConcertRequest request);

    Concert getConcert(long id);

    GetConcertsResponse getAllConcerts();

    void updateConcert(UpdateConcertRequest request);
}
