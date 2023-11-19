package com.example.individual_assignment_hristo_ganchev.controller;

import com.example.individual_assignment_hristo_ganchev.business.Interfaces.RolesService;
import com.example.individual_assignment_hristo_ganchev.business.Interfaces.TicketsService;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.CreateOrderRequest;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.CreateOrderResponse;
import com.example.individual_assignment_hristo_ganchev.business.RolesRelated.CreateRoleRequest;
import com.example.individual_assignment_hristo_ganchev.business.RolesRelated.CreateRoleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173" , "http://localhost:4173"})
public class RoleController {

    private final RolesService rolesService;

    @PostMapping()
    public ResponseEntity<CreateRoleResponse> addRole(@RequestBody CreateRoleRequest request) {
        CreateRoleResponse response = rolesService.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
