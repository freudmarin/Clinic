package com.example.Clinic.spring.repository;

import com.example.Clinic.spring.model.Availability;
import com.example.Clinic.spring.model.Doctor;
import com.example.Clinic.spring.model.Reason;
import com.example.Clinic.spring.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    List<Specialization> findByDoctors(Doctor doctor); //all specialization of a doctor

    Specialization findByReasonList(Reason reason);
}
