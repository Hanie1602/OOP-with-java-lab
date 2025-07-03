package manage;

import entities.CrewAssignment;
import entities.CrewMember;
import entities.Flight;
import entities.FlightAttendant;
import entities.GroundStaff;
import entities.Passenger;
import entities.Pilot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import my_utils.Utils;

public class FlightManagementSystem implements ICewManagement {

    String flightNumber, departureCity, destinationCity, reservationId, passengerName, contactDetails, id, name, licenseNumber, languageFlightAttendant, role;
    Date departureTime, arrivalTime, currentTime;
    int availableSeats, seatNumber, selectedSeat, type;
    int MAX_SEATS = 100;     //Maximum number of seats for each flight
    boolean isUnique;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    // Create ArrayLists for Flight and Passenger
    ArrayList<Flight> listFlight = new ArrayList<>();
    ArrayList<Passenger> listPassenger = new ArrayList<>();

    ArrayList<Pilot> listPilots = new ArrayList<>();
    ArrayList<FlightAttendant> listFlightAttendants = new ArrayList<>();
    ArrayList<GroundStaff> listGroundStaff = new ArrayList<>();
    ArrayList<CrewAssignment> crewAssignments = new ArrayList<>();
    ArrayList<CrewMember> listCrewMembers = new ArrayList<>();

    //Get Flight list, Passenger, Cew Assigment
    public ArrayList<Flight> getFlightList() {
        return this.listFlight;
    }

    public ArrayList<Passenger> getPassengerList() {
        return this.listPassenger;
    }

    public ArrayList<CrewAssignment> getCewAssigmentList() {
        return this.crewAssignments;
    }

    //Function 1: Flight schedule management 
    public void addFlight() {
        System.out.println("\n========ADD FLIGHT========");
        do {
            flightNumber = Utils.getStringreg("Enter flight number (Fxxxx): ", "F\\d{4}", "Flight Number cannot be empty.", "Invalid Flight Number format.");
            if (!Utils.isCodeUnique(flightNumber, listFlight)) {
                System.out.println("Flight with the same code already exist. Please enter a unique code");
            }
        } while (!Utils.isCodeUnique(flightNumber, listFlight));

        departureCity = Utils.getString("Enter departure city: ", "Departure City cannot be empty;");
        destinationCity = Utils.getString("Enter destination city: ", "Destination City cannot be empty");

        //----------------------
        // Get the current time
        currentTime = new Date();

        departureTime = Utils.setDate("Enter Departure Time (dd/MM/yyyy HH:mm): ", "Invalid date format.", "dd/MM/yyyy HH:mm");
        while (departureTime.before(currentTime)) {                 // Check if departure time is before the current time
            System.out.println("Departure Time cannot be before the Current Time!");
            departureTime = Utils.setDate("Enter Departure Time (dd/MM/yyyy HH:mm): ", "Invalid date format.", "dd/MM/yyyy HH:mm");
        }

        arrivalTime = Utils.setDate("Enter Arrival Time (dd/MM/yyyy HH:mm): ", "Invalid date format", "dd/MM/yyyy HH:mm");
        while (arrivalTime.before(departureTime) || arrivalTime.before(currentTime)) {
            System.out.println("Arrival Time must be after the current time and Departure Time!");
            arrivalTime = Utils.setDate("Enter Arrival Time (dd/MM/yyyy HH:mm): ", "Invalid date format", "dd/MM/yyyy HH:mm");
        }
        availableSeats = Utils.getInt("Enter available seats: ", 0);

        //Create a new Flight object and add it to the list of flights
        Flight f = new Flight(flightNumber, departureCity, destinationCity, departureTime, arrivalTime, availableSeats);
        listFlight.add(f);
        System.out.println("Flight added successfully");
    }

