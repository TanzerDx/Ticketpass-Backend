package business.Implementations;

import business.Converters.UserConverter;
import business.Interfaces.UsersUseCases;
import domain.Objects.User;
import domain.UsersRelated.AddUserRequest;
import domain.UsersRelated.AddUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import persistence.entities.UserEntity;
import persistence.interfaces.UserRepository;

@Service
@AllArgsConstructor
public class UsersUseCasesImpl implements UsersUseCases {
    private final UserRepository userRepository;

    @Override
    public AddUserResponse addUser(AddUserRequest request)
    {
        UserEntity savedUser = saveNewUser(request);

        return AddUserResponse.builder()
                .id(savedUser.getId())
                .build();
    }

    @Override
    public User getUser(long id)
    {
        UserEntity userEntity = userRepository.getUser(id);

        User user = UserConverter.convert(userEntity);

        return user;
    }

    @Override
    public void deleteUser(long id)
    {
        userRepository.deleteUser(id);
    }


    private UserEntity saveNewUser(AddUserRequest request)
    {
        UserEntity user = UserEntity.builder()
                .email(request.getEmail())
                .salt(request.getSalt())
                .hashedPassword(request.getHashedPassword())
                .orderList(request.getOrderList())
                .orderListExpired(request.getOrderListExpired())
                .build();

        return userRepository.add(user);
    }
}
