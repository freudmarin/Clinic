package com.example.Clinic.spring.controllers;

import com.example.Clinic.spring.model.Availability;
import com.example.Clinic.spring.model.Doctor;
import com.example.Clinic.spring.model.Patient;
import com.example.Clinic.spring.model.Specialization;
import com.example.Clinic.spring.services.DoctorService;
import com.example.Clinic.spring.services.PatientService;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.type.LocalTimeType;
import org.springframework.util.ObjectUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.A;
import org.zkoss.zul.Messagebox;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@VariableResolver(DelegatingVariableResolver.class)
public class DoctorViewModel extends PatientViewModel {
    @WireVariable
    DoctorService doctorService;

    public DoctorService getDoctorService() {
        return doctorService;
    }

    //doctor attributes from view
    public DoctorView doctor;

    public DoctorView getDoctor() {
        return doctor;
    }

    //availability,specialization data from view
    public List<SpecializationView> specialization;
    public List<AvailabilityView> availability;

    public List<SpecializationView> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<SpecializationView> specialization) {
        this.specialization = specialization;
    }

    public List<AvailabilityView> getAvailability() {
        return availability;
    }

    public void setAvailability(List<AvailabilityView> availability) {
        this.availability = availability;
    }

  public   List<DoctorView> allDoctors;
    List<Specialization> allSpecializations;

    public List<Specialization> getAllSpecializations() {
        return allSpecializations;
    }

    public void setAllSpecializations(List<Specialization> allSpecializations) {
        this.allSpecializations = allSpecializations;
    }

    public List<DoctorView> getAllDoctors() {
        return allDoctors;
    }



    @Init
    public void initSetup() {
        this.allDoctors = doctorService.getAllDoctors().stream().map(this::convertDoctorToView).collect(Collectors.toList());
        this.doctor = new DoctorView();
        this.doctor.availability = IntStream.range(1, 8).mapToObj(operand -> new AvailabilityView(DayOfWeek.of(operand), "08:00", "19:00")).collect(Collectors.toList());
        this.doctor.specializations = doctorService.getSpecialization().stream().map(specialization -> new SpecializationView(specialization.getId(), specialization.getType())).collect(Collectors.toList());

        this.allSpecializations = doctorService.getSpecialization();
    }

    public DoctorViewModel() {
    }

    public  DoctorView convertDoctorToView(Doctor doctor) {
        DoctorView result = null;

        if (doctor != null) {
            result = new DoctorView(doctor.getId(), doctor.getName(), doctor.getUsername(), doctor.getPassword(),
                    doctor.getAvailabilityList().stream().map(avl ->
                            new AvailabilityView(avl.getDayOfWeek(),
                                    avl.getBeginTime() != null ? avl.getBeginTime().format(new DateTimeFormatterBuilder().toFormatter(Locale.ITALY)) : "8:00",
                                    avl.getEndTime() != null ? avl.getEndTime().format(new DateTimeFormatterBuilder().toFormatter(Locale.ITALY)) : "19:00")).collect(Collectors.toList()),
                    doctor.getSpecializationList().stream().map(spec -> new SpecializationView(spec.getId(), spec.getType())).collect(Collectors.toList()));
        }

        return result;
    }public  Doctor convertViewToDoctor(DoctorView doctorView) {
        Doctor result = null;

        if (doctor != null) {
            result = new Doctor(doctorView.getId(),"doctor", doctorView.getName(), doctorView.getUserName(), doctorView.getPassword(),


                    doctorView.getSpecializations().stream().map(spec -> new Specialization(spec.getId(), spec.getType())).collect(Collectors.toList()));
        }

        return result;
    }
    @Command
    @NotifyChange()
    public void addDoctor() {
        Doctor doctor = new Doctor("doctor", this.doctor.userName, this.doctor.password, this.doctor.name,
                this.specialization.stream().map(specializationView -> new Specialization(specializationView.getId(), specializationView.getType())).collect(Collectors.toList()));
        doctorService.addDoctor(doctor);
        boolean validHour = true;
        //this.doctor.availability - from view
        for (AvailabilityView avView : this.doctor.availability) {
            if (LocalTime.parse(avView.getEnd()).isBefore(LocalTime.of(19, 01)) && LocalTime.parse(avView.getStart()).isAfter(LocalTime.of(7, 59))) {
                Availability availability = new Availability(avView.getDay(), LocalTime.parse(avView.getStart()), LocalTime.parse(avView.getEnd()), doctor);
                doctorService.addAvailability(availability);
            } else {
                validHour = false;
                Messagebox.show("The clinic is not opened in this time", "Success", Messagebox.OK, Messagebox.INFORMATION);
                break;
            }
        }
        if (validHour)
            Messagebox.show("Doctor added successfully", "Success", Messagebox.OK, Messagebox.INFORMATION);
    }
}

