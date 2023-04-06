package com.example.Clinic.spring.controllers;

import java.util.List;

public class DoctorView {
    Long id;
    String name;
    String userName;
    String password;
    List<AvailabilityView> availability;
    List<SpecializationView> specializations;

    // [1,2,3,4,5,6] -> reduce -> (accumulator, element) -> accumulator
    // [a,b,c] -> "a, b, c,"
    public String specializationToString() {
        if (this.specializations != null) {


            String description = this.specializations.stream().map(SpecializationView::getType).reduce("", (accumulator, elemnt) -> {
                accumulator += elemnt + ", ";
                return accumulator;
            });
            description = description.substring(0, description.lastIndexOf(","));
            return description;
        } else return "No specialization";
    }

    public DoctorView(Long id, String name, String userName, String password, List<AvailabilityView> availability, List<SpecializationView> specializations) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.availability = availability;
        this.specializations = specializations;
    }

    public DoctorView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AvailabilityView> getAvailability() {
        return availability;
    }

    public void setAvailability(List<AvailabilityView> availability) {
        this.availability = availability;
    }

    public List<SpecializationView> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<SpecializationView> specializations) {
        this.specializations = specializations;
    }
}
