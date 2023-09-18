package domain.TicketsRelated;

import domain.Objects.Ticket;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetTicketsResponse {
    private List<Ticket> tickets;
}
