package com.example.individual_assignment_hristo_ganchev.business.converters;

import com.example.individual_assignment_hristo_ganchev.business.Converters.UserConverter;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserConverterTests {

    @Test
    public void convert_shouldConvertTheUserEntity_toUser() throws Exception
    {
        //Arrange
        UserConverter userConverterMock = mock(UserConverter.class);

        UserEntity toConvert = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        User toCompare = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");



        //Act
        User returnedUser = userConverterMock.convert(toConvert);



        //Assert
        assertThat(toCompare).isEqualTo(returnedUser);
    }

    @Test
    public void convertToEntity_shouldConvertTheUser_toUserEntity() throws Exception
    {
        //Arrange
        UserConverter userConverterMock = mock(UserConverter.class);

        User toConvert = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        UserEntity toCompare = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");



        //Act
        UserEntity returnedUser = userConverterMock.convertToEntity(toConvert);



        //Assert
        assertThat(toCompare).isEqualTo(returnedUser);
    }

//    @Test
//    public void convert_shouldThrowIllegalStateException() throws Exception
//    {
//        //Arrange
//        UserConverter userConverterMock = mock(UserConverter.class);
//
//        UserEntity toConvert = new UserEntity(1L, "hristo@gmail.com",
//                "hashedPassword", "user");
//
//        User toCompare = new User(1L, "hristo@gmail.com",
//                "hashedPassword", "user");
//
//        IllegalStateException illegalStateException = new IllegalStateException();
//
//        when(userConverterMock.convert(toConvert)).thenThrow(illegalStateException);
//
//
//        //Act and Assert
//        assertThrows(IllegalStateException.class, () -> userConverterMock.convert(toConvert));
//    }

}
