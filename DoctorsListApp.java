import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class DoctorsListApp {

    public static ArrayList<Doctor> doctorsList = new ArrayList<>();
    public static ArrayList<Doctor> healthCheckupDoctors = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Appointment> appointments = new ArrayList<>();
    public static User currentUser = null;
    public static Set<Doctor> assignedHealthCheckupDoctors = new HashSet<>();

    public static void main(String[] args) {
        initializeDoctors();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {  // Main application loop
                while (currentUser == null) {
                    System.out.println("\nWelcome to the Doctors List Application!");
                    System.out.println("1. Login");
                    System.out.println("2. Register");
                    System.out.println("3. Exit");
                    System.out.print("Please enter your choice: ");
                    int choice = getIntInput(scanner);
                    
                    switch (choice) {
                        case 1 -> login(scanner);
                        case 2 -> register(scanner);
                        case 3 -> {
                            System.out.println("Exiting application. \nHave A Good Day!!");
                            return;  // Exit the entire program
                        }
                        default -> System.out.println("\nInvalid choice. Please try again.");
                    }
                }

                int choice;
                do {
                    System.out.println("\nWelcome, " + currentUser.getUsername() + "!");
                    System.out.println("1. View All Doctors");
                    System.out.println("2. Search Doctor by Specialization");
                    System.out.println("3. Book an Appointment");
                    System.out.println("4. Book Full Health Checkup");
                    System.out.println("5. View My Appointments");
                    System.out.println("6. Logout");
                    System.out.print("Please enter your choice: ");
                    choice = getIntInput(scanner);
                    
                    switch (choice) {
                        case 1 -> displayDoctors();
                        case 2 -> searchBySpecialization(scanner);
                        case 3 -> bookAppointment(scanner);
                        case 4 -> bookFullHealthCheckup();
                        case 5 -> viewAppointments();
                        case 6 -> {
                            System.out.println("\nLogging out...\n");
                            currentUser = null;  // Log out and return to login menu
                        }
                        default -> System.out.println("\nInvalid choice. Please try again.\n");
                    }
                } while (choice != 6);  // Loop until the user logs out
            }
        }
    }

    private static void initializeDoctors() {
        doctorsList.add(new Doctor("Dr. B. Soma Raju", "Cardiologist", true, new Hospital("Apollo", "Jubilee Hills", "Film Nagar"), 15));
        doctorsList.add(new Doctor("Dr. K. Jyothirmayi", "Neurologist", false, new Hospital("Continental Hospitals", "Gachibowli", "Nanakramguda"), 10));
        doctorsList.add(new Doctor("Dr. Amitav Ray", "Neurologist", true, new Hospital("Apollo", "Jubilee Hills", "Film Nagar"), 12));
        doctorsList.add(new Doctor("Dr. Shailender Singh", "Cardiologist", true, new Hospital("Apollo", "Jubilee Hills", "Film Nagar"), 18));
        doctorsList.add(new Doctor("Dr. Jaydev", "Dentist", true, new Hospital("Yashoda Hospitals", "Somajiguda", "Raj Bhavan Road"), 8));
        doctorsList.add(new Doctor("Dr. Sheilly Kapoor", "Dermatologist", false, new Hospital("Continental Hospitals", "Gachibowli", "Nanakramguda"), 6));
        doctorsList.add(new Doctor("Dr. K H Sancheti", "Orthopedist", true, new Hospital("Apollo", "Jubilee Hills", "Film Nagar"), 20));
        doctorsList.add(new Doctor("Dr. Nisarg Patel", "Orthopedist", true, new Hospital("Yashoda Hospitals", "Somajiguda", "Raj Bhavan Road"), 10));
        doctorsList.add(new Doctor("Dr. Monica Bambroo", "Dermatologist", true, new Hospital("Yashoda Hospitals", "Somajiguda", "Raj Bhavan Road"), 37));
        doctorsList.add(new Doctor("Dr. Smith", "Cardiologist", false, new Hospital("Apollo", "Jubilee Hills", "Film Nagar"), 22));
        doctorsList.add(new Doctor("Dr. Johnson", "Dermatologist", true, new Hospital("Continental Hospitals", "Gachibowli", "Nanakramguda"), 9));
        doctorsList.add(new Doctor("Dr. Patel", "Neurologist", false, new Hospital("Green Valley Clinic", "Ameerpet", "Ameerpet railway"), 5));
        
        healthCheckupDoctors.add(new Doctor("Dr. Anita Sharma", "General Physician", true, new Hospital("Apollo", "Jubilee Hills", "Film Nagar"), 10));
        healthCheckupDoctors.add(new Doctor("Dr. Rajesh Kumar", "General Practitioner", true, new Hospital("Yashoda Hospitals", "Somajiguda", "Raj Bhavan Road"), 12));
        healthCheckupDoctors.add(new Doctor("Dr. Neelam Gupta", "General Physician", true, new Hospital("Green Valley Clinic", "Ameerpet", "Ameerpet railway"), 8));
    }

    private static void displayDoctors() {
        System.out.println("\nList of Doctors:");
        for (int i = 0; i < doctorsList.size(); i++) {
            System.out.println((i + 1) + ". ");
            doctorsList.get(i).showDoctorInfo();
        }
    }

    private static void searchBySpecialization(Scanner scanner) {
        System.out.print("Enter specialization to search: ");
        String specialization = scanner.nextLine();
        System.out.println("\nDoctors with specialization in " + specialization + ":");
        for (Doctor doctor : doctorsList) {
            if (doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                doctor.showDoctorInfo();
            }
        }
    }

    private static void bookAppointment(Scanner scanner) {
        System.out.println("\nAvailable Doctors:");
        for (int i = 0; i < doctorsList.size(); i++) {
            Doctor doctor = doctorsList.get(i);
            System.out.println((i + 1) + ". " + doctor.getName() + 
                               "\nSpecialization: " + doctor.getSpecialization() +
                               "\nExperience: " + doctor.getExperience() + " years" +
                               (doctor.isAvailable() ? " (Available)\n" : " (Not Available)\n"));
        }
    
        System.out.print("\nSelect the doctor number you want to book an appointment with: ");
        int doctorIndex = getIntInput(scanner) - 1;
    
        if (doctorIndex >= 0 && doctorIndex < doctorsList.size()) {
            Doctor selectedDoctor = doctorsList.get(doctorIndex);
    
            if (selectedDoctor.isAvailable()) {
                // Ask for appointment type
                System.out.print("\nEnter appointment type (e.g., Consultation, Follow-up): ");
                String appointmentType = scanner.nextLine();
    
                // Ask for appointment date (not time)
                System.out.print("Enter appointment date (YYYY-MM-DD): ");
                String dateString = scanner.nextLine();
    
                LocalDate appointmentDate;
                try {
                    appointmentDate = LocalDate.parse(dateString);  // Convert string to LocalDate
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please try again with the correct format (YYYY-MM-DD).");
                    return;
                }
    
                // Check if the user already has an appointment with the selected doctor on the same date and appointment type
                for (Appointment existingAppointment : appointments) {
                    if (existingAppointment.getUsername().equals(currentUser.getUsername()) && 
                        existingAppointment.getDoctor().equals(selectedDoctor) && 
                        existingAppointment.getAppointmentDate().equals(appointmentDate) && 
                        existingAppointment.getAppointmentType().equalsIgnoreCase(appointmentType)) {
                        System.out.println("\nYou already have a " + appointmentType + " appointment with Dr. " + selectedDoctor.getName() + " on " + appointmentDate + ".");
                        return;
                    }
                }
    
                // If no conflict, proceed to book the appointment
                appointments.add(new Appointment(currentUser.getUsername(), selectedDoctor, appointmentType, currentUser.getMobileNumber(), appointmentDate));
                System.out.println("**********************************************************************************************************************************************************");
                System.out.println("\nAppointment booked successfully with " + selectedDoctor.getName() + " on " + appointmentDate + " for " + appointmentType + ".");
                System.out.println("\n**********************************************************************************************************************************************************");
            } else {
                System.out.println("\nThe selected doctor is not available. Please choose another doctor.\n");
            }
        } else {
            System.out.println("\nInvalid doctor selection.\n");
        }
    }
    
    

    private static void bookFullHealthCheckup() {
        System.out.println("Booking a full health checkup...");

        // Randomly select a doctor from the list of health checkup doctors
        Random random = new Random();
        Doctor selectedDoctor = getRandomHealthCheckupDoctor(random);

        if (selectedDoctor != null) {
            // Book the appointment with the selected doctor for a health checkup
            appointments.add(new Appointment(currentUser.getUsername(), selectedDoctor, "Full Health Checkup", currentUser.getUsername(), LocalDate.now()));
            System.out.println("\nFull health checkup booked successfully with " + selectedDoctor.getName() + "!");
        } else {
            System.out.println("Sorry, all doctors are currently unavailable for health checkups.");
        }
    }

    private static Doctor getRandomHealthCheckupDoctor(Random random) {
        // Find a doctor who has not been assigned yet
        List<Doctor> availableDoctors = new ArrayList<>();
        for (Doctor doctor : healthCheckupDoctors) {
            if (!assignedHealthCheckupDoctors.contains(doctor)) {
                availableDoctors.add(doctor);
            }
        }

        if (availableDoctors.isEmpty()) {
            return null; // No doctors available
        }

        // Randomly select a doctor
        Doctor selectedDoctor = availableDoctors.get(random.nextInt(availableDoctors.size()));

        // Mark this doctor as assigned
        assignedHealthCheckupDoctors.add(selectedDoctor);

        return selectedDoctor;
    }

    private static void viewAppointments() {
        System.out.println("\nYour Appointments:");
        boolean hasAppointments = false;
        int index = 1;
        
        for (Appointment appointment : appointments) {
            if (appointment.getUsername().equals(currentUser.getUsername())) {
                System.out.println(index + ". " + appointment);
                index++;
                hasAppointments = true;
            }
        }
        
        if (!hasAppointments) {
            System.out.println("You have no appointments booked.\n");
        } else {
            System.out.println("\nOptions: ");
            System.out.println("1. Cancel an appointment");
            System.out.println("2. Reschedule an appointment");
            System.out.println("3. Go back to the main menu");
            
            System.out.print("Please choose an option: ");
            Scanner scanner = new Scanner(System.in);
            int option = getIntInput(scanner);
            
            switch (option) {
                case 1 -> cancelAppointment(scanner);
                case 2 -> rescheduleAppointment(scanner);
                case 3 -> { }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void cancelAppointment(Scanner scanner) {
        System.out.print("Enter the appointment number to cancel: ");
        int appointmentNumber = getIntInput(scanner) - 1;
        
        if (appointmentNumber >= 0 && appointmentNumber < appointments.size()) {
            Appointment appointment = appointments.get(appointmentNumber);
            if (appointment.getUsername().equals(currentUser.getUsername())) {
                appointments.remove(appointmentNumber);
                System.out.println("***********************************");
                System.out.println("Appointment cancelled successfully.");
                System.out.println("***********************************");
            } else {
                System.out.println("You can only cancel your own appointments.");
            }
        } else {
            System.out.println("Invalid appointment number.");
        }
    }

    private static void rescheduleAppointment(Scanner scanner) {
        System.out.print("Enter the appointment number to reschedule: ");
        int appointmentNumber = getIntInput(scanner) - 1;
        
        if (appointmentNumber >= 0 && appointmentNumber < appointments.size()) {
            Appointment appointment = appointments.get(appointmentNumber);
            if (appointment.getUsername().equals(currentUser.getUsername())) {
                System.out.println("Current appointment details: ");
                System.out.println(appointment);
                
                System.out.print("Enter new date for appointment (YYYY-MM-DD): ");
                String newDate = scanner.nextLine();
                
                LocalDate newAppointmentDate = LocalDate.parse(newDate);
                
                appointment.setAppointmentDate(newAppointmentDate);
                System.out.println("***********************************");
                System.out.println("Appointment rescheduled successfully to: " + appointment.getAppointmentDate());
                System.out.println("***********************************");
            } else {
                System.out.println("You can only reschedule your own appointments.");
            }
        } else {
            System.out.println("Invalid appointment number.");
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter a username              : ");
        String username = scanner.nextLine();
        System.out.print("Enter a password (min 8 chars, must contain upper, lower, digit, and special char): ");
        String password = scanner.nextLine();
    
        if (!isValidPassword(password)) {
            System.out.println("Password must be at least 8 characters long, and contain at least one uppercase letter, one lowercase letter, one digit, and one special character. Please try again.");
            return;
        }
    
        System.out.print("Enter your mobile number      : ");
        String mobileNumber = scanner.nextLine();
        
        while (!isValidPhoneNumber(mobileNumber)) {
            System.out.println("\nInvalid mobile number. Please enter a valid 10-digit number starting with a digit between 6-9.");
            mobileNumber = scanner.nextLine();
        }
    
        // Add email validation here
        System.out.print("Enter your email address      : ");
        String email = scanner.nextLine();
    
        while (!isValidEmail(email)) {
            System.out.println("\nInvalid email address. Please enter a valid email address (e.g., example@domain.com).");
            email = scanner.nextLine();
        }
    
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("\nUsername already taken. Please try again.");
                return;
            }
        }
    
        users.add(new User(username, password, mobileNumber, email));  // Assuming the User class now has an email attribute
        System.out.println("\n***************************************************");
        System.out.println("\nRegistration successful! Thank you for registering with Doctors List Application!\n");
        System.out.println("\n***************************************************\n");
    }
    
    private static boolean isValidPassword(String password) {
        // Check if the password contains at least one uppercase letter, one lowercase letter, one digit, and one special character
        return password.length() >= 8 && 
               password.matches(".*[A-Z].*") && // Contains uppercase letter
               password.matches(".*[a-z].*") && // Contains lowercase letter
               password.matches(".*[0-9].*") && // Contains digit
               password.matches(".*[!@#$%^&*(),.?\":{}|<>].*"); // Contains special character
    }

    private static boolean isValidPhoneNumber(String mobileNumber) {
        // Remove all non-digit characters
        mobileNumber = mobileNumber.replaceAll("[^0-9]", "");

        // Validate phone number: starts with 6-9 and is exactly 10 digits
        return mobileNumber.matches("^[6-9][0-9]{9}$");
    }

    private static int getIntInput(Scanner scanner) {
        int input = -1;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.print("\nInvalid input. Please enter a number: \n");
            }
        }
        return input;
    }

    private static void forgotPassword(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.print("Enter a new password (minimum 8 characters): ");
                String newPassword = scanner.nextLine();

                if (newPassword.length() < 8) {
                    System.out.println("Password must be at least 8 characters long.");
                    return;
                }

                user.setPassword(newPassword);
                System.out.println("Password reset successful! You can now log in with your new password.");
                return;
            }
        }
        System.out.println("User not found or email does not match.");
    }


    private static void login(Scanner scanner) {
        System.out.print("Enter your username   : ");
        String username = scanner.nextLine();
        System.out.print("Enter your password   : ");
        String password = scanner.nextLine();
        
        // Check if the username and password match
        boolean userFound = false;
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Login successful!");
                return;
            }
            if (user.getUsername().equals(username)) {
                userFound = true;
            }
        }

        if (userFound) {
            System.out.println("Incorrect password. Would you like to reset it? (yes/no)");
            String resetChoice = scanner.nextLine();
            
            if (resetChoice.equalsIgnoreCase("yes")) {
                forgotPassword(scanner);
            } else {
                // Allow up to 3 attempts to re-enter the password
                int attempts = 3;
                while (attempts > 0) {
                    System.out.print("Please enter your password again: ");
                    String retryPassword = scanner.nextLine();
                    for (User user : users) {
                        if (user.getUsername().equals(username) && user.getPassword().equals(retryPassword)) {
                            currentUser = user;
                            System.out.println("Login successful!");
                            return;
                        }
                    }
                    attempts--;
                    if (attempts == 0) {
                        System.out.println("Too many failed attempts. Please try again later.");
                        return;
                    }
                    System.out.println("Incorrect password. You have " + attempts + " attempt(s) left.");
                }
            }
        } else {
            System.out.println("User not found. Would you like to register? (yes/no)");
            String registerChoice = scanner.nextLine();
            if (registerChoice.equalsIgnoreCase("yes")) {
                register(scanner);
            }
        }
    }

    private static boolean isValidEmail(String email) {
        // Simple regex for validating email format (basic check)
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        
        // Return whether the email matches the regex pattern
        return email.matches(emailRegex);
    }
    
}