    //===============================================
    //Function 2: Passenger Reservation and Booking
    public void searchFlights() {
        System.out.println("\n========SEARCH FLIGHT========");
        departureCity = Utils.getString("Enter Departure City: ", "Departure City cannot be empty.");
        destinationCity = Utils.getString("Enter Destination City: ", "Destination City cannot be empty.");

        boolean availableFlightsExist = false;

        System.out.println("Available Flights:");
        for (Flight flight : listFlight) {
            if (flight.getDepartureCity().equalsIgnoreCase(departureCity)
                    && flight.getDestinationCity().equalsIgnoreCase(destinationCity)) {
                System.out.println(flight.getFlightNumber() + " - "
                        + dateFormat.format(flight.getDepartureTime()) + " -> " + dateFormat.format(flight.getArrivalTime()));
                availableFlightsExist = true;
            }
        }

        if (!availableFlightsExist) {
            System.out.println("No available flights for the specified departure and arrival cities.");
            return;     //Return and do not proceed with booking
        }

        departureTime = Utils.setDate("Enter Departure Time (dd/MM/yyyy HH:mm): ", "Invalid date format.", "dd/MM/yyy HH:mm");

        boolean availableTimeExist = false;

        System.out.println("Available Times: ");
        for (Flight time : listFlight) {
            if (time.getDepartureTime().equals(departureTime)) {
                System.out.println(time.getFlightNumber() + " - "
                        + dateFormat.format(time.getDepartureTime()) + " -> " + dateFormat.format(time.getArrivalTime()));
                availableTimeExist = true;
            }
        }

        if (!availableTimeExist) {
            System.out.println("No available times for specified departure and arrival times.");
            return;     //Return and do not proceed with booking
        }

        String selectedFlightNumber = Utils.getString("Enter Flight Number to book (Fxyzt): ", "Flight number cannot be empty.");
        Flight selectedFlight = null;
        for (Flight flight : listFlight) {
            if (flight.getFlightNumber().equalsIgnoreCase(selectedFlightNumber)) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Invalid flight number. Please try again.");
            return;
        }

        passengerName = Utils.getString("Enter Passenger Name: ", "Passenger name cannot be empty.");
        contactDetails = Utils.getString("Enter Contact Details: ", "Contact details cannot be empty.");

        //Generate a unique and random reservation ID between 4 and 9 digits
        do {
            reservationId = Utils.generateRandomReservationID(4, 9);
            isUnique = Utils.isReservationIdUnique(reservationId, listPassenger);
        } while (!isUnique);

        Passenger passenger = new Passenger(reservationId, passengerName, contactDetails, selectedFlight, seatNumber);
        listPassenger.add(passenger);

        System.out.println("Reservation successful. Your Reservation ID is: " + passenger.getReservationId());
    }

    //===================================================
    //Function 3 (Passenger Check-In and Seat Allocation)
    public void passengerCheckIn() {

        System.out.println("\n========PASSENGER CHECK-IN========");
        reservationId = Utils.getString("Enter Reservation ID: ", "Reservation ID cannot be empty.");
        Passenger passenger = Utils.findPassengerByReservationID(reservationId, listPassenger);
        if (passenger == null) {
            System.out.println("Invalid Reservation ID. Please try again.");
            return;
        }

        Flight flight = passenger.getFlight();
        availableSeats = flight.getAvailableSeats();
        if (availableSeats == 0) {
            System.out.println("No available seats for this flight.");
            return;
        }

        //Display seat availability
        System.out.println("Seat Availability for Flight " + flight.getFlightNumber() + ": ");
        Utils.displaySeatAvailability(flight, seatNumber, MAX_SEATS, listPassenger);

        selectedSeat = -1;
        //Automatically assign seats in ascending order available
        if (availableSeats > 0) {
            for (int seat = 1; seat <= MAX_SEATS; seat++) {
                boolean isOccupied = false;
                for (Passenger p : listPassenger) {
                    if (p.getFlight() == flight && p.getSeatNumber() == seat) {
                        isOccupied = true;
                        break;
                    }
                }
                if (!isOccupied) {
                    selectedSeat = seat;
                    break;
                }
            }

            if (selectedSeat != -1) {
                System.out.println("Seat " + selectedSeat + " has been automatically assigned.");
                System.out.println("Your seat number is: " + selectedSeat);
                passenger.setSeatNumber(selectedSeat);
                flight.setAvailableSeats(availableSeats - 1);
            } else {
                System.out.println("No available seats. Check-in failed.");
                return;
            }
        }

        //Allow passenger to choose an available seat if they disagree with the assigned seat
        boolean chooseOwnSeat = Utils.getYesNoAnswer("Do you want to change your seat number?(yes/no): ");
        if (chooseOwnSeat) {
            selectedSeat = getAvailableSeatChoice(flight);
            if (selectedSeat == -1) {
                System.out.println("No available seats. Check-in failed.");
                return;
            }
            // Update passenger's seat number and decrement available seats
            passenger.setSeatNumber(selectedSeat);
            flight.setAvailableSeats(availableSeats - 1);
        }

        //Generate and display boarding pass
        Utils.generateBoardingPass(passenger, flight);

        System.out.println("Check-in successful. Seat " + selectedSeat + " allocated.");
    }

