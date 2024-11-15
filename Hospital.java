// Hospital.java
public class Hospital {
    public String name;
    public String location;
    public String area;

    public Hospital(String name, String location, String area) {
        this.name = name;
        this.location = location;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getArea() {
        return area;
    }

    public void showHospitalInfo() {
        System.out.println("Hospital Name   : " + name);
        System.out.println("Location        : " + location);
        System.out.println("Area            : " + area);
    }
}