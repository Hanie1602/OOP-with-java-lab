package file;

import entities.Product;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FunctionFile {

  public void loadDataFromFile(ArrayList<Product> list, String fName){      //list: An ArrayList containing the Product objects to be saved.
        try{
            File f = new File(fName);
            if(!f.exists()) return;                                 //checks if the file with the given name exists 
            FileInputStream fi = new FileInputStream(f);            //read
            ObjectInputStream fo = new ObjectInputStream(fi);       //readObject() from the file.
            Product p;
            while((p=(Product)(fo.readObject())) != null){          
                list.add(p);
            }
            fo.close();fi.close(); 
        } catch(IOException | ClassNotFoundException e){
           
        }
    }
    public void saveToFile(ArrayList<Product> list, String fName){
        if(list.isEmpty()){
            System.out.println("Empty list.");
            return;
        }
        try{
            FileOutputStream f = new FileOutputStream(fName);       //write
            ObjectOutputStream fo = new ObjectOutputStream(f);      //writeObject()
            for(Product p: list){
                fo.writeObject(p);
            }
            fo.close();f.close();
            System.out.println("Save successfully!");
        }catch(IOException e){
            
        }
    }
}
