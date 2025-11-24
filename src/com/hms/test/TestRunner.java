package com.hms.test;

import com.hms.service.HospitalService;
import com.hms.exception.HospitalException;
import java.io.File;

public class TestRunner {
    
    public static void main(String[] args) {
        System.out.println("=== STARTING AUTOMATED TESTS ===\n");

        setupEnvironment();

        testAddDoctor();
        testRegisterPatient();
        testBookAppointmentSuccess();
        testBookAppointmentFailure();

        System.out.println("\n=== TEST SUITE FINISHED ===");
    
    }

    private static void setupEnvironment() {
        new File("doctors.dat").delete();
        new File("patients.dat").delete();
        System.out.println("[Setup] Cleared previous data files.");
    }

    // Test 1: Verify adding a doctor works without crashing
    private static void testAddDoctor() {
        System.out.print("Test 1: Add Doctor... ");
        HospitalService service = new HospitalService();
        service.addDoctor("D001", "Dr. Smith", 45, "Cardiology");
        System.out.println("PASSED");
    }

    // Test 2: Verify registering a patient works
    private static void testRegisterPatient() {
        System.out.print("Test 2: Register Patient... ");
        HospitalService service = new HospitalService();
        service.registerPatient("P001", "John Doe", 30, "Flu");
        System.out.println("PASSED");
    }

    // Test 3: Verify booking logic (Success Case)
    private static void testBookAppointmentSuccess() {
        System.out.print("Test 3: Book Valid Appointment... ");
        HospitalService service = new HospitalService();
        service.addDoctor("D001", "Dr. Smith", 45, "Cardiology");
        service.registerPatient("P001", "John Doe", 30, "Flu");

        try {
            service.bookAppointment("D001", "P001");
            System.out.println("PASSED");
        } catch (HospitalException e) {
            System.out.println("FAILED (Exception: " + e.getMessage() + ")");
        }
    }

    // Test 4: Verify error handling (Failure Case)
    private static void testBookAppointmentFailure() {
        System.out.print("Test 4: Book Invalid Appointment (Wrong IDs)... ");
        HospitalService service = new HospitalService();
        
        try {
            service.bookAppointment("D999", "P999"); // These IDs don't exist
            System.out.println("FAILED (Should have thrown exception)");
        } catch (HospitalException e) {
            System.out.println("PASSED (Caught expected error: " + e.getMessage() + ")");
        }
    }
}