package com.example.individual_assignment_hristo_ganchev.persistence.jpa;

import com.example.individual_assignment_hristo_ganchev.persistence.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    List<TicketEntity> getByOrderId(Long orderId);

    List<TicketEntity> getByConcertId(Long concertId);
}
