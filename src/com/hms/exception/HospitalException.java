package com.hms.exception;

/**
 * Custom exception for domain-specific errors (e.g., Doctor not found).
 */
public class HospitalException extends Exception {
    public HospitalException(String message) {
        super(message);
    }
}