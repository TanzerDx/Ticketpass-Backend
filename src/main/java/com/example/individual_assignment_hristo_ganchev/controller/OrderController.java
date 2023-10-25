package com.example.individual_assignment_hristo_ganchev.controller;

import com.example.individual_assignment_hristo_ganchev.business.Interfaces.OrdersService;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.CreateOrderRequest;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.CreateOrderResponse;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.GetAllOrdersResponse;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173" , "http://localhost:4173"})
public class OrderController {

        private final OrdersService ordersService;

        @PostMapping()
        public ResponseEntity<CreateOrderResponse> addOrder(@RequestBody CreateOrderRequest request) {
        CreateOrderResponse response = ordersService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        @GetMapping
        public ResponseEntity<GetAllOrdersResponse> getAllOrders(@RequestParam(name = "userId") Long userId) {
            return ResponseEntity.ok(ordersService.getAllOrders(userId));
        }

        @GetMapping("{id}")
        public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {
        final Order order = ordersService.getOrder(id);
                return ResponseEntity.ok().body(order);
        }

}
