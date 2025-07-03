package my_utils;

import entities.Product;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Utils {

    public static String getString(String welcome, String empty) {
        boolean check = true;
        String result = " ";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            result = sc.nextLine();

            if (result.isEmpty()) {
                System.err.println(empty);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getStringreg(String welcome, String pattern, String empty, String msgreg) {
        boolean check = true;
        String result = " ";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.err.println(empty);
            } else if (!result.matches(pattern)) {
                System.err.println(msgreg);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static int checkChoice(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.err.println("Number must be large than: 0 ");
                } else if (number > max) {
                    System.err.println("Number must be less than: " + max);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.err.println("Input number!!!");
            }
        } while (check || number < min || number > max);
        return number;
    }

    public static int getInt(String welcome, int min) {
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must be large than " + min);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static float getFloat(String welcome, float min) {
        boolean check = true;
        float number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {

                System.out.print(welcome);
                number = Float.parseFloat(sc.nextLine());
                if (number <= min) {
                    System.out.println("Number must be large than " + min);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number <= min);
        return number;
    }

    //Function to check if the code already exists
    public static boolean isCodeUnique(String code, ArrayList<Product> products) {
        for (Product p : products) {
            if (((Product) p).getProductCode().equals(code)) {
                return false;           //Code is not unique
            }
        }
        return true;                    //Code is unique
    }

    //Function to check date;
    public static Date setDate(String welcome, String error, String format) {
        boolean errorCheking;
        Date date = null;
        Scanner sc = new Scanner(System.in);

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        do {
            System.out.println(welcome);
            errorCheking = false;
            String input = sc.nextLine();
            try {
                date = dateFormat.parse(input);
            } catch (ParseException e) {
                System.out.println(error);
                errorCheking = true;
            }
        } while (errorCheking);
        return date;
    }

    //
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }
}
