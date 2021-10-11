package com.example.Clinic.spring.controllers;
import com.example.Clinic.spring.model.Patient;
import com.example.Clinic.spring.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class PatientViewModel extends ReasonView {

    private String patientID;
    private String name;
    private Date dateOfBirth;
    public PatientService getPatientService() {
        return patientService;
    }

    @WireVariable
    PatientService patientService;
    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    private List<Patient> patientsList = new ArrayList<>();

    public List<Patient> getPatientsList() {
        return patientsList;
    }

    public PatientViewModel(String patientID, String name, Date dateOfBirth) {
        this.patientID = patientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    @Init
    public void initSetup() {
        patientsList = patientService.getAllPatients();
    }

    public PatientViewModel() {

    }
    private PatientViewModel convertToPatientView(Patient patient) {
        PatientViewModel result = null;

        if (patient != null) {

            result = new PatientViewModel(patient.getPatientID(), patient.getName(), patient.getDateOfBirth());
        }
        return result;

    }

    public Patient convertViewToPatient(PatientViewModel patient) {
        Patient result = null;

        if (patient != null) {

            result = new Patient(patient.getPatientID(), patient.getName(), patient.getDateOfBirth());
        }
        return result;

    }

    @Command
    @NotifyChange()
    public void add() {
        Patient patient = new Patient(patientID, name, dateOfBirth);

        if (patientService.addPatient(patient) == null)
            Messagebox.show("Patient  with this patient ID  already exists", "Error", Messagebox.OK, Messagebox.ERROR);
        else {
            patientService.addPatient(patient);
            Messagebox.show("Patient added successfully", "Success", Messagebox.OK, Messagebox.INFORMATION);
        }
    }
}


