package com.example.Clinic.spring.repository;

import com.example.Clinic.spring.model.Patient;
import com.example.Clinic.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByPatientID(String patientID);
}