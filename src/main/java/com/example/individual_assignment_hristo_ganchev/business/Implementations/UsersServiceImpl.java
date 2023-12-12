package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.UserConverter;
import com.example.individual_assignment_hristo_ganchev.business.Interfaces.UsersService;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.LoginRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.LoginResponse;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserResponse;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessToken;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessTokenDecoder;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessTokenEncoder;
import com.example.individual_assignment_hristo_ganchev.security.token.impl.AccessTokenImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
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

    private final AccessTokenDecoder accessTokenDecoder;

    private AccessToken requestAccessToken;

    @Override
    public AddUserResponse addUser(AddUserRequest request, String passedRole)
    {
        UserEntity savedUser = saveNewUser(request, passedRole);

        return AddUserResponse.builder()
                .id(savedUser.getId())
                .build();
    }

    @Override
    public User getUserById(long id)
    {
        if (!requestAccessToken.getUserId().equals(id))
        {
            throw new AccessDeniedException("Unauthorized access");
        }

        return UserConverter.convert(userRepository.getById(id));
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
            throw new BadCredentialsException("Incorrect password");
        }
    }

    @Override
    public User banUser(long id)
    {
        UserEntity u = userRepository.getById(id);

        u.setRole("banned");

        userRepository.save(u);

        return UserConverter.convert(u);
    }

    @Override
    public void deleteAdmin(long id)
    {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByAccessToken(String passedAccessToken)
    {
        AccessToken accessToken  = accessTokenDecoder.decode(passedAccessToken);

        return UserConverter.convert(userRepository.getById(accessToken.getUserId()));
    }

    protected UserEntity saveNewUser(AddUserRequest request, String passedRole)
    {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity user = UserEntity.builder()
                .email(request.getEmail())
                .encodedPassword(encodedPassword)
                .role(passedRole)
                .build();

        userRepository.save(user);

        return user;
    }


    protected String generateAccessToken(User user) {

        List<String> roles = new ArrayList<>();
        roles.add(user.getRole());

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getEmail(), user.getId(), roles));
    }
}
