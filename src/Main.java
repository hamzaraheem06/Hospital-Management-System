import User.*; // importing every User class classes
import Appointment.*;
import HealthData.*;
import D_P_Interaction.*;
import ChatVideoConsultation.*;
import Alert.*;
import Notifications.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Doctor generateDoctor(AppointmentManager appointmentManager) {
        System.out.println("Creating a doctor account: ");
        Scanner input = new Scanner(System.in);
        String username;
        LocalDate dob;
        String gender;
        String address;
        String phone;
        String email;
        String password;
        String PMDC_NO;
        String availabilityHours;
        double salary;
        String qualification, speciality;
        int experience;

        // inputting the username
        while(true) {
            System.out.print("Enter Username: ");
            username = input.nextLine();
            if(User.isValidName(username)) {
                break;
            }
            System.out.println("Invalid Username, please try again. Username can only contain alphabetical characters and spaces.");
        }

        // date of birth entries
        System.out.println("Please enter your date of birth details.");
        System.out.print("Enter Date of Birth: ");
        int date = input.nextInt();
        System.out.print("Enter month of Birth: ");
        int month = input.nextInt();
        System.out.print("Enter year of Birth: ");
        int year = input.nextInt();
        dob = LocalDate.of(year, month, date);
        input.nextLine();

        // gender entry
        while(true) {
            System.out.print("Enter Gender: ");
            gender = input.nextLine();
            if(gender.equals("male") || gender.equals("female")) {
                break;
            }
            System.out.println("Invalid Gender, please try again. (male, female)");
        }

        // inputting address
        System.out.print("Enter Address: ");
        address = input.nextLine();

        // inputting phone
        System.out.print("Enter Phone Number: ");
        phone = input.nextLine();

        // inputting email
        while(true) {
            System.out.print("Enter Email: ");
            email = input.nextLine();
            if(User.isValidEmail(email)) {
                break;
            }
            System.out.println("Invalid Email, please try again. (e.g user123@gmail.com)");
        }

        // inputting password
        while(true) {
            System.out.print("Enter Password: ");
            password = input.nextLine();
            if(User.isValidPassword(password)) {
                break;
            }
            System.out.println("Invalid Password, please try again. Password can only contain alphanumerical characters and special characters ( @#$*&=+! ). No Spaces are allowed.");
        }

        // inputting PMDC no.
        while(true) {
            System.out.print("Enter PMDC NO: ");
            PMDC_NO = input.nextLine();
            if(Doctor.isValidPMDC(PMDC_NO)) {
                break;
            }
            System.out.println("Invalid ID, please try again. ( e.g. XXXX-01234");
        }

        // Availability Hours
        System.out.print("Enter Availability Hours: ");
        availabilityHours = input.nextLine();

        // salary
        do {
            System.out.print("Enter Salary: ");
            salary = input.nextDouble();

            if(salary < 0) {
                System.out.println("Invalid Salary, please try again.");
                continue;
            }
        } while(salary < 0);

        input.nextLine();
        // qualification and speciality
        System.out.println("Enter qualifications: ");
        qualification = input.nextLine();

        input.nextLine();
        System.out.print("Enter Speciality: ");
        speciality = input.nextLine();

        // experience
        do {
            System.out.print("Enter Experience: ");
            experience = input.nextInt();
        } while(experience < 0);

        return new Doctor(appointmentManager, username, dob, gender, address, phone, email, password, PMDC_NO, availabilityHours, salary, qualification, speciality, experience);
    }


    public static Patient generatePatient(AppointmentManager appointmentManager) {
        System.out.println("Creating a patient account: ");
        Scanner input = new Scanner(System.in);
        String username;
        LocalDate dob;
        String gender;
        String address;
        String phone;
        String email;
        String password;
        ArrayList<LocalDate> checkupHistory = new ArrayList<>();
        boolean isAdmit;
        double pendingFee;
        ArrayList<String> diagnosis = new ArrayList<>();
        ArrayList<Feedback> feedback = new ArrayList<>();

        while(true) {
            System.out.print("Enter Username: ");
            username = input.nextLine();
            if(User.isValidName(username)) {
                break;
            }
            System.out.println("Invalid Username, please try again. Username can only contain alphabetical characters and spaces.");
        }

        // date of birth entries
        System.out.println("Please enter your date of birth details.");
        System.out.print("Enter Date of Birth: ");
        int date = input.nextInt();
        System.out.print("Enter month of Birth: ");
        int month = input.nextInt();
        System.out.print("Enter year of Birth: ");
        int year = input.nextInt();
        dob = LocalDate.of(year, month, date);
        input.nextLine();
        // gender entry
        while(true) {
            System.out.print("Enter Gender: ");
            gender = input.nextLine();
            if(gender.equals("male") || gender.equals("female")) {
                break;
            }
            System.out.println("Invalid Gender, please try again. (male, female)");
        }

        // inputting address
        System.out.print("Enter Address: ");
        address = input.nextLine();

        // inputting phone
        System.out.print("Enter Phone Number: ");
        phone = input.nextLine();

        // inputting email
        while(true) {
            System.out.print("Enter Email: ");
            email = input.nextLine();
            if(User.isValidEmail(email)) {
                break;
            }
            System.out.println("Invalid Email, please try again. (e.g user123@gmail.com)");
        }

        // inputting password
        while(true) {
            System.out.print("Enter Password: ");
            password = input.nextLine();
            if(User.isValidPassword(password)) {
                break;
            }
            System.out.println("Invalid Password, please try again. Password can only contain alphanumerical characters and special characters ( @#$*&=+! ). No Spaces are allowed.");
        }

        System.out.println("Enter checkup history.");
        while (true) {
            System.out.print("Enter day (1-7): ");
            int visitDay = input.nextInt();
            System.out.print("Enter month (1-12): ");
            int visitMonth = input.nextInt();
            System.out.print("Enter year ( greater than 2000 ): ");
            int visitYear = input.nextInt();

            LocalDate visitDate = LocalDate.of(visitYear, visitMonth, visitDay);
            checkupHistory.add(visitDate);

            System.out.print("Add another date? (y for yes, anything else for no): ");
            char choice = input.next().charAt(0);
            if(choice == 'y') {
                continue;
            } else {
                break;
            }
        }

        System.out.print("Enter admit status: ");
        isAdmit = input.nextBoolean();

        System.out.print("Enter pending fee: ");
        pendingFee = input.nextDouble();

        return new Patient(appointmentManager, username, dob, gender, address, phone, email, password, checkupHistory, isAdmit, pendingFee, diagnosis, feedback);
    }

    public static Admin generateAdmin(AppointmentManager appointmentManager) {
        System.out.println("Creating a admin account: ");
        Scanner input = new Scanner(System.in);
        String username;
        LocalDate dob;
        String gender;
        String address;
        String phone;
        String email;
        String password;

        while(true) {
            System.out.print("Enter Username: ");
            username = input.nextLine();
            if(User.isValidName(username)) {
                break;
            }
            System.out.println("Invalid Username, please try again. Username can only contain alphabetical characters and spaces.");
        }

        // date of birth entries
        System.out.println("Please enter your date of birth details.");
        System.out.print("Enter Date of Birth: ");
        int date = input.nextInt();
        System.out.print("Enter month of Birth: ");
        int month = input.nextInt();
        System.out.print("Enter year of Birth: ");
        int year = input.nextInt();
        dob = LocalDate.of(year, month, date);
        input.nextLine();

        // gender entry
        while(true) {
            System.out.print("Enter Gender: ");
            gender = input.nextLine();
            if(gender.equals("male") || gender.equals("female")) {
                break;
            }
            System.out.println("Invalid Gender, please try again. (male, female)");
        }

        // inputting address
        System.out.print("Enter Address: ");
        address = input.nextLine();

        // inputting phone
        System.out.print("Enter Phone Number: ");
        phone = input.nextLine();

        // inputting email
        while(true) {
            System.out.print("Enter Email: ");
            email = input.nextLine();
            if(User.isValidEmail(email)) {
                break;
            }
            System.out.println("Invalid Email, please try again. (e.g user123@gmail.com)");
        }

        // inputting password
        while(true) {
            System.out.print("Enter Password: ");
            password = input.nextLine();
            if(User.isValidPassword(password)) {
                break;
            }
            System.out.println("Invalid Password, please try again. Password can only contain alphanumerical characters and special characters ( @#$*&=+! ). No Spaces are allowed.");
        }
        return new Admin(appointmentManager, username, dob, gender, address, phone, email, password);
    }

    public static void main(String[] args) {
        AppointmentManager appointmentManager = new AppointmentManager(); // setting up the central Appointment manager to make the appointments consistent across all the users.
        VitalDatabase vitalDatabase = new VitalDatabase(); // setting up the central vitalDatabase
        MedicalDatabase medicalDatabase = new MedicalDatabase(); // setting up the central Medical Database
        Scanner input = new Scanner(System.in);


    }

    public static MedicalHistory generateMedicalHistory(Patient patient, Doctor doctor) {
        MedicalHistory patientHistory = new MedicalHistory();
        patientHistory.addConsultation("2024-02-10: Complained of fatigue and dizziness.");
        patientHistory.addConsultation("2024-02-20: Diagnosed with iron deficiency.");
        patientHistory.addConsultation("2024-03-05: Follow-up visit, improvement noted.");

        // Create first Prescription
        Prescription prescription1 = new Prescription(patient, doctor);
        prescription1.addMedication("Iron Supplement", "100mg", "Once a day");
        prescription1.addMedication("Vitamin C", "500mg", "Twice a day");

        // Create second Prescription
        Prescription prescription2 = new Prescription(patient, doctor);
        prescription2.addMedication("Multivitamins", "1 tablet", "Once a day");

        // Add prescriptions to the medical history
        patientHistory.addPrescription(prescription1);
        patientHistory.addPrescription(prescription2);
        return patientHistory;
    }
}