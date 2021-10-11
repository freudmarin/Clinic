package com.example.Clinic.spring.services;
import com.example.Clinic.spring.model.Availability;
import com.example.Clinic.spring.model.Patient;
import com.example.Clinic.spring.repository.AvailabilityRepository;
import com.example.Clinic.spring.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    public Patient addPatient(Patient patient) {
        Patient patientFromDatabase = patientRepository.findByPatientID(patient.getPatientID());
//If doesn't exists on database
        if (patientFromDatabase == null) {
            patient = patientRepository.save(patient);
            return patient;
        } else
            return null;
    }
    public Patient findPatient(Patient patient)
    {
        return patientRepository.findById(patient.getId());
    }
}
