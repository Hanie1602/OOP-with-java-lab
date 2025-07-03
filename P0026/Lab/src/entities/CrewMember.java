
package entities;

import java.io.Serializable;

public abstract class CrewMember implements Serializable{
    private String id;  
    private String name;
    private int type;   // 1 for Pilot, 2 for Flight Attendant, 3 for Ground Staff

    public CrewMember(String id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "Crew: " +
                "ID: " + id +
                "Name: " + name;
    }
}
