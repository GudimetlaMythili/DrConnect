public class Doctor {
    public String name;
    public String specialization;
    public boolean isAvailable;
    public Hospital hospital;
    public int experience;

    public Doctor(String name, String specialization, boolean isAvailable, Hospital hospital, int experience) {
        this.name = name;
        this.specialization = specialization;
        this.isAvailable = isAvailable;
        this.hospital = hospital;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public int getExperience() {
        return experience;
    }

    public void showDoctorInfo() {
        System.out.println("Doctor Name     : " + name);
        System.out.println("Specialization  : " + specialization);
        System.out.println("Experience      : " + experience + " years");
        System.out.println("Availability    : " + (isAvailable ? "Available" : "Not Available"));
        hospital.showHospitalInfo();
        System.out.println("-------------------------------------------------------------------");
    }
}