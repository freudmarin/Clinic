package com.example.Clinic.spring.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @ManyToOne
    @JoinColumn(name = "doctorID")
    private Doctor doctor;


    @ManyToOne
    @JoinColumn(name = "patientID")
    private Patient patient;


    private Date dateOfAppointment;

    @OneToOne(optional = false)
    @JoinColumn(name = "reasonID")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Reason reason;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Appointment() {
    }

    public Appointment(Doctor doctor, Patient patient, Date dateOfAppointment, Reason reason) {

        this.doctor = doctor;
        this.patient = patient;
        this.dateOfAppointment = dateOfAppointment;
        this.reason = reason;
    }
    public Appointment(Integer id,Doctor doctor, Patient patient, Date dateOfAppointment, Reason reason) {
this.id=id;
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

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }
}
