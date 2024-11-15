public class User {
    public String username;
    public String password;
    public String mobileNumber;
    public String email;  // New field for email address

    // Constructor with the email field
    public User(String username, String password, String mobileNumber, String email) {
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.email = email;  // Set the email
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Other existing getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
