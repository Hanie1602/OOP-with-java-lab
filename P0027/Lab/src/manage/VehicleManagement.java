package manage;

import entities.Vehicle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import my_utils.Utils;

public class VehicleManagement implements IVehicleManagement {

    String vehicleID, vehicleName, vehicleColor, vehicleBrand, type;
    double vehiclePrice;
    Date productYear, currentDate;

    ArrayList<Vehicle> listVehicle = new ArrayList<>();

    public ArrayList<Vehicle> getVehicleList() {
        return this.listVehicle;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

    //1. Add new vehicle
    @Override
    public void addNewVehicle() {
        System.out.println("\n=================================================");
        System.out.println("================ADD A NEW VEHICLE================");
        System.out.println("=================================================");

        do {
            vehicleID = Utils.getStringreg("Enter Vehicle ID {with 6 digits): ", "\\d{6}", "ID cannot be empty", "ID must be 6 digits");
            if (!Utils.isCodeUnique(vehicleID, listVehicle)) {
                System.out.println("Vehicle ID with the same code already exist. Please enter a unique code");
            }
        } while (!Utils.isCodeUnique(vehicleID, listVehicle));

        vehicleName = Utils.getString("Enter Vehicle Name: ", "Name cannot be empty");
        vehicleColor = Utils.getString("Enter Vehicle Color: ", "Color cannot be empty");
        vehiclePrice = Utils.getDouble("Enter Vehicle Price: ", 0);
        vehicleBrand = Utils.getString("Enter Vehicle Brand: ", "Brand cannot be empty");
        type = Utils.getString("Enter Vehicle Type: ", "Type cannot be empty.");

        currentDate = new Date();
        productYear = Utils.setDate("Enter Product Year: ", "Date cannot be empty", "yyyy");

        while (productYear.after(currentDate) || getYearFromDate(productYear) < 2022) {
            System.out.println("Product Year must be before Current Date!!");
            productYear = Utils.setDate("Enter Product Year: ", "Date cannot be empty", "yyyy");
        }

        Vehicle v = new Vehicle(vehicleID, vehicleName, vehicleColor, vehiclePrice, vehicleBrand, type, productYear);

        boolean choose = Utils.getYesNoAnswer("Are you sure you want to add the following vehicle?: ");
        if (choose) {
            listVehicle.add(v);
            System.out.println(v);
            System.out.println("Vehicle added successfully!");
        } else {
            System.out.println("Vehicles are not added");
        }
    }

    private int getYearFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    //------------------------------------------------------------------------------------------------------------
    //2. Check exits Vehicle.
    @Override
    public void checkToExistVehicle() {
        System.out.println("\n====================================================");
        System.out.println("================CHECK VERHICLE EXIST================");
        System.out.println("====================================================");
        if (listVehicle.isEmpty()) {
            System.out.println("No vehicles have been added yet.");
            return;
        }

        vehicleID = Utils.getStringreg("Enter Vehicle ID {with 6 digits): ", "\\d{6}", "ID cannot be empty", "ID must be 6 digits");

        boolean vehicleExists = false;
        for (Vehicle v : listVehicle) {
            if (v.getVehicleID().equals(vehicleID)) {
                vehicleExists = true;
                System.out.println(v);
                break;
            }
        }

        if (vehicleExists) {
            System.out.println("Existed Vehicle");
        } else {
            System.out.println("No Vehicle Found!");
        }
    }

    //------------------------------------------------------------------------------------------------------------
    //3. Update vehicle
    @Override
    public void updateVehicle() {
        System.out.println("\n==============================================");
        System.out.println("================UPDATE VEHICLE================");
        System.out.println("==============================================");

        Scanner sc = new Scanner(System.in);
        vehicleID = Utils.getStringreg("Enter Vehicle ID {with 6 digits): ", "\\d{6}", "ID cannot be empty", "ID must be 6 digits");

        //Find the vehicle with the given ID
        Vehicle vehicleToUpdate = null;
        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getVehicleID().equals(vehicleID)) {
                vehicleToUpdate = vehicle;
                System.out.println(vehicle);
                break;
            }
        }

        if (vehicleToUpdate == null) {
            System.out.println("Vehicle does not exist");
            return; //Exit the function if the vehicle doesn't exist
        }

        //Allow the user to update vehicle information
        System.out.print("Enter New Vehicle Name: ");
        String newName = sc.nextLine();
        if (newName.isEmpty()) {
            vehicleToUpdate.getVehicleName();
        } else {
            vehicleToUpdate.setVehicleName(newName);
        }

        System.out.print("Enter New Vehicle Color: ");
        String newColor = sc.nextLine();
        if (newColor.isEmpty()) {
            vehicleToUpdate.getVehicleColor();
        } else {
            vehicleToUpdate.setVehicleColor(newColor);
        }

        System.out.print("Enter New Price: ");
        String newPriceInputStr = sc.nextLine();
        if (!newPriceInputStr.isEmpty()) {
            double newPriceInput = Double.parseDouble(newPriceInputStr);
            if (newPriceInput >= 0) {
                vehicleToUpdate.setVehiclePrice(newPriceInput);
            } else {
                System.out.println("Price cannot be negative. Keeping the current price.");
            }
        }

