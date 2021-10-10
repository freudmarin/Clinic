package com.example.Clinic.spring.services;

import com.example.Clinic.spring.model.Appointment;
import com.example.Clinic.spring.model.Doctor;
import com.example.Clinic.spring.repository.AppointmentRepository;
import com.example.Clinic.spring.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;


    public void addAppointment(Appointment appointment) {


        appointmentRepository.save(appointment);
    }
}
