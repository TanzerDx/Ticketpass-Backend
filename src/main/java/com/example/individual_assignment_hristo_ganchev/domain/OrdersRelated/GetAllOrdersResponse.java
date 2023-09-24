package com.example.individual_assignment_hristo_ganchev.domain.OrdersRelated;

import com.example.individual_assignment_hristo_ganchev.domain.Objects.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class GetAllOrdersResponse {
    private List<Order> orders;
}
