
package manage;

public interface ICewManagement {
    //Function 4: Crew Management and Administrator Access 
    void addPilot();
    void addFlightAttendant();
    void addGroundStaff();
    
    //Delete Flight
    void deleteFlight();
    
    //Edit Flight Time
    void editFlightTime ();
    
    //Function to assign crew members to a flight
    void assignCrewToFlight();
    
    //Display all lists of flights and passengers
    void displayAllLists();
}
