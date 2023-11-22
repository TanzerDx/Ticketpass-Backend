package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.UserConverter;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserRequest;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServiceImplTest {

    @Test
    public void getUser_shouldGetUserByID() throws Exception {
    // Arrange
            UserRepository userRepositoryMock = mock(UserRepository.class);

            UserEntity toReturn = new UserEntity(1L, "hristo@gmail.com", null,
                    "hashedPassword", false);

            User toCompare = new User(1L, "hristo@gmail.com", null,
                    "hashedPassword", false);

            when(userRepositoryMock.getById(1L)).thenReturn(toReturn);

            UsersServiceImpl sut = new UsersServiceImpl(userRepositoryMock);


    // Act
            User retrievedUser = sut.getUserById(1L);


    // Assert
            assertThat(toCompare).isEqualTo(retrievedUser);
    }

    @Test
    public void getUser_shouldThrowNullPointerException() throws Exception {
        // Arrange
        UserRepository userRepositoryMock = mock(UserRepository.class);


        NullPointerException nullPointerException = new NullPointerException();

        when(userRepositoryMock.getById(1L)).thenThrow(nullPointerException);

        UsersServiceImpl sut = new UsersServiceImpl(userRepositoryMock);


        // Act and Assert
        assertThrows(NullPointerException.class, () -> sut.getUserById(1L));
    }

}
