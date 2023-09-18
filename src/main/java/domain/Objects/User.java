package domain.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String email;

    private Long salt;

    private Long hashedPassword;

    private List<Long> orderList;

    private List<Long> orderListExpired;

}
