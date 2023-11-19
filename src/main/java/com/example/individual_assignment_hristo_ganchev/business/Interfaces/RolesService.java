package com.example.individual_assignment_hristo_ganchev.business.Interfaces;

import com.example.individual_assignment_hristo_ganchev.business.RolesRelated.CreateRoleRequest;
import com.example.individual_assignment_hristo_ganchev.business.RolesRelated.CreateRoleResponse;

public interface RolesService {
    CreateRoleResponse createRole(CreateRoleRequest request);
}
