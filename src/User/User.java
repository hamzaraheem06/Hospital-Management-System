package User;

import Appointment.Appointment;

import java.util.Random; // using the Random class to generate a user id
import java.util.regex.Pattern; // using the Pattern class for credentials and information matching
import java.time.Period; // using the Calendar class for age calculation
import java.time.LocalDate;

// base class for application users
// Identifiers (variables and method names) are chosen in a sense that they are self-explanatory
public abstract class User {
    private static final Random rand = new Random(); // instantiating the Random class object
    // patterns for authentication
    private static final String NAME_PATTERN = "^[a-zA-Z]{2,}(\\s[a-zA-Z]{2,})+$";
    private static final String MAIL_PATTERN = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+$";
    private static final String PASS_PATTERN = "^[a-zA-Z0-9@#$*&=+!]{8,20}+$";
    private static final char[] characters = {'a', 'b', 'c', 'd', 'e', 'f','A', 'B', 'C', 'D', 'E', 'F', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    // declaring all the user attributes, keeping them private to ensure security and applying the encapsulation principle of OOP
    private final String userID = randomIdGenerator();
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private String password;

    // defining the constructor;
    User(String name, LocalDate dob, String gender, String address, String phone, String email, String password) {
        setName(name);
        setDateOfBirth(dob);
        setGender(gender);
        setAddress(address);
        setPhone(phone);
        setEmail(email);
        setPassword(password);
        // using setters to validate entries
    }

    // defining all the setters with validations
    public void setName(String name) {
        if(!isValidName(name)) {
            System.out.println("Invalid name!");
            this.name = null;
            return;
        }
        this.name = name;
    }

    public void setDateOfBirth(LocalDate birthDate) { // method to calculate and set age of the user
        if (birthDate == null) {
            System.out.println("Invalid age!");
            return;
        }
        this.dateOfBirth = birthDate;
    }

    public void setGender(String gender) {
        if(gender.equals("male") || gender.equals("female")) { // let's be real there are only two genders
            this.gender = gender;
        }
        else {
            System.out.println("Invalid gender!"); // get this mentally ill wierdo
            this.gender = null;
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        if(!isValidEmail(email)) {
            System.out.println("Invalid email!");
            this.email = null;
            return;
        }
        this.email = email;
    }

    public void setPassword(String password) {
        if(!isValidPassword(password)) {
            System.out.println("Invalid password!");
            this.password = null;
            return;
        }
        this.password = password;
    }

    // defining all the getters
    public String getUserID() {
        return userID;
    }
    public String getName() {
        return name;
    }
    public String getGender() {
        return gender;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public Period getAge() {
        return Period.between(dateOfBirth, LocalDate.now());
    }

    // defining user class methods
    public void changeUsername(String name) {
        if(!isValidName(name)) {
            System.out.println("Invalid name!");
            return;
        }
        this.name = name;
    }

    public void changePassword(String password) {
        if(!isValidPassword(password)) {
            System.out.println("Invalid password!");
            return;
        }
        this.password = password;
    }

    // validation and generation functions
    public static String randomIdGenerator() {
        String id = "";
        for(int i = 0; i < 8; i++) {
            id += characters[rand.nextInt(characters.length)];
        }
        return id;
    }

    // declaring abstract methods
    public abstract void scheduleAppointment(Doctor doctor, Patient patient, LocalDate date);

    public abstract void cancelAppointment(Appointment appointment);

    public static boolean isValidName(String name) {
        return Pattern.matches(NAME_PATTERN, name);
    }

    public static boolean isValidEmail(String email) {
        return Pattern.matches(MAIL_PATTERN, email);
    }

    public static boolean isValidPassword(String password) {
        return Pattern.matches(PASS_PATTERN, password);
    }

    // overriding the toString method to display the user
    @Override
    public String toString() {
        return String.format("UserID: %S\tName: %s\tDate Of Birth: %s\tGender: %s\tAddress: %s\tPhone: %s\tEmail: %s\tPassword: %s", userID, name, dateOfBirth, gender, address, phone, email, password);
    }
}