package com.example.Clinic.spring.repository;

import com.example.Clinic.spring.model.Appointment;
import com.example.Clinic.spring.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository  extends JpaRepository<Appointment, Long> {
}
