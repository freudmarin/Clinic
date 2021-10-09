package com.example.Clinic.spring.controllers;

import java.time.DayOfWeek;

public class AvailabilityView {

    boolean selected;
    DayOfWeek day;


    String start;
    String end;

    //   private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");



    public AvailabilityView(DayOfWeek day, String start, String end) {

        this.day = day;
        this.start = start;
        this.end = end;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
