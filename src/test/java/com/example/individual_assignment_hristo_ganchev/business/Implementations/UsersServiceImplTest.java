package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.UserConverter;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.UserRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServiceImplTest {

    @Test
    public void getUser_shouldGetUserByID() throws Exception {
        // Arrange
            UserRepository userRepositoryMock = mock(UserRepository.class);

            UserEntity toTest = new UserEntity(1L, "hristo@gmail.com", null,
                    "hashedPassword", null, null);

            when(userRepositoryMock.getUserById(1L)).thenReturn(toTest);

            UsersServiceImpl sut = new UsersServiceImpl(userRepositoryMock);


        // Act
            User retrievedUser = sut.getUserById(1L);


        // Assert
            assertThat(UserConverter.convert(toTest)).isEqualTo(retrievedUser);
    }

}
