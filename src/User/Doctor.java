package User;

// importing all the mandatory Classes
import Appointment.Appointment;
import Appointment.AppointmentManager;
import D_P_Interaction.Feedback;

import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.ArrayList;


public class Doctor extends User{
    private static final String PMDC_PATTERN = "^[a-zA-Z]{2,4}-[0-9]{4,5}"; // According to what Google said to me, I am not accountable if is not the official
    // declaring all the attributes as private
    private final String doctorID = User.randomIdGenerator();
    private String PMDC_NO;
    private String availabilityHours;
    private double salary;
    private String qualification;
    private String speciality;
    private int yearsOfExperience;
    private ArrayList<Patient> patients = new ArrayList<>();
    private final AppointmentManager appointmentManager;

    // defining all the constructors
    public Doctor( AppointmentManager manager, String name, LocalDate dob, String gender, String address, String phone, String email, String password, String PMDC_NO, String availabilityHours, double salary, String qualification, String speciality, int experience) {
        super(name, dob, gender, address, phone, email, password );
        setPMDC_NO(PMDC_NO);
        setAvailabilityHours(availabilityHours);
        setSalary(salary);
        setQualification(qualification);
        setSpeciality(speciality);
        setExperience(experience);
        this.appointmentManager = manager;
    }

    // a very demure copy constructor
    public Doctor(Doctor doctor) {
        super(doctor.getName(), doctor.getDateOfBirth(), doctor.getGender(), doctor.getAddress(), doctor.getPhone(), doctor.getEmail(), doctor.getPassword());
        this.appointmentManager = doctor.appointmentManager;
        setPMDC_NO(doctor.getPMDC_NO());
        setAvailabilityHours(doctor.getAvailabilityHours());
        setSalary(doctor.getSalary());
        setQualification(doctor.getQualification());
        setSpeciality(doctor.getSpeciality());
        setExperience(doctor.getExperience());
        this.patients = new ArrayList<Patient>(doctor.patients); // creating the new arraylist so that the copy object doesnt affect the original object
    }

    // defining the setters with validations
    public void setPMDC_NO(String PMDC_NO) {
        if(!isValidPMDC(PMDC_NO)) {
            System.out.println("Invalid PMDC");
            this.PMDC_NO = null;
            return;
        }
        this.PMDC_NO = PMDC_NO;
    }

    public void setAvailabilityHours(String availabilityHours) {
        this.availabilityHours = availabilityHours;
    }

    public void setSalary(double salary) {
        if(salary < 0.0) {
            System.out.println("Invalid salary");
            this.salary = 0.0;
            return;
        }
        this.salary = salary;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setExperience(int experience) {
        if(experience < 0) {
            System.out.println("Invalid experience");
            this.yearsOfExperience = 0;
            return;
        }
        this.yearsOfExperience = experience;
    }

    // defining all the getters
    public String getDoctorID() {
        return doctorID;
    }

    public String getPMDC_NO() {
        return PMDC_NO;
    }

    public String getAvailabilityHours() {
        return availabilityHours;
    }

    public double getSalary() {
        return salary;
    }

    public String getQualification() {
        return qualification;
    }

    public String getSpeciality() {
        return speciality;
    }

    public int getExperience() {
        return yearsOfExperience;
    }

    // validation methods
    public static boolean isValidPMDC(String PMDC_NO) {
        return Pattern.matches(PMDC_PATTERN, PMDC_NO);
    }

    // defining class methods
    public void diagnosePatient(Patient patient, String diagnose) {
        if(!patients.contains(patient)) { // updating the doctor's independent patient list to keep the record
            patients.add(patient);
        }
        patient.addDiagnosis(diagnose);
    }

    public double calculateConsultation() {
        if (yearsOfExperience < 5) {
            return 50;
        } else if (yearsOfExperience <= 10) {
            return 100;
        } else {
            return 200;
        }
    }

    public void addFeedback(Patient patient, Feedback feedback) {
        if(!patients.contains(patient)) {
            patients.add(patient);
        }
        patient.addFeedback(feedback);
    }

    public String getPatientDetails(Patient patient) { // displaying only necessary patient details to doctor
        return String.format("Name: %s\nAge: %s\nGender: %s\nDiagnosis: %s\n", patient.getName(), patient.getAge(), patient.getGender(), patient.getDiagnosis());
    }

    // consistent appointment scheduler and cancel methods
    @Override
    public void scheduleAppointment(Doctor doctor, Patient patient, LocalDate date) {
        appointmentManager.requestAppointment(date, this, patient);
        System.out.println("Appointment scheduled for " + date + ".");
    }

    @Override
    public void cancelAppointment(Appointment appointment) {
        appointmentManager.cancelAppointment(appointment);
    }

    @Override
    public String toString() {
        return String.format("\nDetails:\nName: %s\tAge: %ss\tGender: %s\tAddress: %s\tPhone: %s\tEmail: %s\tPassword: %s\nProfessional Details:\nDoctor ID: %s\tPMDC No: %s\tAvailability Hours: %s\tSalary: %.3f\tQualification: %s\tSpeciality: %s\tExperience: %d years\t",getName(), getAge(), getGender(), getAddress(), getPhone(), getEmail(), getPassword(), doctorID, PMDC_NO, availabilityHours, salary, qualification, speciality, yearsOfExperience);
    }
}

