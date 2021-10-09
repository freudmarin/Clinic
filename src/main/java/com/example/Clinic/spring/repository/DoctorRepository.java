package com.example.Clinic.spring.repository;

import com.example.Clinic.spring.model.Doctor;
import com.example.Clinic.spring.model.Reason;
import com.example.Clinic.spring.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecializationList(Specialization specialization);
}
