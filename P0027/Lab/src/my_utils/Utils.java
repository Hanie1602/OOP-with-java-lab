package my_utils;

import entities.Vehicle;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Utils {

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

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

    public static double getDouble(String welcome, double min) {
        boolean check = true;
        double number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Double.parseDouble(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must be greater than " + min);
                } else {
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input must be a valid number!");
            }
        } while (check || number < min);
        return number;
    }

    //Check choice
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

    //Check Code
    public static boolean isCodeUnique(String code, ArrayList<Vehicle> listVehicle) {
        for (Vehicle v : listVehicle) {
            if (((Vehicle) v).getVehicleID().equals(code)) {
                return false;           //Code is not unique
            }
        }
        return true;                    //Code is unique
    }

    //Check date;
    public static Date setDate(String welcome, String error, String format) {
        boolean errorCheking;
        Date date = null;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print(welcome);
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

    //Yes or No Answer
    public static boolean getYesNoAnswer(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.err.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    //======================================================RANDOM ID ===============================================
    //Random ID with a specified length
    public static String generateRandomID(int minLength, int maxLength) {
        Random rand = new Random();
        int length = rand.nextInt(maxLength - minLength + 1) + minLength;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(rand.nextInt(10)); // Generates random digits
        }
        return sb.toString();
    }
}
