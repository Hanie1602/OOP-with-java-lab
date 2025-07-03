package manage;

import entities.Product;
import entities.ProductDailyUse;
import entities.ProductLongShelfLife;
import java.util.ArrayList;
import java.util.Date;

public class ReportManage implements IReport {

    //3.1. Products that have expired
    @Override
    public void reportExpiredProducts(ArrayList<Product> listReport) {
        System.out.println("\nExpired Products:");
        Date currentDate = new Date();

        //Checks if each product has expired
        //ompares the product's manufacturing date with the current date
        for (Product product : listReport) {
            if (product instanceof ProductDailyUse) {
                ProductDailyUse dailyUseProduct = (ProductDailyUse) product;
                if (dailyUseProduct.getManufacturingDate().compareTo(currentDate) < 0) {
                    System.out.println(dailyUseProduct);
                }
            } else if (product instanceof ProductLongShelfLife) {
                ProductLongShelfLife longShelfLifeProduct = (ProductLongShelfLife) product;
                if (longShelfLifeProduct.getManufacturingDate().compareTo(currentDate) < 0) {
                    System.out.println(longShelfLifeProduct);
                }
            }
        }
    }

    //3.2. Products that the store is selling
    @Override
    public void reportSellingProducts(ArrayList<Product> listReport) {
        System.out.println("\nSelling Products:");
        Date currentDate = new Date();

        //Checks if each product is available for sale (quantity > 0) 
        //if the product has not expired (manufacturing date <= current date)
        for (Product product : listReport) {
            if (product.getQuantity() > 0) {
                if (product instanceof ProductDailyUse) {
                    ProductDailyUse dailyUseProduct = (ProductDailyUse) product;
                    if (dailyUseProduct.getQuantity()>0 && dailyUseProduct.getManufacturingDate().compareTo(currentDate) <= 0) {
                        if(dailyUseProduct.getProductName().contains("Banh uot"))
                        System.out.println(dailyUseProduct);
                    }
                } else if (product instanceof ProductLongShelfLife) {
                    ProductLongShelfLife longShelfLifeProduct = (ProductLongShelfLife) product;
                    if (longShelfLifeProduct.getQuantity()>0 && longShelfLifeProduct.getManufacturingDate().compareTo(currentDate) <= 0) {
                        if(longShelfLifeProduct.getProductName().contains("Banh uot"))
                        System.out.println(longShelfLifeProduct);
                    }
                }
            }
        }
    }

    //3.3. Products that are running out of stock
    @Override
    public void reportLowStockProducts() {

    }

    @Override
    public void reportImportExportReceipt() {

    }
}
