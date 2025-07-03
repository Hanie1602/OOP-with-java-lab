package my_utils;

import entities.CrewMember;
import entities.Flight;
import entities.FlightAttendant;
import entities.GroundStaff;
import entities.Passenger;
import entities.Pilot;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Utils {

    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static String getString(String welcome, String empty) {
        boolean check = true;
        String result = " ";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            result = sc.nextLine();

            if (result.isEmpty()) {
                System.err.println(empty);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getStringreg(String welcome, String pattern, String empty, String msgreg) {
        boolean check = true;
        String result = " ";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.err.println(empty);
            } else if (!result.matches(pattern)) {
                System.err.println(msgreg);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static int getInt(String welcome, int min) {
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must be large than " + min);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static int getSeat(String prompt, int min, int max) {
        boolean check = false;
        int seatNumber = 0;
        Scanner sc = new Scanner(System.in);

        while (!check) {
            try {
                System.out.print(prompt);
                String userInput = sc.nextLine().trim();

                //Ensure that the input is not empty
                if (userInput.isEmpty()) {
                    System.err.println("Please enter a valid seat number.");
                    continue;
                }

                //Extract the numeric part of the seat number (e.g., "1A" -> 1)
                int extractedSeatNumber = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));

                if (extractedSeatNumber < min || extractedSeatNumber > max) {
                    System.err.println("Seat number must be between " + min + " and " + max);
                } else {
                    seatNumber = extractedSeatNumber;
                    check = true;
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a valid seat number.");
            }
        }

        return seatNumber;
    }

    public static int checkChoice(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.err.println("Number must be large than: 0 ");
                } else if (number > max) {
                    System.err.println("Number must be less than: " + max);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.err.println("Input number!!!");
            }
        } while (check || number < min || number > max);
        return number;
    }

    //==================================CHECK CODE/ID IS UNIQUE OR NOT UNIQUE==================================
    //Check Code
    public static boolean isCodeUnique(String code, ArrayList<Flight> flightNumber) {
        for (Flight f : flightNumber) {
            if (((Flight) f).getFlightNumber().equals(code)) {
                return false;           //Code is not unique
            }
        }
        return true;                    //Code is unique
    }

    //Check Reservation ID
    public static boolean isReservationIdUnique(String reservationId, ArrayList<Passenger> passengerNumber) {
        for (Passenger p : passengerNumber) {
            if (p.getReservationId().equals(reservationId)) {
                return false; // Not unique
            }
        }
        return true; // Unique
    }

    //Check Flight Attendant ID
    public static boolean isFlightAttendantIDUnique(String ID, ArrayList<FlightAttendant> listFlightAttendants) {
        for (FlightAttendant fl : listFlightAttendants) {
            if (fl.getId().equals(ID)) {
                return false;
            }
        }
        return true;
    }

    //Check Pilot ID
    public static boolean isPilotIDUnique(String ID, ArrayList<Pilot> listPilot) {
        for (Pilot p : listPilot) {
            if (p.getId().equals(ID)) {
                return false;
            }
        }
        return true;
    }

    //Check Ground Staff ID
    public static boolean isGoundStaffIDUnique(String ID, ArrayList<GroundStaff> listGroundStaff) {
        for (GroundStaff grs : listGroundStaff) {
            if (grs.getId().equals(ID)) {
                return false;
            }
        }
        return true;
    }

    //==============================================================================================================
    //Check date;
    public static Date setDate(String welcome, String error, String format) {
        boolean errorCheking;
        Date date = null;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print(welcome);
            errorCheking = false;
            String input = sc.nextLine();
            try {
                date = dateFormat.parse(input);
            } catch (ParseException e) {
                System.out.println(error);
                errorCheking = true;
            }
        } while (errorCheking);
        return date;
    }

    //Yes or No Answer
    public static boolean getYesNoAnswer(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.err.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }
    //======================================================RESERVATION ID===============================================
    //Random reservation ID with a specified length

    public static String generateRandomReservationID(int minLength, int maxLength) {
        Random rand = new Random();
        int length = rand.nextInt(maxLength - minLength + 1) + minLength;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(rand.nextInt(10)); // Generates random digits
        }
        return sb.toString();
    }

    //Find a passenger by Reservation ID
    public static Passenger findPassengerByReservationID(String reservationID, ArrayList<Passenger> listPassenger) {
        for (Passenger passenger : listPassenger) {
            if (passenger.getReservationId().equals(reservationID)) {
                return passenger;
            }
        }
        return null;
    }

    //=============================================================SEAT=======================================================
    //Display seat availability for a flight
    public static void displaySeatAvailability(Flight flight, int seatNumber, int max, ArrayList<Passenger> listPassenger) {
        System.out.println("Seat Availability:");
        for (seatNumber = 1; seatNumber <= max; seatNumber++) {
            String seatStatus = "Empty";
            for (Passenger passenger : listPassenger) {
                if (passenger.getFlight() == flight && passenger.getSeatNumber().equals(seatNumber)) {
                    seatStatus = "Occupied";
                    break;
                }
            }
            System.out.println("Seat " + seatNumber + ": " + seatStatus);
        }
    }

    //Check Seats are available
    public static String getAvailableSeatChoice(String prompt, int min, int max, ArrayList<Passenger> listPassenger, Flight flight) {
        boolean check = true;
        String seatNumber = " ";
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(prompt);
                seatNumber = sc.nextLine();

                int seatNumberAsInt;
                try {
                    seatNumberAsInt = Integer.parseInt(seatNumber);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input. Please enter a valid seat number.");
                    continue;
                }

                if (seatNumberAsInt < min || seatNumberAsInt > max) {
                    System.err.println("Seat number must be between " + min + " and " + max);
                } else if (isSeatOccupied(seatNumber, listPassenger, flight)) {
                    System.err.println("Seat " + seatNumber + " is already booked. Please choose another seat.");
                } else {
                    check = false;
                }
            } catch (Exception e) {
                System.err.println("Invalid input. Please enter a valid seat number.");
            }
        } while (check || isSeatOccupied(seatNumber, listPassenger, flight));
        return seatNumber;
    }

    // Check seat is occupied
    private static boolean isSeatOccupied(String seatNumber, ArrayList<Passenger> listPassenger, Flight flight) {
        for (Passenger passenger : listPassenger) {
            if (passenger.getFlight() == flight && passenger.getSeatNumber().equals(seatNumber)) {
                return true;
            }
        }
        return false;
    }

    //Generate and display the boarding pass
    public static void generateBoardingPass(Passenger passenger, Flight flight) {
        System.out.println("======== BOARDING PASS ========");
        System.out.println("Flight Number: " + flight.getFlightNumber());
        System.out.println("Departure City: " + flight.getDepartureCity());
        System.out.println("Destination City: " + flight.getDestinationCity());
        System.out.println("Departure Time: " + dateFormat.format(flight.getDepartureTime()));
        System.out.println("Passenger Name: " + passenger.getPassengerName());
        System.out.println("Seat Number: " + passenger.getSeatNumber());
        System.out.println("================================");
    }

}
