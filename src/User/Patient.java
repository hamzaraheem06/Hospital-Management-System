package User;

// importing all the required Classes
import java.time.LocalDate;
import java.util.ArrayList;
import Appointment.Appointment;
import Appointment.AppointmentManager;
import HealthData.VitalDatabase;
import D_P_Interaction.Feedback;
import D_P_Interaction.MedicalDatabase;

public class Patient extends User {
    // defining all the private attributes for security
    private final String patientID = User.randomIdGenerator();
    private ArrayList<LocalDate> checkupHistory = new ArrayList<>();
    private boolean isAdmit;
    private double pendingFee;
    private ArrayList<String> diagnosis = new ArrayList<>();
    private ArrayList<Feedback> feedbacks;
    private final AppointmentManager appointmentManager;

    // defining the constructor
    public Patient( AppointmentManager manager, String name, LocalDate dob, String gender, String address, String phone, String email, String password, ArrayList<LocalDate> checkupHistory, boolean isAdmit, double pendingFee, ArrayList<String> diagnosis, ArrayList<Feedback> feedback) {
        super( name, dob, gender, address, phone, email, password);
        this.checkupHistory = checkupHistory;
        this.isAdmit = isAdmit;
        this.pendingFee = pendingFee;
        this.diagnosis = diagnosis;
        this.feedbacks = feedback;
        this.appointmentManager = manager;
    }

    // copy constructor
    public Patient(Patient patient) {
        super(patient.getName(), patient.getDateOfBirth(), patient.getGender(), patient.getAddress(), patient.getPhone(), patient.getEmail(), patient.getPassword());
        this.isAdmit = patient.isAdmit;
        this.pendingFee = patient.pendingFee;
        this.diagnosis.addAll(patient.diagnosis);
        this.feedbacks = new ArrayList<>(patient.feedbacks); // creating the new arraylist so that the copy object doesnt affect the original object
        this.appointmentManager = patient.appointmentManager;
        this.checkupHistory = new ArrayList<>(patient.checkupHistory); // creating the new arraylist so that the copy object doesnt affect the original object
    }

    // getters
    public String getPatientID() {
        return patientID;
    }

    public boolean isAdmit() {
        return isAdmit;
    }

    public ArrayList<LocalDate> getCheckupHistory() {
        return checkupHistory;
    }

    public ArrayList<String> getDiagnosis() {
        return diagnosis;
    }

    public ArrayList<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public double getPendingFee() {
        return pendingFee;
    }

    public LocalDate getLastVisit() {
        return checkupHistory.getLast();
    }

    // setters
    public void setAdmit(boolean admit) {
        this.isAdmit = admit;
    }

    public void addCheckup(LocalDate checkup) {
        this.checkupHistory.add(checkup);
    }

    public void addDiagnosis(String diagnosis) {
        this.diagnosis.add(diagnosis);
    }

    public void addFeedback(Feedback feedback) {
        this.feedbacks.add(feedback);
    }

    public void setPendingFee(double pendingFee) {
        if(pendingFee < 0) {
            System.out.println("Invalid amount.");
            pendingFee = -1; // -1 to indicate that the fee was never initialized, also to differ it from 0 which will
            // mean that the fee has been paid completely
        }
        this.pendingFee = pendingFee;
    }

    // patient methods
    public void payFee(double payAmount) {
        if (payAmount < 0) {
            System.out.println("Invalid amount.");
            payAmount = 0;
        }
        pendingFee -= payAmount;
        System.out.println("Payment successful. Remaining Amount: " + payAmount);
    }

    public void generateReport() {
        VitalDatabase.displayPatientVitals(this); // accessing the central database for vitals for displaying
    }

    public void generateMedicalReport() {
        MedicalDatabase.displayPatientMedicalHistory(this); // accessing the central medical database
    }

    // consistent appointment methods for users
    public void scheduleAppointment(Doctor doctor, LocalDate date) {
        appointmentManager.requestAppointment(date, doctor, this);
        System.out.println("Appointment scheduled for " + date + ".");
    }

    public void cancelAppointment(Appointment appointment) {
        appointmentManager.cancelAppointment(appointment);
    }

    @Override
    public String toString() {
        return String.format("\\nDetails:\\nUserID: %s\\tName: %s\\tAge: %s\\tGender: %s\\tAddress:" +
                " %s\\tPhone: %s\\tEmail: %s\\tPassword: %s\\Patient Details:\\Patient ID: %s\\tCheckup" +
                " History: %s\\tAdmit: %b\\tPending Fee:  %f\\tDiagnosis:" +
                " %s\\t", getUserID(), getName(), getAge(), getGender(), getAddress(), getPhone(), getEmail(),
                getPassword(), patientID, checkupHistory, isAdmit, pendingFee, diagnosis);
    }
}

