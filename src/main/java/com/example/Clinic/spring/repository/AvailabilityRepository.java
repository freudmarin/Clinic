package com.example.Clinic.spring.repository;

import com.example.Clinic.spring.model.Availability;
import com.example.Clinic.spring.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByDoctor(Doctor doctor);
}
