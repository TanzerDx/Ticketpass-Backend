package com.example.individual_assignment_hristo_ganchev.controller;

import com.example.individual_assignment_hristo_ganchev.business.Interfaces.OrdersUseCases;
import com.example.individual_assignment_hristo_ganchev.domain.Objects.Order;
import com.example.individual_assignment_hristo_ganchev.domain.OrdersRelated.CreateOrderRequest;
import com.example.individual_assignment_hristo_ganchev.domain.OrdersRelated.CreateOrderResponse;
import com.example.individual_assignment_hristo_ganchev.domain.OrdersRelated.GetAllOrdersResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

        private final OrdersUseCases ordersUseCases;

        @PostMapping()
        public ResponseEntity<CreateOrderResponse> addOrder(@RequestBody CreateOrderRequest request) {
        CreateOrderResponse response = ordersUseCases.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        @GetMapping
        public ResponseEntity<GetAllOrdersResponse> getAllOrders(@RequestParam(name = "userId") Long userId) {
            return ResponseEntity.ok(ordersUseCases.getAllOrders(userId));
        }



        @GetMapping("{id}")
        public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {
        final Order order = ordersUseCases.getOrder(id);
                return ResponseEntity.ok().body(order);
        }

}
