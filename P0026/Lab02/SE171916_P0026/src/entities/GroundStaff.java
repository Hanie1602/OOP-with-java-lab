package entities;

public class GroundStaff extends CrewMember{
    private String roleGroundStaff;

    public GroundStaff(String id, String name, int type,String roleGroundStaff) {
        super(id, name, type);
        this.roleGroundStaff = roleGroundStaff;
    }

    public String getRoleGroundStaff() {
        return roleGroundStaff;
    }

    public void setRoleGroundStaff(String roleGroundStaff) {
        this.roleGroundStaff = roleGroundStaff;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Role: " + roleGroundStaff;
    }
    
}
