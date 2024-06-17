
package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vehicle implements Serializable {
    private String vehicleID;
    private String vehicleName;
    private String vehicleColor;
    private double vehiclePrice;
    private String vehicleBrand;
    private String type;
    private Date productYear;

    public Vehicle(String vehicleID, String vehicleName, String vehicleColor, double vehiclePrice, String vehicleBrand, String type, Date productYear) {
        this.vehicleID = vehicleID;
        this.vehicleName = vehicleName;
        this.vehicleColor = vehicleColor;
        this.vehiclePrice = vehiclePrice;
        this.vehicleBrand = vehicleBrand;
        this.type = type;
        this.productYear = productYear;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getProductYear() {
        return productYear;
    }

    public void setProductYear(Date productYear) {
        this.productYear = productYear;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        return "\nVehicle ID: " + vehicleID +
                " ,Name: " + vehicleName +
                " ,Color: " + vehicleColor +
                " ,Price: " + vehiclePrice +
                " ,Brand: " + vehicleBrand +
                " ,Type: " + type +
                " ,Product Year: " + dateFormat.format(productYear);
    }
}
