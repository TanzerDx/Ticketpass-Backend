package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.UserConverter;
import com.example.individual_assignment_hristo_ganchev.business.Interfaces.RolesService;
import com.example.individual_assignment_hristo_ganchev.business.RolesRelated.CreateRoleRequest;
import com.example.individual_assignment_hristo_ganchev.business.RolesRelated.CreateRoleResponse;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.RoleEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RoleRepository roleRepository;

    @Override
    public CreateRoleResponse createRole(CreateRoleRequest request){
        RoleEntity savedRole = saveNewRole(request);

        return CreateRoleResponse.builder()
                .id(savedRole.getId())
                .build();
    }


    private RoleEntity saveNewRole(CreateRoleRequest request) {

        RoleEntity role = null;

            role = RoleEntity.builder()
                    .role(request.getRole())
                    .user(UserConverter.convertToEntity((request.getUser())))
                    .build();

        return roleRepository.save(role);
    }

}
