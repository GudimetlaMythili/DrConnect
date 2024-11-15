import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Appointment {
    public String username;
    public Doctor doctor;
    public String appointmentType;
    public String mobileNumber;
    public LocalDate appointmentDate;  // Only store the date, not the time

    public Appointment(String username, Doctor doctor, String appointmentType, String mobileNumber, LocalDate appointmentDate) {
        this.username = username;
        this.doctor = doctor;
        this.appointmentType = appointmentType;
        this.mobileNumber = mobileNumber;
        this.appointmentDate = appointmentDate;
    }

    public String getUsername() {
        return username;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    @Override
    public String toString() {
        // Use the DateTimeFormatter to display the date in a readable format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "Doctor: Dr. " + doctor.getName() + "\nSpecialization: " + doctor.getSpecialization() +
               "\nAppointment Type: " + appointmentType + "\nAppointment Date: " + appointmentDate.format(formatter);
    }

    public String getAppointmentType() {
        return appointmentType;
    }
}
