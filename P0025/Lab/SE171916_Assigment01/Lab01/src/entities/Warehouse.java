
package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Warehouse implements Serializable{
    private String warehourseCode;
    private String createTime;
    private ArrayList<Product> productsList;

    public Warehouse(String warehourseCode, String createTime, ArrayList<Product> productsList) {
        this.warehourseCode = warehourseCode;
        this.createTime = createTime;
        this.productsList = productsList;
    }

    public String getWarehourseCode() {
        return warehourseCode;
    }

    public void setWarehourseCode(String warehourseCode) {
        this.warehourseCode = warehourseCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<Product> productsList) {
        this.productsList = productsList;
    }

    @Override
    public String toString() {
        String result = "Ware hourse Code: " + warehourseCode + ", Time: " + createTime;
        
        for(Product p : productsList){
            String pCode = p.getProductCode();
            result += (", " + pCode);
        }
        return result;
    }
    
}
