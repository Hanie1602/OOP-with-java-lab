
package entities;

public class FlightAttendant extends CrewMember{
    private String languageFlightAttendant;

    public FlightAttendant(String id, String name, int type, String languageFlightAttendant) {
        super(id, name, type);
        this.languageFlightAttendant = languageFlightAttendant;
    }

    public String getLanguageFlightAttendant() {
        return languageFlightAttendant;
    }

    public void setLanguageFlightAttendant(String languageFlightAttendant) {
        this.languageFlightAttendant = languageFlightAttendant;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Languge: " + languageFlightAttendant;
    }
    
    
}
