
package manage;

import entities.Product;
import java.util.ArrayList;

public interface IProductManage {
    //1. Add a product 
    void addProductDailyuse();
    void addProductLongShelfLife();
    
    //2. Update product information
    void updateProductInformation();
    
    //3. Delete product. 
    void deleteProduct();
    
    //4. Show all product
    void showAllProducts();
    
    //Get product list
    ArrayList<Product> getProductList();
}
