package com.example.Clinic.spring.controllers;

import com.example.Clinic.spring.model.Doctor;
import com.example.Clinic.spring.model.Patient;
import com.example.Clinic.spring.services.DoctorService;
import com.example.Clinic.spring.services.PatientService;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import java.util.List;
import java.util.stream.Collectors;
@VariableResolver(DelegatingVariableResolver.class)
public class BookAppointmentViewModel extends DoctorViewModel {
    List<AppointmentView> allAppointments;
    List<DoctorView> allDoctors;


    List<Patient> allPatients;

    @WireVariable
    PatientService patientService;
    public PatientService getPatientService() {
        return patientService;
    }


    private String reasonOfVisit;

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

    @Init(superclass=true)
    public void BookAppointmentInitSetup() {

        this.allDoctors = super.doctorService.getAllDoctors().stream().map(this::convertDoctorToView).collect(Collectors.toList());
    this.allPatients=patientService.getAllPatients();
    }




    public ListModel<DoctorView> getFilteredDoctors() {
        return new ListModelList<DoctorView>(this.allDoctors);
    }


    @Command
    @NotifyChange({"allDoctors"})
    public void changeFilter() {
        this.allDoctors = super.doctorService.getDoctorsByVisitReason(reasonOfVisit).stream().map(this::convertDoctorToView).collect(Collectors.toList());
    }
}