    //Get the passenger's choice of an available seat
    private int getAvailableSeatChoice(Flight flight) {
        ArrayList<Integer> availableSeat = new ArrayList<>();
        for (seatNumber = 1; seatNumber <= MAX_SEATS; seatNumber++) {
            boolean isOccupied = false;
            for (Passenger passenger : listPassenger) {
                if (passenger.getFlight() == flight && passenger.getSeatNumber() == seatNumber) {
                    isOccupied = true;
                    break;
                }
            }
            if (!isOccupied) {
                availableSeat.add(seatNumber);
            }
        }

        if (availableSeat.isEmpty()) {
            return -1; // No available seats
        }

        System.out.println("Available Seats:");
        for (int i = 0; i < availableSeat.size(); i++) {
            System.out.print(availableSeat.get(i));
            if (i < availableSeat.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        return Utils.getAvailableSeatChoice("Select an available seat: ", 1, MAX_SEATS, listPassenger, flight);
    }

    //========================================================
    //Function 4: Crew Management and Administrator Access 
    @Override
    public void addPilot() {
        System.out.println("\n======== ADD PILOT ========");

        do {
            id = Utils.generateRandomReservationID(3, 7);
            isUnique = Utils.isPilotIDUnique(id, listPilots);
            System.out.println("Pilot ID: " + id);
        } while (!isUnique);

        name = Utils.getString("Enter Pilot Name: ", "Pilot Name cannot be empty.");
        licenseNumber = Utils.getString("Enter License Number: ", "License Number cannot be empty.");

        Pilot pilot = new Pilot(id, name, 1, licenseNumber);
        listPilots.add(pilot);

        System.out.println("Pilot added successfully.");
    }

    //2. Add Flight Attendant
    @Override
    public void addFlightAttendant() {
        System.out.println("\n======== ADD FLIGHT ATTENDANT ========");

        do {
            id = Utils.generateRandomReservationID(3, 7);
            isUnique = Utils.isFlightAttendantIDUnique(id, listFlightAttendants);
            System.out.println("Your Flight Attendant ID: " + id);
        } while (!isUnique);

        name = Utils.getString("Enter Flight Attendant Name: ", "Flight Attendant Name cannot be empty.");
        languageFlightAttendant = Utils.getString("Enter Language Spoken: ", "Language cannot be empty.");

        FlightAttendant flightAttendant = new FlightAttendant(id, name, 2, languageFlightAttendant);
        listFlightAttendants.add(flightAttendant);

        System.out.println("Flight Attendant added successfully.");
    }

    //3. Add Ground Staff
    @Override
    public void addGroundStaff() {
        System.out.println("\n======== ADD GROUND STAFF ========");

        do {
            id = Utils.generateRandomReservationID(3, 7);
            isUnique = Utils.isGoundStaffIDUnique(id, listGroundStaff);
            System.out.println("Your Ground Staff ID: " + id);
        } while (!isUnique);

        name = Utils.getString("Enter Ground Staff Name: ", "Ground Staff Name cannot be empty.");
        role = Utils.getString("Enter Ground Staff Role: ", "Role cannot be empty.");

        GroundStaff groundStaff = new GroundStaff(id, name, 3, role);
        listGroundStaff.add(groundStaff);
        System.out.println("Ground Staff added successfully.");
    }

    //4. Delete Filight
    @Override
    public void deleteFlight() {
        System.out.println("\n========DELETE FLIGHT========");
        if (listFlight.isEmpty()) {
            System.out.println("No flights to delete.");
            return;
        }

        String flightNumberToDelete = Utils.getString("Enter the flight number to delete (Fxxxx): ", "Invalid flight number format.");
        Flight flightToDelete = null;

        for (Flight flight : listFlight) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumberToDelete)) {
                flightToDelete = flight;
                break;
            }
        }

