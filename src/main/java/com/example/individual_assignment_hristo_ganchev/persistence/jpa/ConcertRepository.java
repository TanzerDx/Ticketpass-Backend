package com.example.individual_assignment_hristo_ganchev.persistence.jpa;

import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertRepository extends JpaRepository<ConcertEntity, Long> {
    @Query("SELECT c FROM ConcertEntity c WHERE (c.artist LIKE %:keyword% OR c.musicGenre LIKE %:keyword% OR c.city LIKE %:keyword%) AND c.date > CURRENT_DATE")
    List<ConcertEntity> getByKeyword(@Param("keyword") String keyword);
}
