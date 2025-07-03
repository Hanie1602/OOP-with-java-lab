
package manage;

import entities.Product;
import java.util.ArrayList;

public interface IReport {
    //1. Products that have expired
    void reportExpiredProducts(ArrayList<Product> listReport);
    
    //2. Products that the store is selling
    void reportSellingProducts(ArrayList<Product> listReport);
    
    //3. Products that are running out of stock
    void reportLowStockProducts();
    
    //4. Import/Export Receipt of a Product
    void reportImportExportReceipt();
}
