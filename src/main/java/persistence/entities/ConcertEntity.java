package persistence.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ConcertEntity {

    private Long id;

    private String artist;

    private String musicGenre;

    private String venue;

    private Date date;

    private String city;

    private String desc;

    private String photoURL;

    private Double price;

    private Long ticketsRemaining;
}
