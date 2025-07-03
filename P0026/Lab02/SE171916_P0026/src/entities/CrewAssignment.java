
package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class CrewAssignment implements Serializable{
    private Flight flight;
    private ArrayList<CrewMember> crewMembers;

    public CrewAssignment(Flight flight, ArrayList<CrewMember> crewMembers) {
        this.flight = flight;
        this.crewMembers = crewMembers;
    }

    public Flight getFlight() {
        return flight;
    }

    public ArrayList<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    @Override
    public String toString() {
        return "CrewAssignment{" + "flight=" + flight + ", crewMembers=" + crewMembers + '}';
    }

}
