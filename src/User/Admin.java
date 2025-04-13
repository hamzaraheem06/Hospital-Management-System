package User;

// importing necessary classes
import Appointment.Appointment;
import Appointment.AppointmentManager;
import java.util.ArrayList;
import java.time.LocalDate;

public class Admin extends User {
    // private class variables
    private final String adminID = User.randomIdGenerator();
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private final AppointmentManager appointmentManager;

    // Constructor
    public Admin(AppointmentManager manager, String name, LocalDate dob, String gender, String address, String phone, String email, String password) {
        super(name, dob, gender, address, phone, email, password);
        this.appointmentManager = manager;
    }

    // Copy Constructor
    public Admin(Admin admin) {
        super(admin.getName(), admin.getDateOfBirth(), admin.getGender(), admin.getAddress(), admin.getPhone(), admin.getEmail(), admin.getPassword());
        this.appointmentManager = admin.appointmentManager;
    }

    // Getters
    public String getAdminID() {
        return adminID;
    }

    // Methods for adding users

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    // Methods for viewing details
    public void viewPatient(String patientID) {
        for (Patient patient : patients) {
            if (patient.getPatientID().equals(patientID)) {
                System.out.println(patient);
                return;
            }
        }
        System.out.println("Patient not found");
    }

    public void viewDoctor(String doctorID) {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorID().equals(doctorID)) {
                System.out.println(doctor);
                return;
            }
        }
        System.out.println("Doctor not found");
    }

    // Methods for deleting users
    public void deletePatient(String patientID) {
        patients.removeIf(patient -> patient.getPatientID().equals(patientID));
        System.out.println("Patient deleted successfully");
    }

    public void deleteDoctor(String doctorID) {
        doctors.removeIf(doctor -> doctor.getDoctorID().equals(doctorID));
        System.out.println("Doctor deleted successfully");
    }

    // Methods for updating records
    public void updatePatient(String patientID, Patient newDetails) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getPatientID().equals(patientID)) {
                patients.set(i, newDetails);
                System.out.println("Patient updated successfully");
                return;
            }
        }
        System.out.println("Patient not found");
    }

    public void updateDoctor(String doctorID, Doctor newDetails) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getDoctorID().equals(doctorID)) {
                doctors.set(i, newDetails);
                System.out.println("Doctor updated successfully");
                return;
            }
        }
        System.out.println("Doctor not found");
    }

    // Additional Admin functionalities
    public void generateInvoice(int invoiceID, double amount, int patientID) {
        System.out.println("Invoice ID: " + invoiceID + ", Amount: $" + amount + " generated for patient ID: " + patientID);
    }

    public void sendPhoneReminder(int userID) {
        System.out.println("Phone reminder sent to user ID: " + userID);
    }

    public void sendMailReminder(int userID) {
        System.out.println("Email reminder sent to user ID: " + userID);
    }

    public void scheduleAppointment(Doctor doctor, Patient patient, LocalDate date) {
        appointmentManager.requestAppointment(date, doctor, patient);
        System.out.println("Appointment scheduled for " + date + ".");
    }

    public void cancelAppointment(Appointment appointment) {
        appointmentManager.cancelAppointment(appointment);
    }

    public void approveAppointment(Appointment appointment) {
        appointmentManager.approveAppointment(appointment);
    }

//  Generate Reports
    public void generateReport() {
        System.out.println("---- Admin Report ----");
        System.out.println("Total Patients: " + patients.size());
        System.out.println("Total Doctors: " + doctors.size());
        System.out.println("Total Appointments: " + appointmentManager.getAppointments().size());
        System.out.println("----------------------");
    }
}
