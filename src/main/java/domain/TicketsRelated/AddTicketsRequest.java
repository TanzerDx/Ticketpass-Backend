package domain.TicketsRelated;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddTicketsRequest {

    private Long orderId;

    private Long concertId;

    @NotBlank
    private String QR;

    @NotBlank
    private String userName;

    @NotBlank
    private String concertArtist;

    @NotBlank
    private String concertVenue;

    @NotBlank
    private Date concertDate;

    @NotBlank
    private String concertCity;

    @NotBlank
    private String section;

    private Optional<Integer> row;

    private Optional<String> seat;
}
