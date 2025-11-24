package com.hms.main;

import com.hms.exception.HospitalException;
import com.hms.service.HospitalService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HospitalService service = new HospitalService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== HOSPITAL MANAGEMENT SYSTEM ===");

        while (running) {
            System.out.println("\n1. Add Doctor");
            System.out.println("2. Register Patient");
            System.out.println("3. Book Appointment");
            System.out.println("4. List Doctors");
            System.out.println("5. List Patients");
            System.out.println("6. Exit & Save");
            System.out.print("Enter choice: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: "); String dId = scanner.nextLine();
                        System.out.print("Enter Name: "); String dName = scanner.nextLine();
                        System.out.print("Enter Age: "); int dAge = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Specialization: "); String spec = scanner.nextLine();
                        service.addDoctor(dId, dName, dAge, spec);
                        break;
                    case 2:
                        System.out.print("Enter ID: "); String pId = scanner.nextLine();
                        System.out.print("Enter Name: "); String pName = scanner.nextLine();
                        System.out.print("Enter Age: "); int pAge = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Ailment: "); String ail = scanner.nextLine();
                        service.registerPatient(pId, pName, pAge, ail);
                        break;
                    case 3:
                        System.out.print("Doctor ID: "); String docId = scanner.nextLine();
                        System.out.print("Patient ID: "); String patId = scanner.nextLine();
                        service.bookAppointment(docId, patId);
                        break;
                    case 4:
                        service.listDoctors();
                        break;
                    case 5:
                        service.listPatients();
                        break;
                    case 6:
                        service.saveAll();
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (HospitalException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("System Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}