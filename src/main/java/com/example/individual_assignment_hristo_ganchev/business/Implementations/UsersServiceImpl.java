package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.UserConverter;
import com.example.individual_assignment_hristo_ganchev.business.Interfaces.UsersService;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.LoginRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.LoginResponse;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserResponse;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessTokenEncoder;
import com.example.individual_assignment_hristo_ganchev.security.token.impl.AccessTokenImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AccessTokenEncoder accessTokenEncoder;

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
        User user = UserConverter.convert(userRepository.login(request.getEmail()));

        if (passwordEncoder.matches(request.getPassword(), user.getEncodedPassword())) {

            return LoginResponse.builder()
                    .accessToken(generateAccessToken(user))
                    .build();
        }
        else {
            return null;
        }
    }

    @Override
    public void deleteUser(long id)
    {
        userRepository.deleteById(id);
    }


    private UserEntity saveNewUser(AddUserRequest request)
    {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity user = UserEntity.builder()
                .email(request.getEmail())
                .encodedPassword(encodedPassword)
                .role("user")
                .build();

        return userRepository.save(user);
    }

    private String generateAccessToken(User user) {

        List<String> roles = new ArrayList<>();
        roles.add(user.getRole());

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getEmail(), user.getId(), roles));
    }
}
