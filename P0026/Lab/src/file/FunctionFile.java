package file;

import entities.CrewAssignment;
import entities.Flight;
import entities.Passenger;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FunctionFile {

    // Save data from the list to the file
    public void saveDataToFile(ArrayList<Flight> listFlight, ArrayList<Passenger> listPassenger, ArrayList<CrewAssignment> crewAssignments, String fName, String txtName) {
        if (listFlight.isEmpty() && listPassenger.isEmpty() && crewAssignments.isEmpty()) {
            System.out.println("Empty lists.");
            return;
        }

        try {
            FileOutputStream f = new FileOutputStream(fName); // Write
            ObjectOutputStream fo = new ObjectOutputStream(f); // WriteObject()
            PrintWriter flightManageWriter = new PrintWriter(txtName); // Write to flight_management.txt

            // Save flights
            for (Flight flight : listFlight) {
                fo.writeObject(flight);
                flightManageWriter.println(flight.toString());
            }

            // Save passengers
            for (Passenger passenger : listPassenger) {
                fo.writeObject(passenger);
                flightManageWriter.println(passenger.toString());
            }

            // Save crew assignments
            for (CrewAssignment assignment : crewAssignments) {
                fo.writeObject(assignment);
                flightManageWriter.println(assignment.toString());
            }

            fo.close();
            f.close();
            flightManageWriter.close();
            System.out.println("Save successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Load data from file into the list
    public void loadDataFromFile(ArrayList<Flight> listFlight, ArrayList<Passenger> listPassenger, ArrayList<CrewAssignment> crewAssignments, String fName) {
        try {
            File f = new File(fName);
            if (!f.exists()) {
                return;         //Check if the file with the given name exists
            }
            FileInputStream fi = new FileInputStream(f);            //read
            ObjectInputStream fo = new ObjectInputStream(fi);       //readObject() from the file.

            // Clear existing data
            listFlight.clear();
            listPassenger.clear();
            crewAssignments.clear();

            while (true) {
                try {
                    Object obj = fo.readObject();
                    if (obj instanceof Flight) {
                        listFlight.add((Flight) obj);
                    } else if (obj instanceof Passenger) {
                        listPassenger.add((Passenger) obj);
                    } else if (obj instanceof CrewAssignment) {
                        crewAssignments.add((CrewAssignment) obj);
                    }
                } catch (EOFException e) {
                    break; // Reached the end of the file
                }
            }

            fo.close();
            fi.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printAllListsToFile(ArrayList<Flight> listFlight, ArrayList<Passenger> listPassenger, ArrayList<CrewAssignment> crewAssignments, String fName) {
        try {
            PrintWriter writer = new PrintWriter(fName);
            
            // Print the list of flights
            writer.println("=== List of Flights ===");
            for (Flight flight : listFlight) {
                writer.println(flight.toString());
            }
            writer.println();

            // Print the list of passengers
            writer.println("=== List of Passengers ===");
            for (Passenger passenger : listPassenger) {
                writer.println(passenger.toString());
            }
            writer.println();

            // Print the list of crew members
            writer.println("=== List of Crew Assignment ===");
            for (CrewAssignment crewAssigment : crewAssignments) {
                writer.println(crewAssigment.toString());
            }

            writer.close();
            System.out.println("All lists have been written to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to the file.");
        }
    }
}
