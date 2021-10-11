package com.example.Clinic.spring.repository;

import com.example.Clinic.spring.model.Appointment;
import com.example.Clinic.spring.model.Doctor;
import com.example.Clinic.spring.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository  extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctor(Doctor doctor);
}
