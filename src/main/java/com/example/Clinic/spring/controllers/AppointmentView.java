package com.example.Clinic.spring.controllers;

public class AppointmentView {
    Integer id;
    String appointmentDate;
    DoctorView doctor;
    PatientViewModel patient;
    String reason;
    public AppointmentView(Integer id, String appointmentDate, DoctorView doctor, PatientViewModel patient, String reason) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
        this.patient = patient;
        this.reason = reason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public DoctorView getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorView doctor) {
        this.doctor = doctor;
    }

    public PatientViewModel getPatient() {
        return patient;
    }

    public void setPatient(PatientViewModel patient) {
        this.patient = patient;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
