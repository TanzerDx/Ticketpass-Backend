package com.example.individual_assignment_hristo_ganchev.business.Interfaces;

import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserResponse;

public interface UsersService {
    AddUserResponse addUser(AddUserRequest request);

    User getUser(long id);

    void deleteUser(long id);
}
