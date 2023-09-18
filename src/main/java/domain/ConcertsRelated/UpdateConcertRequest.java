package domain.ConcertsRelated;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateConcertRequest {

    private Long id;

    @NotBlank
    private String artist;

    @NotBlank
    private String musicGenre;

    @NotBlank
    private String venue;

    @NotBlank
    private Date date;

    @NotBlank
    private String city;

    @NotBlank
    private String desc;

    @NotBlank
    private String photoURL;

    @NotBlank
    private Double price;

    @NotBlank
    private Long ticketsRemaining;
}
