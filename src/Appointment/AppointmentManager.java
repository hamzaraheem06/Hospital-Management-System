package Appointment;
import User.*;

import java.util.ArrayList;
import java.time.LocalDate;

// Appointment manager is the only class that stores all the appointments, update the appointment list, remove anything, or approve/reject everything
// is controlled by this class
public class AppointmentManager {
    private ArrayList<Appointment> appointments; // This arraylist is the global appointment store, no other store should
    // exist that contradicts its elements

    public AppointmentManager() {
        this.appointments = new ArrayList<>();
    }

    public void requestAppointment(LocalDate date, Doctor doctor, Patient patient) {
        Appointment appointment = new Appointment(date, doctor, patient,  Appointment.AppointmentStatus.PENDING);
        appointments.add(appointment);
        System.out.println("Appointment requested: " + appointment);
    }

    public void approveAppointment(Appointment appointment) {
        if (appointments.contains(appointment) && appointment.getStatus() == Appointment.AppointmentStatus.PENDING) {
            appointment.updateStatus( Appointment.AppointmentStatus.APPROVED);
            System.out.println("Appointment approved: " + appointment);
        } else {
            System.out.println("Appointment not found or not in pending state.");
        }
    }

    public void cancelAppointment(Appointment appointment) {
        if (appointments.contains(appointment)) {
            appointment.updateStatus(Appointment.AppointmentStatus.CANCELED);
            System.out.println("Appointment canceled: " + appointment);
        } else {
            System.out.println("Appointment not found.");
        }
    }

    public void updateAppointmentStatus(Appointment appointment, Appointment.AppointmentStatus newStatus) {
        if (appointments.contains(appointment)) {
            appointment.updateStatus(newStatus);
        } else {
            System.out.println("Appointment not found.");
        }
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public ArrayList<Appointment> getPendingAppointments() {
        ArrayList<Appointment> pendingAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == Appointment.AppointmentStatus.PENDING) {
                pendingAppointments.add(appointment);
            }
        }
        return pendingAppointments;
    }

    public ArrayList<Appointment> getApprovedAppointments() {
        ArrayList<Appointment> approvedAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == Appointment.AppointmentStatus.APPROVED) {
                approvedAppointments.add(appointment);
            }
        }
        return approvedAppointments;
    }

    public ArrayList<Appointment> getCancelledAppointments() {
        ArrayList<Appointment> cancelledAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == Appointment.AppointmentStatus.CANCELED) {
                cancelledAppointments.add(appointment);
            }
        }
        return cancelledAppointments;
    }

    public ArrayList<Appointment> getNoShowAppointments() {
        ArrayList<Appointment> noShowAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == Appointment.AppointmentStatus.NO_SHOW) {
                noShowAppointments.add(appointment);
            }
        }
        return noShowAppointments;
    }

    public ArrayList<Appointment> getRejectedAppointments() {
        ArrayList<Appointment> rejectedAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == Appointment.AppointmentStatus.REJECTED) {
                rejectedAppointments.add(appointment);
            }
        }
        return rejectedAppointments;
    }

    public ArrayList<Appointment> getAppointmentsByDoctor(Doctor doctor) {
        ArrayList<Appointment> doctorsAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctor() == doctor) {
                doctorsAppointments.add(appointment);
            }
        }
        return doctorsAppointments;
    }

    public ArrayList<Appointment> getCompletedAppointments() {
        ArrayList<Appointment> completedAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == Appointment.AppointmentStatus.COMPLETED) {
                completedAppointments.add(appointment);
            }
        }
        return completedAppointments;
    }

    public ArrayList<Appointment> getAppointmentsByPatient(Patient patient) {
        ArrayList<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient() == patient) {
                patientAppointments.add(appointment);
            }
        }
        return patientAppointments;
    }

    public void generateReport() {
        System.out.println("Generating Record...");
        System.out.println();
        System.out.println("Total number of appointments: " + appointments.size());
        System.out.println();
        System.out.println("Total number of appointments approved: " + getApprovedAppointments().size());
        System.out.println();
        System.out.println("Total number of appointments cancelled: " + getCancelledAppointments().size());
        System.out.println();
        System.out.println("Total number of appointments no-show: " + getNoShowAppointments().size());
        System.out.println();
        System.out.println("Total number of appointments rejected: " + getRejectedAppointments().size());
        System.out.println();
        System.out.println("Total number of appointments approved: " + getApprovedAppointments().size());
        System.out.println();
        System.out.println("Total number of appointments cancelled: " + getCancelledAppointments().size());
        System.out.println();
        System.out.println("Total number of appointments no-show: " + getNoShowAppointments().size());
        System.out.println();
        System.out.println("Total number of appointments rejected: " + getRejectedAppointments().size());
        System.out.println();
    }

    public void viewAppointments() {
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
            System.out.println();
        }
    }
}
