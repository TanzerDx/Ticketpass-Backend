package com.example.individual_assignment_hristo_ganchev.persistence.jpa;

import com.example.individual_assignment_hristo_ganchev.persistence.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    List<RoleEntity> getByUserId(Long userId);

}
