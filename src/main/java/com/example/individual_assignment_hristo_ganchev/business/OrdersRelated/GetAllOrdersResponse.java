package com.example.individual_assignment_hristo_ganchev.business.OrdersRelated;

import com.example.individual_assignment_hristo_ganchev.domain.Order;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllOrdersResponse {
    private List<Order> orders;
}
