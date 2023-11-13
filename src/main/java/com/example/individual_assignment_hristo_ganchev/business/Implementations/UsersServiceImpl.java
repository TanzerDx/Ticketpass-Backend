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
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.UserRepository;

import java.util.ArrayList;
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
        UserEntity userEntity = userRepository.getById(id);

        return UserConverter.convert(userEntity);
    }


    @Override
    public LoginResponse Login(LoginRequest request)
    {
        User user = UserConverter.convert(userRepository.login(request.getEmail(), request.getPassword()));

        return LoginResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    @Override
    public void deleteUser(long id)
    {
        userRepository.deleteById(id);
    }


    private UserEntity saveNewUser(AddUserRequest request)
    {
        UserEntity user = UserEntity.builder()
                .email(request.getEmail())
                .salt("salt")
                .hashedPassword(request.getHashedPassword())
                .isAdmin(false)
                .build();

        return userRepository.save(user);
    }
}
