package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.OrderConverter;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.GetAllOrdersResponse;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.OrderRepository;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrdersServiceImplTest {

    @Test
    void getAllOrders_shouldReturnOrdersIfPresent() throws Exception {

        //Arrange
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            OrderRepository orderRepository = mock(OrderRepository.class);

            List<OrderEntity> allOrders = Arrays.asList(new OrderEntity(1L, 1L, 1L, sdf.parse("2023/09/20"), "Hristo",
                    "Ganchev", "Woenselse Markt 18", "0612345678", 3, 94.49, "iDeal" ));

            when(orderRepository.getAll(1l)).thenReturn(allOrders);

            OrdersServiceImpl sut = new OrdersServiceImpl(orderRepository);



        // Act
            GetAllOrdersResponse sutResponse = sut.getAllOrders(1L);



        // Assert
            assertThat(sutResponse.getOrders()).isNotEmpty();
    }



    @Test
    void getAllOrders_shouldReturnAnEmptyListIfOrdersAreNotPresent() {

        //Arrange
            OrderRepository orderRepository = mock(OrderRepository.class);

            when(orderRepository.getAll(1l)).thenReturn(new ArrayList<>());

            OrdersServiceImpl sut = new OrdersServiceImpl(orderRepository);



        // Act
            GetAllOrdersResponse sutResponse = sut.getAllOrders(1L);



        // Assert
            assertThat(sutResponse.getOrders()).isEmpty();
    }



    @Test
    void getOrder_shouldGetOrderById() throws Exception {

        //Arrange
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            OrderRepository orderRepository = mock(OrderRepository.class);

            OrderEntity toTest = new OrderEntity(1L, 1L, 1L, sdf.parse("2023/09/20"), "Hristo",
                    "Ganchev", "Woenselse Markt 18", "0612345678", 3, 94.49, "iDeal" );

            when(orderRepository.getOrder(1l)).thenReturn(toTest);

            OrdersServiceImpl sut = new OrdersServiceImpl(orderRepository);



        // Act
            Order retrievedOrder = sut.getOrder(1L);



        // Assert
            assertThat(OrderConverter.convert(toTest)).isEqualTo(retrievedOrder);
    }
}