package main;

import file.FunctionFile;
import manage.FlightManagementSystem;
import my_utils.Utils;

public class FunctionMenu {
    FlightManagementSystem flight = new FlightManagementSystem();
    FunctionFile file = new FunctionFile();

    String filename = "flights.dat";
    String flightmanage = "flight_management.txt";
    
    public void mainMenu(){
        file.loadDataFromFile(flight.getFlightList(), flight.getPassengerList(), flight.getCewAssigmentList(), filename);
        while (true) {
            System.out.println("\n");
            System.out.println("Airline Management System Menu: ");
            System.out.println("1. Flight schedule management");
            System.out.println("2. Passenger Reservation and Booking ");
            System.out.println("3. Passenger Check-In and Seat Allocation and Availability ");
            System.out.println("4. Crew Management and Administrator Access ");
            System.out.println("5. Save to file");
            System.out.println("6. Print all lists from file ");
            System.out.println("7. Exit");
            int choice = Utils.checkChoice("Enter your choice: ", 1, 7);

            switch (choice) {
                case 1:
                    flight.addFlight();
                    break;
                case 2:
                    flight.searchFlights();
                    break;
                case 3:
                    flight.passengerCheckIn();
                    break;
                case 4:
                    manageCrew();
                    break;
                case 5:
                    saveDataToFile();
                    System.out.println("Data saved to file.");
                    break;
                case 6:
                    printAllFile();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    System.exit(0);
            }
        }
    }

    private void manageCrew() {
        while (true) {
            System.out.println("\n======== CREW MANAGEMENT ========");
            System.out.println("1. Add Pilot");
            System.out.println("2. Add Flight Attendant");
            System.out.println("3. Add Ground Staff");
            System.out.println("4. Assign Crew to Flight");
            System.out.println("5. ");
            System.out.println("6. Display all lists of flights and passengers");
            System.out.println("7. Back to Main Menu");

            int choice = Utils.checkChoice("Select an option: ", 1, 7);

            switch (choice) {
                case 1:
                    flight.addPilot();
                    break;
                case 2:
                    flight.addFlightAttendant();
                    break;
                case 3:
                    flight.addGroundStaff();
                    break;
                case 4:
                    flight.assignCrewToFlight();
                    break;
                case 5:
                    //viewCrewAssignments();
                    break;
                case 6:
                    flight.displayAllLists();
                    break;
                case 7:
                    return; // Return to the main menu
            }
        }
    }

    private void saveDataToFile() {
        file.saveDataToFile(flight.getFlightList(), flight.getPassengerList(), flight.getCewAssigmentList(), filename, flightmanage);
    }
    
    private void printAllFile() {
        file.printAllListsToFile(flight.getFlightList(), flight.getPassengerList(), flight.getCewAssigmentList(), flightmanage);
    }
}
