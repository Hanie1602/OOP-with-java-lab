
package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductDailyUse extends Product {
    private Date manufacturingDate;
    private Date expirationDate;

    //Constructor
    public ProductDailyUse(Date manufacturingDate, Date expirationDate, String productCode, String productName, int quantity, float price) {
        super(productCode, productName, quantity, price);
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
    }
    
    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/mm/yyyy");
        
        return super.toString() +
                ", ManufacturingDate: " + formatDate.format(manufacturingDate) +
                ", ExpirationDate: " + formatDate.format(expirationDate);
    }
    
}
