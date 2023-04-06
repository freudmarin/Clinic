package com.example.Clinic.spring.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "doctorID")
    private Doctor doctor;


    @ManyToOne
    @JoinColumn(name = "patientID")
    private Patient patient;

    private Date dateOfAppointment;
    private String reason;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Appointment() {
    }

    public Appointment(Doctor doctor, Patient patient, Date dateOfAppointment, String reason) {

        this.doctor = doctor;
        this.patient = patient;
        this.dateOfAppointment = dateOfAppointment;
        this.reason = reason;
    }

    public Appointment(Long id, Doctor doctor, Patient patient, Date dateOfAppointment, String reason) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.dateOfAppointment = dateOfAppointment;
        this.reason = reason;
    }


    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
