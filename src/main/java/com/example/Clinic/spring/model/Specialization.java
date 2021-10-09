package com.example.Clinic.spring.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Specialization")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "specializationList", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Doctor> doctors = new ArrayList<>();


    @OneToMany(mappedBy = "specialization")
    private List<Reason> reasonList;
    private String type;


    /*    public Specialization(List<Doctor> doctors, *//*List<Reason> reasonList,*//* String type) {
        this.doctors = doctors;
     //   this.reasonList = reasonList;
        this.type = type;
    }*/
    public Specialization(Integer id, String type) {
        //     this.doctors = doctors;
//        this.reasonList = reasonList;
        this.id = id;
        this.type = type;
    }

    public Specialization() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Doctor> getDoctor() {
        return doctors;
    }

    public void setDoctor(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Reason> getReasonList() {
        return reasonList;
    }

    public void setReasonList(List<Reason> reasonList) {
        this.reasonList = reasonList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

