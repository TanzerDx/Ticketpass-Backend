package com.example.individual_assignment_hristo_ganchev.persistence.jpa;

import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepository extends JpaRepository<ConcertEntity, Long> {
}