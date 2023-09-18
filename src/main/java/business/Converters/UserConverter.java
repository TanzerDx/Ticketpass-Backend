package business.Converters;

import domain.Objects.User;
import persistence.entities.UserEntity;

import java.util.List;

public final class UserConverter {

    public static User convert(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .salt(user.getSalt())
                .hashedPassword(user.getHashedPassword())
                .orderList(user.getOrderList())
                .orderListExpired(user.getOrderListExpired())
                .build();

    }
}
