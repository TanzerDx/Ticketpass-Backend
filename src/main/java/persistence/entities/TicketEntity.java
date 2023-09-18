package persistence.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Optional;

@Data
@Builder
public class TicketEntity {

    private Long id;

    private Long orderId;

    private Long concertId;

    private String QR;

    private String userName;

    private String concertArtist;

    private String concertVenue;

    private Date concertDate;

    private String concertCity;

    private String section;

    private Optional<Integer> row;

    private Optional<String> seat;
}
