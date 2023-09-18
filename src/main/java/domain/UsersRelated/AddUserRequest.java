package domain.UsersRelated;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequest {

    @NotBlank
    private String email;

    @NotBlank
    private Long salt;

    @NotBlank
    private Long hashedPassword;

    private List<Long> orderList;

    private List<Long> orderListExpired;
}
