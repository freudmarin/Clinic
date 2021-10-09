package com.example.Clinic.spring.repository;

import com.example.Clinic.spring.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
}
