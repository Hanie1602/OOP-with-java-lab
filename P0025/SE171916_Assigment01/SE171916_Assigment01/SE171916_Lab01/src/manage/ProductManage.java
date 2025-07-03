package manage;

import entities.Product;
import entities.ProductDailyUse;
import entities.ProductLongShelfLife;

import java.util.ArrayList;
import java.util.Date;
import my_utils.Utils;

public class ProductManage implements IProductManage {

    String productCode, productName;
    int quantity;
    float price;
    Date manufacturingDate, expirationDate;

    private ArrayList<Product> listProducts;        

    //Contructor
    public ProductManage() {
        listProducts = new ArrayList<>();
    }

    //Get product list
    @Override
    public ArrayList<Product> getProductList() {
        return this.listProducts;
    }

    //1. add a Product
    @Override
    public void addProductDailyuse() {
        //submenu
        System.out.println("\n=====ADDING A PRODUCT=====");

        do {
            productCode = Utils.getStringreg("Enter product code (must be in format D000000): ", "D\\d{6}",
                    "Product code cannot be empty", "Invalid product code format. Please use format D000000");
            
            // Check if the entered product code is not unique
            if (!Utils.isCodeUnique(productCode, listProducts)) {
                System.out.println("Product with the same code already exist. Please enter a unique code");
            }
        } while (!Utils.isCodeUnique(productCode, listProducts));

        productName = Utils.getString("Enter product name: ", "Name cannot be empty");
        price = Utils.getFloat("Enter product price: ", 0);
        quantity = Utils.getInt("Enter product quantity: ", 0);

        manufacturingDate = Utils.setDate("Enter manufacturing date: ", "Invalid date!!", "dd/mm/yyy");
        expirationDate = Utils.setDate("Enter expiration date: ", "Invalid date!!", "dd/mm/yyy");

        while (expirationDate.before(manufacturingDate)) {
            System.out.println("Expiration date must be after Manufacturing date !");
            expirationDate = Utils.setDate("Enter expiration date: ", "Invalid date!!", "dd/mm/yyy");
        }

        Product newProduct = new ProductDailyUse(manufacturingDate, expirationDate, productCode, productName, quantity, price);
        listProducts.add(newProduct);

        System.out.println("Product added successfully with code: " + productCode);
    }

    @Override
    public void addProductLongShelfLife() {
        System.out.println("\n=====ADDING A PRODUCT=====");

        do {
            productCode = Utils.getStringreg("Enter product code (must be in format L000000: ", "L\\d{6}",
                    "Product code cannot be empty", "Invalid product code format. Please use format L000000");
            if (!Utils.isCodeUnique(productCode, listProducts)) {
                System.out.println("Product with the same code already exist. Please enter a unique code");
            }
        } while (!Utils.isCodeUnique(productCode, listProducts));

        productName = Utils.getString("Enter product name: ", "Name cannot be empty");
        price = Utils.getFloat("Enter product price: ", 0);
        quantity = Utils.getInt("Enter product quantity: ", 0);
        manufacturingDate = Utils.setDate("Enter manufacturing date: ", "Invalid date!!", "dd/mm/yyy");
        expirationDate = Utils.setDate("Enter expiration date: ", "Invalid date!!", "dd/mm/yyy");
        while (expirationDate.before(manufacturingDate)) {
            System.out.println("Expiration date must be after Manufacturing date !");
            expirationDate = Utils.setDate("Enter expiration date: ", "Invalid date!!", "dd/mm/yyy");
        }

        Product newProduct = new ProductLongShelfLife(manufacturingDate, expirationDate, productCode, productName, quantity, price);
        listProducts.add(newProduct);
    }

    //2. Update product information
    @Override
    public void updateProductInformation() {
        // Submenu
        System.out.println("\n=====UPDATE PRODUCT INFORMATION=====");

        // Request the product code to update
        productCode = Utils.getStringreg("Enter the product code you want to update: ", "L\\d{6}||D\\d{6}",
                "Product code cannot be empty", "Invalid product code format");

        // Find the product with the entered code
        Product productToUpdate = null;
        for (int i = 0; i < listProducts.size(); i++) {
            Product product = listProducts.get(i);
            if (product.getProductCode().equals(productCode)) {
                productToUpdate = product;
                //If found, break the loop
                break;
            }
        }

        if (productToUpdate == null) {
            System.out.println("Product does not exist with code: " + productCode);
            return; // Exit if the product does not exist
        }

        // Display options to update information
        System.out.println("Select information to update:");
        System.out.println("1. Product Name");
        System.out.println("2. Price");
        System.out.println("3. Quantity");
        System.out.println("4. Manufacturing Date");
        System.out.println("5. Expiration Date");

        int choice = Utils.checkChoice("Enter your choice (1-5): ", 1, 5);

        switch (choice) {
            case 1:
                String newName = Utils.getString("Enter new product name: ", "Name cannot be empty!");
                productToUpdate.setProductName(newName);
                break;
            case 2:
                float newPrice = Utils.getFloat("Enter the new product price: ", 0);
                productToUpdate.setPrice(newPrice);
                break;
            case 3:
                int newQuantity = Utils.getInt("Enter the new product quantity: ", 0);
                productToUpdate.setQuantity(newQuantity);
                break;
            case 4:
                if (productToUpdate instanceof ProductDailyUse) {
                    Date newManufacturingDate = Utils.setDate("Enter the new manufacturing date: ", "Invalid date format! Use dd/mm/yyyy", "dd/mm/yyyy");
                    ((ProductDailyUse) productToUpdate).setManufacturingDate(newManufacturingDate);
                } else {
                    Date newManufacturingDate = Utils.setDate("Enter the new manufacturing date: ", "Invalid date format! Use dd/mm/yyyy", "dd/mm/yyyy");
                    ((ProductLongShelfLife) productToUpdate).setManufacturingDate(newManufacturingDate);
                }
                break;
            case 5:
                if (productToUpdate instanceof ProductDailyUse) {
                    Date newExpirationDate = Utils.setDate("Enter the new expiration date: ", "Invalid date format! Use dd/mm/yyyy", "dd/mm/yyyy");
                    ((ProductDailyUse) productToUpdate).setExpirationDate(newExpirationDate);
                } else {
                    Date newManufacturingDate = Utils.setDate("Enter the new manufacturing date: ", "Invalid date format! Use dd/mm/yyyy", "dd/mm/yyyy");
                    ((ProductLongShelfLife) productToUpdate).setManufacturingDate(newManufacturingDate);
                }
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Product information updated successfully.");

    }

    //3. Delete product. 
    @Override
    public void deleteProduct() {
        // Submenu
        System.out.println("\n=====DELETE PRODUCT=====");

        // Request the product code to delete
        productCode = Utils.getStringreg("Enter the product code you want to delete: ", "L\\d{6}||D\\d{6}",
                "Product code cannot be empty", "Invalid product code format");

        Product productToDelete = null;
        for (Product product : listProducts) {
            if (product.getProductCode().equals(productCode)) {
                productToDelete = product;
                break;
            }
        }

        if (productToDelete == null) {
            System.err.println("Product does not exist");
        } else {
            // Remove the product from the list
            listProducts.remove(productToDelete);
            System.out.println("Product deleted successfully.");
        }

    }

    //4. Show all product
    @Override
    public void showAllProducts() {
        System.out.println("\n====== All Products ======");

        for (Product showProduct : listProducts) {
            System.out.println(showProduct);
        }
    }
}





