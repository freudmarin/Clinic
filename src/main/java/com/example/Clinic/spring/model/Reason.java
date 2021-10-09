package com.example.Clinic.spring.model;

import javax.persistence.*;

    @Entity
    @Table(name="Reason")
    public class Reason {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String reason;

        @ManyToOne
        @JoinColumn(name="specializationID")
        private Specialization specialization;


        @OneToOne(mappedBy = "reason")
        private Appointment appointment;

        public Reason( String reason, Specialization specialization, Appointment appointment) {

            this.reason = reason;
            this.specialization = specialization;
            this.appointment = appointment;
        }

        public Reason() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Specialization getSpecialization() {
            return specialization;
        }

        public void setSpecialization(Specialization specialization) {
            this.specialization = specialization;
        }

        public Appointment getAppointment() {
            return appointment;
        }

        public void setAppointment(Appointment appointment) {
            this.appointment = appointment;
        }
    }