        System.out.print("Enter New Vehicle Brand: ");
        String newBrand = sc.nextLine();
        if (newBrand.isEmpty()) {
            vehicleToUpdate.getVehicleBrand();
        } else {
            vehicleToUpdate.setVehicleBrand(newBrand);
        }

        System.out.print("Enter New Vehicle Type: ");
        String newType = sc.nextLine();
        if (newType.isEmpty()) {
            vehicleToUpdate.getType();
        } else {
            vehicleToUpdate.setType(newType);
        }

        System.out.print("Enter New Product Year: ");
        String newDate = sc.nextLine();
        if (!newDate.isEmpty()) {
            try {
                Date newProductYear = dateFormat.parse(newDate);
                vehicleToUpdate.setProductYear(newProductYear);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Keeping the current product year.");
            }
        } else {
            System.out.println("No changes were made to the product year.");
        }

        System.out.println(vehicleToUpdate);
        System.out.println("Vehicle Product Year successfully upgraded");

    }

    //------------------------------------------------------------------------------------------------------------
    //4. Delete vehicle.
    @Override
    public void deleteVehicle() {
        System.out.println("\n==============================================");
        System.out.println("================DELETE VEHICLE================");
        System.out.println("==============================================");
        if (listVehicle.isEmpty()) {
            System.out.println("No vehicles have been added yet");
            return;
        }

        vehicleID = Utils.getStringreg("Enter Vehicle ID {with 6 digits): ", "\\d{6}", "ID cannot be empty", "ID must be 6 digits");

        Vehicle vehicle = null;
        for (Vehicle v : listVehicle) {
            if (v.getVehicleID().equals(vehicleID)) {
                vehicle = v;
                break;
            }
        }

        if (vehicle == null) {
            System.out.println("Vehicle does not exist");
            return;
        }

        boolean choose = Utils.getYesNoAnswer("Are you sure you want to delete the following vehicle?: ");
        if (choose) {
            System.out.println(vehicle);
            listVehicle.remove(vehicle);
            System.out.println("Vehicle deleted successfully!");
        } else {
            System.out.println("No changes were made. Vehicle not deleted.");
        }
    }

    //------------------------------------------------------------------------------------------------------------
    //5.Search Vehicle
    @Override
    public void searchByName() {
        System.out.println("\n======================================================");
        System.out.println("================SEARCH VEHICLE BY NAME================");
        System.out.println("======================================================");
        if (listVehicle.isEmpty()) {
            System.out.println("No vehicles have been added yet.");
            return;
        }

        vehicleName = Utils.getString("Enter the name or part of the name to search for:", "Vehicle Name cannot be empty").toLowerCase();
        ArrayList<Vehicle> searchResults = new ArrayList<>();

        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getVehicleName().toLowerCase().contains(vehicleName)) {
                searchResults.add(vehicle);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No matching vehicles found.");
        } else {
            System.out.println("Search Results (sorted by name in descending order):");
            searchResults.sort(Comparator.comparing(Vehicle::getVehicleName).reversed());
            for (Vehicle vehicle : searchResults) {
                System.out.println(vehicle);
            }
        }

    }

    @Override
    public void searchByID() {
        System.out.println("\n====================================================");
        System.out.println("================SEARCH VEHICLE BY ID================");
        System.out.println("====================================================");
        if (listVehicle.isEmpty()) {
            System.out.println("No vehicles have been added yet");
            return;
        }

        vehicleID = Utils.getStringreg("Enter Vehicle ID (with 6 digits): ", "\\d{6}", "Vehicle ID cannot be empty", "ID must be 6 digits");
        Vehicle foundVehicle = null;
        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getVehicleID().equals(foundVehicle)) {
                foundVehicle = vehicle;
                break;
            }
        }

        if (foundVehicle == null) {
            System.out.println("No matching vehicle found");
        } else {
            System.out.println("Matching Vehicle:");
            System.out.println(foundVehicle);
        }
    }

    //------------------------------------------------------------------------------------------------------------
    //6. Display vehicle list
    @Override
    public void showAll() {
        System.out.println("\n========================================");
        System.out.println("================SHOW ALL================");
        System.out.println("========================================");

        if (listVehicle.isEmpty()) {
            System.out.println("No vehicles have been added yet");
            return;
        }

        for (Vehicle vehicle : listVehicle) {
            System.out.println(vehicle);
        }
    }

    @Override
    public void showByPrice() {
        System.out.println("\n=============================================");
        System.out.println("================SHOW BY PRICE================");
        System.out.println("=============================================");

        if (listVehicle.isEmpty()) {
            System.out.println("No vehicle have been added yet");
            return;
        }

        vehiclePrice = Utils.getDouble("Enter Vehicle Price: ", 0);
        ArrayList<Vehicle> priceVehicles = new ArrayList<>();
        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getVehiclePrice() <= vehiclePrice) {
                priceVehicles.add(vehicle);
            }
        }

        if (priceVehicles.isEmpty()) {
            System.out.println("No vehicles found with a price less than or equal to " + vehiclePrice);
        } else {
            priceVehicles.sort((v1, v2) -> Double.compare(v2.getVehiclePrice(), v1.getVehiclePrice()));
            for (Vehicle vehicle : priceVehicles) {
                System.out.println("Vehicles with Price Less Than or Equal");
                System.out.println(vehicle);
            }
        }
    }

}
