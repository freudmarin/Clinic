package com.example.Clinic.spring.controllers;

import com.example.Clinic.spring.model.Doctor;
import com.example.Clinic.spring.model.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentView {
    Long id;
    Date appointmentDate;
    DoctorView doctor;
    Patient patient;
    String reason;
    public AppointmentView(Long  id,Date appointmentDate, DoctorView doctor, Patient patient, String  reason) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
        this.patient = patient;
        this.reason = reason;
    }


    public AppointmentView() {
    }



    public String ConvertDateToString() {

return  this.appointmentDate.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public DoctorView getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorView doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String  getDoctorName()
    {
        return this.doctor.getName();
    }


    public String  getPatientName()
    {
        return this.patient.getName();
    }
}
