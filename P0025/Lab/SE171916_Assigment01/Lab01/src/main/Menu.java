package main;

import file.FunctionFile;
import manage.ProductManage;
import manage.ReportManage;
import manage.WarehouseManage;
import my_utils.Utils;

public class Menu {

    ProductManage sp = new ProductManage();
    ReportManage rp = new ReportManage();
    WarehouseManage wm = new WarehouseManage();
    FunctionFile file = new FunctionFile();

    String filename = "Product.dat";

    public void mainMenu() {
        file.loadDataFromFile(sp.getProductList(), filename);
        
        while (true) {
            System.out.println("========== MAIN MENU ==========");
            System.out.println("1. Manage Products/Items of the Store");
            System.out.println("2. Manage Warehouse");
            System.out.println("3. Report");
            System.out.println("4. Store Data to Files");
            System.out.println("5. Clo1se the application");
            int choice = Utils.checkChoice("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1:
                    manageProductsMenu();
                    break;
                case 2:
                    warehouseManagementMenu();
                    break;
                case 3:
                    reportMenu();
                    break;
                case 4:
                    storeDataToFiles();
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
            }
        }
    }

    public void manageProductsMenu() {

        while (true) {
            System.out.println("\n===== MANEGE PRODUCT MENU =====");
            System.out.println("1. Add a Product");
            System.out.println("2. Update Product Information");
            System.out.println("3. Delete Product");
            System.out.println("4. Show All Products");
            System.out.println("5. Back to Main Menu");
            int choice = Utils.checkChoice("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1:
                    choice = Utils.checkChoice("\n1. Add a new product daily use \n2. Add a new product long shelf life \n3. Exit \nEnter your choice (1-3): ", 1, 3);
                    if (choice == 3) {
                        return;
                    } else if (choice == 1) {
                        sp.addProductDailyuse();
                    } else {
                        sp.addProductLongShelfLife();
                    }
                    break;
                case 2:
                    sp.updateProductInformation();
                    break;
                case 3:
                    sp.deleteProduct();
                    break;
                case 4:
                    sp.showAllProducts();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void warehouseManagementMenu() {
        while (true) {
            System.out.println("Warehouse Management Menu:");
            System.out.println("1. Create an Import Receipt");
            System.out.println("2. Create an Export Receipt");
            System.out.println("3. Back to Main Menu");
            int choice = Utils.checkChoice("Enter your choice: ", 1, 3);

            switch (choice) {
                case 1:
                    wm.createImportReceipt();
                    break;
                case 2:
                    wm.createExportReceipt();
                    break;
                case 3:
                    return;
            }
        }
    }

    public void reportMenu() {

        while (true) {
            System.out.println("\n======Report Menu======");
            System.out.println("1. Products that have expired");
            System.out.println("2. Products that the store is selling");
            System.out.println("3. Products that are running out of stock");
            System.out.println("4. Import/Export Receipt of a Product");
            System.out.println("5. Back to Main Menu");
            int choice = Utils.checkChoice("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1:
                    rp.reportExpiredProducts(sp.getProductList());
                    break;
                case 2:
                    rp.reportSellingProducts(sp.getProductList());
                    break;
                case 3:
                    rp.reportLowStockProducts();
                    break;
                case 4:
                    //reportImportExportReceipt();
                    break;
                case 5:
                    return;
            }
        }
    }

    public void storeDataToFiles() {
        file.saveToFile(sp.getProductList(), filename);
    }
}
