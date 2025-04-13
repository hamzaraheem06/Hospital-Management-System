package Appointment;

import User.Doctor;
import User.Patient;
import User.User;

import java.time.LocalDate;

// a central class for Appointment storage
public class Appointment {
    // enum for status
    public enum AppointmentStatus {
        PENDING,   // Waiting for approval
        APPROVED,  // Approved by admin or doctor
        CANCELED,  // Canceled by patient or admin
        COMPLETED, // Successfully completed appointment
        NO_SHOW,   // Patient didn't attend the appointment
        REJECTED   // Rejected by the doctor/admin
    }

    // private class attributes
    private final String appointmentID = User.randomIdGenerator();
    private final LocalDate date;
    private final Doctor doctor;
    private final Patient patient;
    private AppointmentStatus status;

    // Constructor
    public Appointment(LocalDate date, Doctor doctor, Patient patient, AppointmentStatus status) {
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
        this.status = status;
    }

    // Copy Constructor
    public Appointment(Appointment appointment) {
        this.date = appointment.getDate();
        this.doctor = appointment.getDoctor();
        this.patient = appointment.getPatient();
        this.status = appointment.getStatus();
    }

    // getters
    public String getAppointmentID() {
        return appointmentID;
    }

    public LocalDate getDate() {
        return date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    // update status
    public void updateStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID + ", Date: " + date + ", Doctor: " + doctor.getName() + ", Patient: " + patient.getName() + ", Status: " + status;
    }
}
