package persistence.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@Builder
public class UserEntity {

    private Long id;

    private String email;

    private Long salt;

    private Long hashedPassword;

    private List<Long> orderList;

    private List<Long> orderListExpired;

}
