package com.example.Clinic.spring.controllers;

import com.example.Clinic.spring.model.Appointment;
import com.example.Clinic.spring.model.Doctor;
import com.example.Clinic.spring.model.Reason;
import com.example.Clinic.spring.model.Specialization;

import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.stream.Collectors;

public class Conversions {
    public Reason convertViewToReason(ReasonView reasonView)
    {
        Reason result = null;

        if (reasonView != null) {
            result  = new Reason(reasonView.getId(), reasonView.getType());
        }
        return  result;
    }
    public DoctorView convertDoctorToView(Doctor doctor) {
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
    }

    public Doctor convertViewToDoctor(DoctorView doctorView) {
        Doctor result = null;

        if (doctorView != null) {
            result = new Doctor(doctorView.getId(), "doctor", doctorView.getName(), doctorView.getUserName(), doctorView.getPassword(),
                    doctorView.getSpecializations().stream().map(spec -> new Specialization(spec.getId(), spec.getType())).collect(Collectors.toList()));
        }

        return result;
    }
    public AppointmentView convertAppointmentToView(Appointment appointment) {
        AppointmentView result = null;
        //  SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        if (appointment != null) {
            result = new AppointmentView(appointment.getId(), appointment.getDateOfAppointment(), convertDoctorToView(appointment.getDoctor()), appointment.getPatient(), appointment.getReason());

        }
        return result;
    }


}
