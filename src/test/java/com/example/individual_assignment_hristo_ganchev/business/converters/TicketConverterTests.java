package com.example.individual_assignment_hristo_ganchev.business.converters;

import com.example.individual_assignment_hristo_ganchev.business.Converters.TicketConverter;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.domain.Ticket;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.TicketEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class TicketConverterTests {

    @Test
    public void convert_shouldConvertTheTicketEntity_toTicket() throws Exception
    {
        // Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        TicketConverter ticketConverterMock = mock(TicketConverter.class);

        ConcertEntity concertEntity = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        UserEntity userEntity = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        OrderEntity orderEntity = new OrderEntity(1L,  concertEntity, userEntity, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");



        Concert concert= new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        Order order = new Order(1L,  concert, user, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");


        List<TicketEntity> toConvert = Arrays.asList(new TicketEntity(1L, orderEntity ,
                "QR", "Hristo Ganchev", "Standing", null, null));

        List<Ticket> toCompare = Arrays.asList(new Ticket(1L, order ,
                "QR", "Hristo Ganchev", "Standing", null, null));



        //Act
        List<Ticket> returnedTickets = toConvert
                .stream()
                .map(TicketConverter::convert)
                .toList();


        //Assert
        assertThat(toCompare).isEqualTo(returnedTickets);

    }

    @Test
    public void convertToEntity_shouldConvertTheTicket_toTicketEntity() throws Exception
    {
        // Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        TicketConverter ticketConverterMock = mock(TicketConverter.class);

        ConcertEntity concertEntity = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        UserEntity userEntity = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        OrderEntity orderEntity = new OrderEntity(1L,  concertEntity, userEntity, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");



        Concert concert= new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        Order order = new Order(1L,  concert, user, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");


        List<Ticket> toConvert = Arrays.asList(new Ticket(1L, order ,
                "QR", "Hristo Ganchev", "Standing", null, null));

        List<TicketEntity> toCompare = Arrays.asList(new TicketEntity(1L, orderEntity ,
                "QR", "Hristo Ganchev", "Standing", null, null));



        //Act
        List<TicketEntity> returnedTickets = toConvert
                .stream()
                .map(TicketConverter::convertToEntity)
                .toList();


        //Assert
        assertThat(toCompare).isEqualTo(returnedTickets);

    }

}
