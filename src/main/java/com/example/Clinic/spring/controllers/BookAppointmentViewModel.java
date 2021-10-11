package com.example.Clinic.spring.controllers;

import com.example.Clinic.spring.model.*;
import com.example.Clinic.spring.services.AppointmentService;
import com.example.Clinic.spring.services.DoctorService;
import com.example.Clinic.spring.services.PatientService;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.A;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@VariableResolver(DelegatingVariableResolver.class)
public class BookAppointmentViewModel extends DoctorViewModel {
    List<AppointmentView> allAppointments;
    List<DoctorView> allDoctors;
    List<Patient> allPatients;




    AppointmentView appointment = new AppointmentView();
ReasonView reason=new ReasonView();

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

    public  String reasonOfVisit;

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

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    public AppointmentService getAppointmentService() {
        return appointmentService;
    }



    @Init(superclass = true)
    public void BookAppointmentInitSetup() {

        this.allDoctors = super.doctorService.getAllDoctors().stream().map(this::convertDoctorToView).collect(Collectors.toList());
        this.allPatients = patientService.getAllPatients();



    }

    @Command
    @NotifyChange({"allDoctors"})
    public void changeFilter() {
        this.allDoctors = super.doctorService.getDoctorsByVisitReason(reasonOfVisit).stream().map(this::convertDoctorToView).collect(Collectors.toList());

    }

    @Command //@Command declares a command method
    @NotifyChange({"appointment"})
    public void selectedDoctor() {

        //  this.appointment.doctor.name=this.selectedDoctor.name;

        this.appointment.doctor = this.selectedDoctor;

    }

    public Patient getSelectedPatient() {
        return selectedPatient;
    }

    public void setSelectedPatient(Patient selectedPatient) {
        this.selectedPatient = selectedPatient;
    }

    @Command //@Command declares a command method
    @NotifyChange({"patientPersistence","appointment"})
    public void selectedPatient() {

      this.patientPersistence= patientService.findPatientById(this.selectedPatient);

      this.appointment.patient=this.patientPersistence;

    }
    @Command
    @NotifyChange()
    public void addAppointment() {
        Appointment appointment = new Appointment(this.appointment.getId(),this.convertViewToDoctor(this.appointment.doctor),this.appointment.patient, this.appointment.appointmentDate, this.doctorService.getReason(this.reasonOfVisit).getReason());
        appointmentService.addAppointment(appointment);

        Messagebox.show("Appointment booked successfully", "Success", Messagebox.OK, Messagebox.INFORMATION);
    }

/*    public  Appointment convertViewToAppointment(AppointmentView appointmentView) throws ParseException {
        Appointment result = null;
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        if (appointmentView != null) {
            result = new Appointment(appointmentView.getDoctor(), appointmentView.getPatient(), formatter.parse(appointmentView.getAppointmentDate()), appointmentView.getDoctor());
                    doctor.getAvailabilityList().stream().map(avl ->
                            new AvailabilityView(avl.getDayOfWeek(),
                                    avl.getBeginTime() != null ? avl.getBeginTime().format(new DateTimeFormatterBuilder().toFormatter(Locale.ITALY)) : "8:00",
                                    avl.getEndTime() != null ? avl.getEndTime().format(new DateTimeFormatterBuilder().toFormatter(Locale.ITALY)) : "19:00")).collect(Collectors.toList()),
                    doctor.getSpecializationList().stream().map(spec -> new SpecializationView(spec.getId(), spec.getType())).collect(Collectors.toList()));
        }



        return result;
    }*/
   /*     Doctor doctor = new Doctor("doctor", this.doctor.userName, this.doctor.password, this.doctor.name,
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
    }*/
}
