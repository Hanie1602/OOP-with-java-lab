package file;

import entities.Vehicle;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import my_utils.Utils;

public class FunctionFile {

    public void saveDateToFile(ArrayList<Vehicle> listVehicle, String fName) {
        if (listVehicle.isEmpty()) {
            System.out.println("Empty List");
            return;
        }

        try {
            FileOutputStream fo = new FileOutputStream(fName);
            ObjectOutputStream oo = new ObjectOutputStream(fo);

            for (Vehicle vehicle : listVehicle) {
                oo.writeObject(vehicle);
            }

            oo.close();
            fo.close();
            System.out.println("Save successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromFile(ArrayList<Vehicle> listVehicle, String fName) {
        try {
            File f = new File(fName);
            if (!f.exists()) {
                return;
            }

            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream oi = new ObjectInputStream(fi);

            listVehicle.clear();

            while (true) {
                try {
                    Object obj = oi.readObject();
                    if (obj instanceof Vehicle) {
                        listVehicle.add((Vehicle) obj);
                    }
                } catch (EOFException e) {
                    break;
                }
            }

            oi.close();
            fi.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printAllFileVehiclesFromFile(ArrayList<Vehicle> listVehicle, String fName) {
        System.out.println("\n============================================================");
        System.out.println("================PRINT ALL VEHICLES FROM FILE================");
        System.out.println("============================================================");

        // Load vehicle data from the file
        loadDataFromFile(listVehicle, fName);

        if (listVehicle.isEmpty()) {
            System.out.println("No vehicles found in the program data");
        } else {
            System.out.println("All Vehicles from the Program Data: ");
            for (Vehicle vehicle : listVehicle) {
                System.out.println(vehicle);
            }
        }
    }

    public void printVehiclesByYearFromFile(ArrayList<Vehicle> listVehicle, String fName) {
        System.out.println("\n================================================================");
        System.out.println("================PRINT VEHICLES BY YEAR FROM FILE================");
        System.out.println("================================================================");

        // Load vehicle data from the file
        loadDataFromFile(listVehicle, fName);

        if (listVehicle.isEmpty()) {
            System.out.println("No vehicles found in the file.");
            return;
        }

        boolean check = true;
        Collections.sort(listVehicle, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle t, Vehicle t1) {
                return t1.getProductYear().compareTo(t.getProductYear());
            }
        });

        Date productYear = Utils.setDate("Enter Product Year: ", "Product Year cannot be empty", "yyyy");
        for (Vehicle v : listVehicle) {
            if (v.getProductYear().equals(productYear) || v.getProductYear().after(productYear)) {
                System.out.println(v);
                check = false;
            }
        }
        if (check) {
            System.out.println("Not founded");
        }
    }
}
