package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.UserConverter;
import com.example.individual_assignment_hristo_ganchev.business.Interfaces.UsersService;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.LoginRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.LoginResponse;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.UserRepository;

import java.util.Optional;

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
    public User getUserById(long id)
    {
        UserEntity userEntity = userRepository.getUserById(id);

        return UserConverter.convert(userEntity);
    }


    @Override
    public LoginResponse Login(LoginRequest request)
    {
        UserEntity userEntity = userRepository.Login(request.getEmail(), request.getPassword());

        User user = UserConverter.convert(userEntity);

        return LoginResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
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
                .hashedPassword(request.getPassword())
                .build();

        return userRepository.add(user);
    }
}
