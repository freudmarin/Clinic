package com.example.Clinic.spring.controllers;

import com.example.Clinic.spring.model.User;
import com.example.Clinic.spring.services.AppointmentService;
import com.example.Clinic.spring.services.AuthenticationService;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.List;
import java.util.stream.Collectors;
@VariableResolver(DelegatingVariableResolver.class)
public class MyAppointmentsView /*extends BookAppointmentViewModel*/ {

    @WireVariable
    AuthenticationService authenticationService;
    List<AppointmentView> allAppointments;


    @WireVariable
    AppointmentService appointmentService;
    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public List<AppointmentView> getAllAppointments() {
        return allAppointments;
    }

    public void setAllAppointments(List<AppointmentView> allAppointments) {
        this.allAppointments = allAppointments;
    }

    public AppointmentService getAppointmentService() {
        return appointmentService;
    }

    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Init//(superclass = true)
    public void ListAppointmentInitSetup() {


        User user = authenticationService.getUserData();

        if (authenticationService.getUserRole(user.getId()).equals("doctor")) {

            //this is for the allApointments.zul
            //this is for the allAppointments.zul
            //
            this.allAppointments= appointmentService.getAllAppointments().stream().filter(appointment1 -> appointment1.getDoctor().getId().equals(user.getId())).map(Conversions::convertAppointmentToView).collect(Collectors.toList());
        }
        else {
            this.allAppointments = appointmentService.getAllAppointments().stream().map(Conversions::convertAppointmentToView).collect(Collectors.toList());
        }
        //this is for the book-appointment.zul


    }
}
