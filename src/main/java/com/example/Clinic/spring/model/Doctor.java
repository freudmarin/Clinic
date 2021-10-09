package com.example.Clinic.spring.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Doctor")

public class Doctor extends User {


    @OneToMany(mappedBy = "doctor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Availability> availabilityList;


    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "doctor_specialization",

            inverseJoinColumns = @JoinColumn(name = "specialization_id"))
    private List<Specialization> specializationList;


    @OneToMany(mappedBy = "doctor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Appointment> appointmentListList;


    public Doctor(String role, String username, String password, String name, List<Specialization> specializationList) {// List<Appointment> appointmentListList) {
        super(role, username, password, name);

      //  this.availabilityList = availabilityList;
        this.specializationList = specializationList;
        // this.appointmentListList = appointmentListList;
    }

    public Doctor() {
    }




    public List<Availability> getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(List<Availability> availabilityList) {
        this.availabilityList = availabilityList;
    }

    public List<Specialization> getSpecializationList() {
        return specializationList;
    }

    public void setSpecializationList(List<Specialization> specializationList) {
        this.specializationList = specializationList;
    }

    public List<Appointment> getAppointmentListList() {
        return appointmentListList;
    }

    public void setAppointmentListList(List<Appointment> appointmentListList) {
        this.appointmentListList = appointmentListList;
    }
}
