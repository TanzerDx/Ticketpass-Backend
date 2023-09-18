package controller;

import business.Interfaces.OrdersUseCases;
import domain.Objects.Order;
import domain.OrdersRelated.CreateOrderRequest;
import domain.OrdersRelated.CreateOrderResponse;
import domain.OrdersRelated.GetAllOrdersResponse;
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
        public ResponseEntity<Order> getOrder(@RequestParam(name = "id", required = false) final long id) {
        final Order order = ordersUseCases.getOrder(id);
                return ResponseEntity.ok().body(order);
                }

}
