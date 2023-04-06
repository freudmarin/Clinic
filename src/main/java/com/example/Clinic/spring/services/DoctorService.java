package com.example.Clinic.spring.services;

import com.example.Clinic.spring.model.Availability;
import com.example.Clinic.spring.model.Doctor;
import com.example.Clinic.spring.model.Reason;
import com.example.Clinic.spring.model.Specialization;
import com.example.Clinic.spring.repository.AvailabilityRepository;
import com.example.Clinic.spring.repository.DoctorRepository;
import com.example.Clinic.spring.repository.ReasonRepository;
import com.example.Clinic.spring.repository.SpecializationRepository;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    SpecializationRepository specializationRepository;
    @Autowired
    ReasonRepository reasonRepository;
    @Autowired
    SessionFactory sessionFactory;

    public List<Availability> getDoctorAvailabilities() {
        return availabilityRepository.findAll();
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    public List<Doctor> getDoctorsByVisitReason(String  reason) {
        List<Doctor> doctorList;
        if(reason!=null && !reason.equals("")) {
            Reason reasonResult = reasonRepository.findByReasonContaining(reason);
            Specialization specialization = specializationRepository.findByReasonList(reasonResult);
            doctorList = doctorRepository.findBySpecializationList(specialization);
        }
        else {
            doctorList = doctorRepository.findAll();
        }
        return doctorList;
    }
    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public List<Specialization> getSpecialization() {
        return specializationRepository.findAll();
    }
    public void addAvailability(Availability availability) {
        availabilityRepository.save(availability);
    }
    public Reason getReason(String reason) {
        return  reasonRepository.findByReason(reason);
    }
    public List<Availability> getAvailabilityByDoctor (Doctor doctor)
    {
        return availabilityRepository.findByDoctor(doctor);
    }
}
