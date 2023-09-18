package domain.ConcertsRelated;

import domain.Objects.Concert;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetConcertsResponse {
    private List<Concert> concerts;
}
