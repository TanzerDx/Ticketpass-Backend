package domain.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Concert {

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
