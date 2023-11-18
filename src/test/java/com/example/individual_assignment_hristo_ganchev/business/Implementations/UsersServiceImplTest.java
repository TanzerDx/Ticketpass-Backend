package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.UserConverter;
import com.example.individual_assignment_hristo_ganchev.domain.Role;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.RoleEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.UserRepository;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessTokenEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServiceImplTest {

    @Test
    public void getUser_shouldGetUserByID() throws Exception {
    // Arrange
            UserRepository userRepositoryMock = mock(UserRepository.class);
            PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
            AccessTokenEncoder accessTokenEncoder = mock(AccessTokenEncoder.class);

            RoleEntity roleEntity = new RoleEntity(1L, "user");
            Role role = new Role(1L, "user");

            UserEntity toReturn = new UserEntity(1L, "hristo@gmail.com",
                    "hashedPassword", Arrays.asList(roleEntity));

            User toCompare = new User(1L, "hristo@gmail.com",
                    "hashedPassword", Arrays.asList(role));

            when(userRepositoryMock.getById(1L)).thenReturn(toReturn);

            UsersServiceImpl sut = new UsersServiceImpl(userRepositoryMock, passwordEncoder, accessTokenEncoder);


    // Act
            User retrievedUser = sut.getUserById(1L);


    // Assert
            assertThat(toCompare).isEqualTo(retrievedUser);
    }

}
