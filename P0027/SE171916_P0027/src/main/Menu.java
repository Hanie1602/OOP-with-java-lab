package main;

import file.FunctionFile;
import manage.VehicleManagement;
import my_utils.Utils;

public class Menu {
    VehicleManagement vehicle = new VehicleManagement();
    FunctionFile file = new FunctionFile();
    
    String fileName = "vehicle.dat";

    public void mainMenu() {
        file.loadDataFromFile(vehicle.getVehicleList(), fileName);
        while (true) {
            System.out.println("\n==================================================");
            System.out.println("==========VEHICLE MANAGEMENT SYSTEM MENU==========");
            System.out.println("==================================================");
            System.out.println("1. Add new vehicle. ");
            System.out.println("2. Check exits Vehicle. ");
            System.out.println("3. Update vehicle. ");
            System.out.println("4. Delete vehicle. ");
            System.out.println("5. Search vehicle. ");
            System.out.println("6. Display all Vehecle ");
            System.out.println("7. Save all vehicles to file");
            System.out.println("8. Print all vehicle from the file");
            System.out.println("9. Quit");
            int choice = Utils.checkChoice("Enter your choice: ", 1, 9);

            switch (choice) {
                case 1:
                    vehicle.addNewVehicle();
                    break;
                case 2:
                    vehicle.checkToExistVehicle();
                    break;
                case 3:
                    vehicle.updateVehicle();
                    break;
                case 4:
                    vehicle.deleteVehicle();
                    break;
                case 5:
                    searchVehicle();
                    break;
                case 6:
                    displayVehicleList();
                    break;
                case 7:
                    saveDateToFile();
                    break;
                case 8:
                    printToFile();
                    break;
                case 9:
                    System.out.println("Good bye");
                    System.exit(0);
            }
        }
    }

    public void searchVehicle() {
        while (true) {
            System.out.println("\n===============");
            System.out.println("Search Options:");
            System.out.println("1. Search by Name");
            System.out.println("2. Search by ID");
            System.out.println("3. Search By Type");
            System.out.println("4. Back to Main Menu");
            int choice = Utils.checkChoice("Ener your choice: ", 1, 4);

            switch (choice) {
                case 1:
                    vehicle.searchByName();
                    break;
                case 2:
                    vehicle.searchByID();
                    break;
                case 3:
                    vehicle.searchByType();
                case 4:
                    return;
            }
        }
    }
    
    public void displayVehicleList() {
        while(true){
            System.out.println("\n================");
            System.out.println("Display Options: ");
            System.out.println("1. Show all");
            System.out.println("2. Show by price");
            System.out.println("3. Back to Main Menu");
            int choice = Utils.checkChoice("Enter your choice: ", 1, 3);
            
            switch(choice){
                case 1:
                    vehicle.showAll();
                    break;
                case 2:
                    vehicle.showByPrice();
                    break;
                case 3:
                    return;
            }
        }
    }
    
    public void saveDateToFile(){
        file.saveDateToFile(vehicle.getVehicleList(), fileName);
    }
    
    public void printToFile(){
        while(true){
            System.out.println("\n===============");
            System.out.println("\nPrint Options: ");
            System.out.println("1. Print All File Vehicle From File");
            System.out.println("2. Print Vehicles By Year From File");
            System.out.println("3. Back to Main Menu");
            int choice = Utils.checkChoice("Enter your choice: ", 1, 3);
            
            switch(choice){
                case 1:
                    file.printAllFileVehiclesFromFile(vehicle.getVehicleList(), fileName);
                    break;
                case 2:
                    file.printVehiclesByYearFromFile(vehicle.getVehicleList(), fileName);
                    break;
                case 3:
                    return;
            }
        }
        
    }
}
