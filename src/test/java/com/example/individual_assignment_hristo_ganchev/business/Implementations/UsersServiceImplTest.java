package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.UserRepository;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessToken;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessTokenEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServiceImplTest {

    @Test
    public void getUser_shouldGetUserByID() throws Exception {
    // Arrange
            UserRepository userRepositoryMock = mock(UserRepository.class);
            PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
            AccessTokenEncoder accessTokenEncoder = mock(AccessTokenEncoder.class);
        AccessToken accessToken = mock(AccessToken.class);


            UserEntity toReturn = new UserEntity(1L, "hristo@gmail.com",
                    "hashedPassword", "user");

            User toCompare = new User(1L, "hristo@gmail.com",
                    "hashedPassword", "user");

        when(accessToken.getUserId()).thenReturn(1l);
            when(userRepositoryMock.getById(1L)).thenReturn(toReturn);

            UsersServiceImpl sut = new UsersServiceImpl(userRepositoryMock, passwordEncoder, accessTokenEncoder, accessToken);


    // Act
            User retrievedUser = sut.getUserById(1L);


    // Assert
            assertThat(toCompare).isEqualTo(retrievedUser);
    }

    @Test
    public void getUser_shouldThrowNullPointerException() throws Exception {
        // Arrange
        UserRepository userRepositoryMock = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        AccessTokenEncoder accessTokenEncoder = mock(AccessTokenEncoder.class);
        AccessToken accessToken = mock(AccessToken.class);

        NullPointerException nullPointerException = new NullPointerException();

        when(accessToken.getUserId()).thenReturn(1l);
        when(userRepositoryMock.getById(1L)).thenThrow(nullPointerException);

        UsersServiceImpl sut = new UsersServiceImpl(userRepositoryMock, passwordEncoder, accessTokenEncoder, accessToken);


        // Act and Assert
        assertThrows(NullPointerException.class, () -> sut.getUserById(1L));
    }

    @Test
    public void getUser_shouldThrowAccessDeniedException_whenAccessTokenUserIDisNotEqualToPassedUserID() throws Exception {
        // Arrange
        UserRepository userRepositoryMock = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        AccessTokenEncoder accessTokenEncoder = mock(AccessTokenEncoder.class);
        AccessToken accessToken = mock(AccessToken.class);


        UserEntity toReturn = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");


        when(accessToken.getUserId()).thenReturn(2L);
        when(userRepositoryMock.getById(1L)).thenReturn(toReturn);

        UsersServiceImpl sut = new UsersServiceImpl(userRepositoryMock, passwordEncoder, accessTokenEncoder, accessToken);


        // Act and Assert
        assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> sut.getUserById(1L));
    }

}
