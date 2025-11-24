package com.hms.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String appointmentId;
    private String doctorId;
    private String patientId;
    private LocalDateTime dateTime;

    public Appointment(String appointmentId, String doctorId, String patientId) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.dateTime = LocalDateTime.now(); // Defaults to current time for simplicity
    }

    @Override
    public String toString() {
        return String.format("Appt ID: %s | Dr: %s | Pat: %s | Time: %s", 
            appointmentId, doctorId, patientId, 
            dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}