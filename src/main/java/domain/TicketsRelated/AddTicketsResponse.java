package domain.TicketsRelated;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddTicketsResponse {
    private Long orderId;
    private String artist;
}
