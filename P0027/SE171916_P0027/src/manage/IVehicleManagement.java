
package manage;

public interface IVehicleManagement {
    //1. Add new vehicle
    void addNewVehicle();
    
    //2. Check exits Vehicle.
    void checkToExistVehicle();
    
    //3. Update vehicle
    void updateVehicle();
    
    //4. Delete vehicle.
    void deleteVehicle();
    
    //5.Search Vehicle
    void searchByName();
    void searchByID();
    void searchByType();
    
    //6. Display vehicle list
    void showAll();
    void showByPrice();
}
