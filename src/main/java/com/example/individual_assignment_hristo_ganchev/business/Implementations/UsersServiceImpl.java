package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.UserConverter;
import com.example.individual_assignment_hristo_ganchev.business.Interfaces.UsersService;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.UserRepository;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
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
