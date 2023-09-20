package com.example.individual_assignment_hristo_ganchev.persistence.implementations;

import org.springframework.stereotype.Repository;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static long NEXT_ID = 1;

    private final List<UserEntity> savedUsers;

    public UserRepositoryImpl() {
        this.savedUsers = new ArrayList<>();
    }

    @Override
    public UserEntity add(UserEntity user)
    {
        user.setId(NEXT_ID);
        NEXT_ID++;
        this.savedUsers.add(user);
        return user;
    }

    @Override
    public UserEntity getUser(Long id)
    {
        return savedUsers.stream()
                .filter(userEntity -> userEntity.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteUser(Long id)
    {
        this.savedUsers.removeIf(userEntity -> userEntity.getId().equals(id));
    }

}
