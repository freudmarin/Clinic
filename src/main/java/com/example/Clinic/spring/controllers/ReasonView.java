package com.example.Clinic.spring.controllers;

import com.example.Clinic.spring.model.Doctor;
import com.example.Clinic.spring.model.Reason;
import com.example.Clinic.spring.model.Specialization;

import java.util.stream.Collectors;

public class ReasonView {
    Integer id;
    String type;

    public ReasonView(Integer id, String type) {
        this.id = id;
        this.type = type;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ReasonView() {
    }


}
