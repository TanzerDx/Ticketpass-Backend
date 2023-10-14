package com.example.individual_assignment_hristo_ganchev.persistence.interfaces;

import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;

public interface UserRepository {
    UserEntity add(UserEntity user);

    UserEntity getUserById(Long id);

    UserEntity Login(String email, String password);

    void deleteUser(Long id);

}
