package com.example.Clinic.spring.controllers;

import com.example.Clinic.spring.model.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentView {
    Integer id;
    Date appointmentDate;
    DoctorView doctor;
    Patient patient;
    ReasonView reason;
    public AppointmentView(Integer id,Date appointmentDate, DoctorView doctor, Patient patient, ReasonView reason) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
        this.patient = patient;
        this.reason = reason;
    }


    public AppointmentView() {
    }
    public String ConvertDateToString() throws ParseException {

return  this.appointmentDate.toString();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public ReasonView getReason() {
        return reason;
    }

    public void setReason(ReasonView reason) {
        this.reason = reason;
    }
}
