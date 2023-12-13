package com.example.individual_assignment_hristo_ganchev.business.Interfaces;

import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.LoginRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.LoginResponse;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserResponse;

public interface UsersService {
    AddUserResponse addUser(AddUserRequest request, String passedRole);

    User getUserById(long id);

    LoginResponse Login(LoginRequest request);

    void deleteAdmin(long id);

    User banUser(long id);

    User unbanUser(long id);

    User getUserByAccessToken(String passedAccessToken);
}
