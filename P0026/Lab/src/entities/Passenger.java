package entities;

import java.io.Serializable;

public class Passenger implements Serializable {

    private String reservationId;
    private String passengerName;
    private String contactDetails;
    private Flight flight;
    private String seatNumber;

    public Passenger(String reservationId, String passengerName, String contactDetails, Flight flight, String seatNumber) {
        this.reservationId = reservationId;
        this.passengerName = passengerName;
        this.contactDetails = contactDetails;
        this.flight = flight;
        this.seatNumber = seatNumber;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Passenger: "
                + " Reservation ID: " + reservationId
                + " ,Passenger Name: " + passengerName
                + " ,Contact Details: " + contactDetails
                + " ,Seat Number: " + seatNumber
                + " \n" + getFlight();
    }

}
