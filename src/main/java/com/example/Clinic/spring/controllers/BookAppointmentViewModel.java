package com.example.Clinic.spring.controllers;

import com.example.Clinic.spring.model.*;
import com.example.Clinic.spring.services.AppointmentService;
import com.example.Clinic.spring.services.DoctorService;
import com.example.Clinic.spring.services.PatientService;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.text.DateFormats;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;

import javax.persistence.Convert;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.Clinic.spring.controllers.Conversions.convertViewToDoctor;

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
List<String> appointmentTimes = new ArrayList<>();
//converted date for persistence
Date dayOfAppointment =new Date();
//selected string from the view
  public   String selectedTimeOfAppointment;
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

    public List<String> getAppointmentTimes() {
        return appointmentTimes;
    }

    public void setAppointmentTimes(List<String> appointmentTimes) {
        this.appointmentTimes = appointmentTimes;
    }

    public void setAllDoctors(List<DoctorView> allDoctors) {
        this.allDoctors = allDoctors;
    }
/*
    public Date getDayOfAppointment() {
        return dayOfAppointment;
    }

    public void setDayOfAppointment(Date dayOfAppointment) {
        this.dayOfAppointment = dayOfAppointment;
    }*/

    public String getSelectedTimeOfAppointment() {
        return selectedTimeOfAppointment;
    }

    public void setSelectedTimeOfAppointment(String selectedTimeOfAppointment) {
        this.selectedTimeOfAppointment = selectedTimeOfAppointment;
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
    @NotifyChange({"appointment","appointmentTimes"})
    public void selectedDoctor() {
        this.appointment.doctor = this.selectedDoctor;
        this.appointmentTimes=appointmentTime();



    }




    @Command //@Command declares a command method
    @NotifyChange({"appointment"})
    public void selectedDateOfAppointment() {
        //dayOfAppointment is variable for selected date
        this.appointment.appointmentDate = convertSelectedTimeDate(selectedTimeOfAppointment);



        //convert  the List<Availability> to List<AvailabilityView> after you process the data fetched from database
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

        Appointment appointment = new Appointment(this.appointment.getId(), convertViewToDoctor(this.selectedDoctor), this.appointment.patient, this.appointment.appointmentDate, this.doctorService.getReason(this.reasonOfVisit).getReason());
        appointmentService.addAppointment(appointment);
        Messagebox.show("Appointment booked successfully", "Success", Messagebox.OK, Messagebox.INFORMATION);
    }

    public List<String> appointmentTime() {
        //Once the doctor is selected we can fetch the list of availabilities for that particular doctor
        List<Availability> doctorAvailabilities = doctorService.getAvailabilityByDoctor(Conversions.convertViewToDoctor(this.appointment.doctor));
        List<String> allAppointmentTimes = new ArrayList<>();


        for (Availability availability : doctorAvailabilities) {
            String  dayOfWeek = availability.getDayOfWeek().toString();


         String firstLetter  =  dayOfWeek.substring(0,1);
         String otherLetters =dayOfWeek.substring(1).toLowerCase();
            LocalTime start = availability.getBeginTime();
            LocalTime end = availability.getEndTime();
            allAppointmentTimes.add(firstLetter+otherLetters +" "+ start.toString());
            while (start.isBefore(end)) {
                start = start.plusMinutes(30);
                allAppointmentTimes.add(firstLetter+otherLetters+" "+ start.toString());
            }
allAppointmentTimes.remove(allAppointmentTimes.size()-1);
        }
        return allAppointmentTimes;
    }

    public Date convertSelectedTimeDate(String selectedTimeOfAppointment) {


        String[] arrStringOfSelectedDate = selectedTimeOfAppointment.split(" ");
        String dayOfWeekSelected = arrStringOfSelectedDate[0].toUpperCase();
        String hourSelected =arrStringOfSelectedDate[1];
        String[] hourAndMinutes= hourSelected.split(":");
        int hour =Integer.parseInt(hourAndMinutes[0]);
        int minutes=Integer.parseInt(hourAndMinutes[1]);


        //the selected one
        int dayOfWeek = DayOfWeek.valueOf(dayOfWeekSelected).getValue();//parseDayOfWeek(dayOfWeekSelected);


        LocalDate today = LocalDate.now();
        LocalDate choosenDate=today;
        //so  if today's index is greater than the selected it means you can't choose that selected
        if (today.getDayOfWeek().getValue() > dayOfWeek) {
            return null;
        } else {
            DayOfWeek dayOfThisWeek = DayOfWeek.of(dayOfWeek);


            while(choosenDate.getDayOfWeek()!=dayOfThisWeek)
            {
choosenDate=choosenDate.plusDays(1);
            }
            Date choosenDateInDateFormat = convertToDate(choosenDate);

       //     choosenDateInDateFormat.setTime(hour+minutes);
return choosenDateInDateFormat;




        }
    }
    public   Date convertToDate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
/*    public   int parseDayOfWeek(String day)
    {
        SimpleDateFormat dayFormat = new SimpleDateFormat("DD:HH:mm");
        Date date = dayFormat.day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }*/
}

