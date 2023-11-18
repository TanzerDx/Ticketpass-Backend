package com.example.individual_assignment_hristo_ganchev.persistence.jpa;

import com.example.individual_assignment_hristo_ganchev.persistence.entities.RoleEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query("select r from RoleEntity r where role = ?1")
    RoleEntity selectByRole(String role);

}
