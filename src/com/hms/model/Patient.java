package com.hms.model;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private String ailment;
    private List<String> appointmentHistory;

    public Patient(String id, String name, int age, String ailment) {
        super(id, name, age);
        this.ailment = ailment;
        this.appointmentHistory = new ArrayList<>();
    }

    public void addAppointmentLog(String log) {
        appointmentHistory.add(log);
    }

    @Override
    public String toString() {
        return super.toString() + " | Ailment: " + ailment;
    }
}