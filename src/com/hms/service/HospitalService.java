package com.hms.service;

import com.hms.exception.HospitalException;
import com.hms.model.*;
import com.hms.util.DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HospitalService {
    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Appointment> appointments;
    
    private final String DOC_FILE = "doctors.dat";
    private final String PAT_FILE = "patients.dat";

    @SuppressWarnings("unchecked")
    public HospitalService() {
        // Load data on startup
        try {
            doctors = (List<Doctor>) DataManager.loadData(DOC_FILE);
            patients = (List<Patient>) DataManager.loadData(PAT_FILE);
            if (doctors == null) doctors = new ArrayList<>();
            if (patients == null) patients = new ArrayList<>();
            appointments = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            doctors = new ArrayList<>();
            patients = new ArrayList<>();
            appointments = new ArrayList<>();
        }
    }

    public void addDoctor(String id, String name, int age, String spec) {
        doctors.add(new Doctor(id, name, age, spec));
    }

    public void registerPatient(String id, String name, int age, String ailment) {
        patients.add(new Patient(id, name, age, ailment));
    }

    public void bookAppointment(String docId, String patId) throws HospitalException {
        // Validate existence using Java Streams
        boolean docExists = doctors.stream().anyMatch(d -> d.getId().equals(docId));
        Optional<Patient> patientOpt = patients.stream().filter(p -> p.getId().equals(patId)).findFirst();

        if (!docExists) throw new HospitalException("Doctor ID not found.");
        if (patientOpt.isEmpty()) throw new HospitalException("Patient ID not found.");

        String apptId = "APT-" + (appointments.size() + 1);
        Appointment appt = new Appointment(apptId, docId, patId);
        appointments.add(appt);
        
        patientOpt.get().addAppointmentLog("Booked " + apptId + " with Dr. " + docId);
        System.out.println("Appointment Booked Successfully: " + apptId);
    }

    public void listDoctors() {
        if(doctors.isEmpty()) System.out.println("No doctors in system.");
        doctors.forEach(System.out::println);
    }

    public void listPatients() {
        if(patients.isEmpty()) System.out.println("No patients in system.");
        patients.forEach(System.out::println);
    }

    public void saveAll() {
        try {
            DataManager.saveData(doctors, DOC_FILE);
            DataManager.saveData(patients, PAT_FILE);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
}