        if (flightToDelete == null) {
            System.out.println("Flight not found. Please enter a valid flight number.");
        } else {
            listFlight.remove(flightToDelete);
            System.out.println("Flight " + flightToDelete.getFlightNumber() + " has been deleted.");
        }
    }

    //5. Display all lists of flights and passengers
    @Override
    public void displayAllLists() {
        System.out.println("=== List of Flights ===");

        // Sort the list of flights by departure time in decreasing order
        listFlight.sort((flight1, flight2) -> flight2.getDepartureTime().compareTo(flight1.getDepartureTime()));

        for (Flight flight : listFlight) {
            System.out.println(flight);
        }

        System.out.println("\n=== List of Passengers ===");
        for (Passenger passenger : listPassenger) {
            System.out.println(passenger);
        }
    }

    //6: Edit Flight Time
    @Override
    public void editFlightTime() {
        System.out.println("\n========EDIT FLIGHT TIME========");

        if (listFlight.isEmpty()) {
            System.err.println("No flights to edit.");
            return;
        }

        String flightNumberToEdit = Utils.getString("Enter the flight number to edit (Fxxxx): ", "Invalid flight number format.");
        Flight flightToEdit = null;

        for (Flight flight : listFlight) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumberToEdit)) {
                flightToEdit = flight;
                break;
            }
        }

        if (flightToEdit == null) {
            System.err.println("Flight not found. Please enter a valid flight number.");
            return;
        }

        currentTime = new Date();  // Initialize the current time

        // Prompt the user for two options: change arrival time or change departure time
        int choice = Utils.checkChoice("1. Change Arrival Time \n2. Change Departure Time \nChoose an option: ", 1, 2);

        if (choice == 1) {
            // User wants to change the arrival time
            Date newDepartureTime = flightToEdit.getDepartureTime();
            Date newArrivalTime = Utils.setDate("Enter new Arrival Time (dd/MM/yyyy HH:mm): ", "Invalid date format", "dd/MM/yyyy HH:mm");
            while (newArrivalTime.before(newDepartureTime) || newArrivalTime.before(flightToEdit.getDepartureTime())) {
                System.out.println("Arrival Time must be after the new Departure Time and the old Departure Time!");
                newArrivalTime = Utils.setDate("Enter new Arrival Time (dd/MM/yyyy HH:mm): ", "Invalid date format", "dd/MM/yyyy HH:mm");
            }
            flightToEdit.setArrivalTime(newArrivalTime);
            System.out.println("Arrival time updated successfully.");
        } else if (choice == 2) {
            // User wants to change the departure time
            Date newDepartureTime = Utils.setDate("Enter new Departure Time (dd/MM/yyyy HH:mm): ", "Invalid date format.", "dd/MM/yyyy HH:mm");
            while (newDepartureTime.before(currentTime)) {
                System.out.println("Departure Time cannot be before the Current Time!");
                newDepartureTime = Utils.setDate("Enter new Departure Time (dd/MM/yyyy HH:mm): ", "Invalid date format.", "dd/MM/yyyy HH:mm");
            }

            Date newArrivalTime = flightToEdit.getArrivalTime();
            while (newArrivalTime.before(newDepartureTime) || newArrivalTime.before(flightToEdit.getDepartureTime())) {
                System.out.println("Arrival Time must be after the new Departure Time and the old Departure Time!");
                newArrivalTime = Utils.setDate("Enter new Arrival Time (dd/MM/yyyy HH:mm): ", "Invalid date format", "dd/MM/yyyy HH:mm");
            }
            flightToEdit.setDepartureTime(newDepartureTime);
            System.out.println("Departure time updated successfully.");
        }
    }

    // Function to assign crew members to a flight
    @Override
    public void assignCrewToFlight() {

    }
}
