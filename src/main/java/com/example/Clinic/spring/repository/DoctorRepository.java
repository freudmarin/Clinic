package com.example.Clinic.spring.repository;

import com.example.Clinic.spring.model.Doctor;
import com.example.Clinic.spring.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecializationList(Specialization specialization);
}
