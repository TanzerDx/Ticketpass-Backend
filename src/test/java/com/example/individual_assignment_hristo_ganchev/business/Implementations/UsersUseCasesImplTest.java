package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.UserConverter;
import com.example.individual_assignment_hristo_ganchev.domain.Objects.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersUseCasesImplTest {

    @Test
    public void getUser_shouldGetUserByID() throws Exception {
        // Arrange
            UserRepository userRepositoryMock = mock(UserRepository.class);

            UserEntity toTest = new UserEntity(1L, "hristo@gmail.com", "salt",
                    "hashedPassword", null, null);

            when(userRepositoryMock.getUser(1L)).thenReturn(toTest);

            UsersUseCasesImpl sut = new UsersUseCasesImpl(userRepositoryMock);


        // Act
            User retrievedUser = sut.getUser(1L);


        // Assert
            assertThat(UserConverter.convert(toTest)).isEqualTo(retrievedUser);
    }

}
