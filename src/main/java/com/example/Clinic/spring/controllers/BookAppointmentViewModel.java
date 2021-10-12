package com.example.Clinic.spring.controllers;

import com.example.Clinic.spring.model.*;
import com.example.Clinic.spring.services.AppointmentService;
import com.example.Clinic.spring.services.DoctorService;
import com.example.Clinic.spring.services.PatientService;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;
import java.util.List;
import java.util.stream.Collectors;

@VariableResolver(DelegatingVariableResolver.class)
public class BookAppointmentViewModel {

    List<DoctorView> allDoctors;
    List<Patient> allPatients;
    AppointmentView appointment = new AppointmentView();
    ReasonView reason = new ReasonView();
    //public PatientViewModel patientPersistence;
    DoctorView selectedDoctor = new DoctorView();
    Patient selectedPatient = new Patient();
    Patient patientPersistence = new Patient();
    @WireVariable
    PatientService patientService;
    @WireVariable
    DoctorService doctorService;
    @WireVariable
    AppointmentService appointmentService;



    public PatientService getPatientService() {
        return patientService;
    }

    public String reasonOfVisit;

    public String getReasonOfVisit() {
        return reasonOfVisit;
    }

    public void setReasonOfVisit(String reasonOfVisit) {
        this.reasonOfVisit = reasonOfVisit;
    }

    public List<DoctorView> getAllDoctors() {
        return allDoctors;
    }

    public List<Patient> getAllPatients() {
        return allPatients;
    }

    public void setAllPatients(List<Patient> allPatients) {
        this.allPatients = allPatients;
    }

    public BookAppointmentViewModel() {
    }


    public DoctorView getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(DoctorView selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }


    public AppointmentView getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentView appointment) {
        this.appointment = appointment;
    }

    public Patient getPatientPersistence() {
        return patientPersistence;
    }

    public void setPatientPersistence(Patient patientPersistence) {
        this.patientPersistence = patientPersistence;
    }

    public ReasonView getReason() {
        return reason;
    }

    public void setReason(ReasonView reason) {
        this.reason = reason;
    }

    public DoctorService getDoctorService() {
        return doctorService;
    }

    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public AppointmentService getAppointmentService() {
        return appointmentService;
    }

    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public Patient getSelectedPatient() {
        return selectedPatient;
    }

    public void setSelectedPatient(Patient selectedPatient) {
        this.selectedPatient = selectedPatient;
    }


    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }


    @Init(superclass = true)
    public void BookAppointmentInitSetup() {


        //this is for the book-appointment.zul
        this.allDoctors = doctorService.getAllDoctors().stream().map(Conversions::convertDoctorToView).collect(Collectors.toList());
        this.allPatients = patientService.getAllPatients();

    }

    @Command
    @NotifyChange({"allDoctors"})
    public void changeFilter() {
        this.allDoctors = doctorService.getDoctorsByVisitReason(reasonOfVisit).stream().map(Conversions::convertDoctorToView).collect(Collectors.toList());
    }

    @Command //@Command declares a command method
    @NotifyChange({"appointment"})
    public void selectedDoctor() {
        this.appointment.doctor = this.selectedDoctor;
    }

    @Command //@Command declares a command method
    @NotifyChange({"patientPersistence", "appointment"})
    public void selectedPatient() {
        this.patientPersistence = this.getPatientService().findPatient(this.selectedPatient);
        this.appointment.patient = this.patientPersistence;
    }

    @Command
    @NotifyChange()
    public void addAppointment() {
      /*  List<Availability> doctorAvailabilitiesFromDb= doctorService.getAvailabilityByDoctor(this.convertViewToDoctor(this.appointment.doctor));
        for(Availability availability:doctorAvailabilitiesFromDb)
        {
            if(availability.getDayOfWeek().equals(DayOfWeek.parse(this.appointment.appointmentDate.getDay())
        }*/
        Appointment appointment = new Appointment(this.appointment.getId(), Conversions.convertViewToDoctor(this.selectedDoctor), this.appointment.patient, this.appointment.appointmentDate, this.doctorService.getReason(this.reasonOfVisit).getReason());
        appointmentService.addAppointment(appointment);
        Messagebox.show("Appointment booked successfully", "Success", Messagebox.OK, Messagebox.INFORMATION);
    }
  /*  @Command
    @NotifyChange("showPatientPopUp")
    public void patientPopUp() {
        //delete the todo in the database

        this.showPatientPopUp = true;

        *//* HashMap<String, Object> map = new HashMap<>();
        map.put("student", classroom.getStudents());
        Window window = (Window) Executions.createComponents("show-students.zul", null, map);
        window.setBorder(true);
        window.setClosable(true);


        window.doModal();
        window.onClose();*//*
    }*/







}
