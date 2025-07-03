
package entities;

public class Pilot extends CrewMember{
    private String licenseNumber;

    public Pilot(String id, String name, int type, String licenseNumber) {
        super(id, name, type);
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return super.toString() +
                "License Number: " + licenseNumber;
    }
}
