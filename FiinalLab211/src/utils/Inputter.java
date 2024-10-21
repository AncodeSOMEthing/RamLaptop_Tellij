/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Inputter {

    public static Scanner sc = new Scanner(System.in);

    //không cần phải kahi báo ở hàm main là Inpputer in = new Inputter(); => ko cần thiết
    //int i = Inputter.inputInt();
    
    public static int inputIntLimit(String msg, int min, int max) { //msg = "Enter an integer"; min=0;
        int data;
        while (true) {
            try {
                System.out.print(msg);
                data = Integer.parseInt(sc.nextLine());
                if (data > min && data <= max) {
                    break;
                } else {
                    
                    System.out.println("Invalid value, try again");
//                    System.out.println("In to:"+min+"-"+max);
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input, try again.");
            }
        }
        return data;
    }
    //tại sao phải xài static
    //Khi mình muốn gọi 1 hàm bình thường, mình cần phải khai báo object ra trước
    //VD: ItemList itemList = new ItemList();
    //itemList.addItem();
    
    //nhưng mà đối với static, mình không cần phải khai báo, mà có thể gọi trực tiếp bằng class luôn
    //mình chỉ cần import package, rồi gọi trực tiếp: Inputter.inputInt();
    public static int inputInt(String msg) {
        int data;
        while (true) {
            try {
                System.out.print(msg);
                data = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input, try again.");
            }
        }
        return data;
    }

    public static int inputIntOrBlank(String msg, int min, int max) {
        int data;
        String temp;
        while (true) {
            try {
                System.out.print(msg);
                temp = sc.nextLine().trim();
                if (temp.isEmpty()) {
                    return -1;
                }
                data = Integer.parseInt(temp);
                if (data < min || data > max) {
                    System.out.println("Input must be in between " + min + " and " + max + ", try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input, try again.");
            }
        }
        return data;
    }

    public static String inputStr(String msg) {
        System.out.print(msg);
        String data = sc.nextLine().trim();
        return data;
    }

    public static String inputNonBlankStr(String msg) {
        String data;
        do {
            System.out.print(msg);
            data = sc.nextLine().trim(); //"   abc def   " => "abc def"
            if (data.length() == 0) {
                System.out.println("Input must not be empty.");
            }
        } while (data.length() == 0);
        return data;
    }

    public static boolean confirmation(String text) { //"Do you wish to continue? (Y/N)" "y"=>"Y", "yes" => "YES"
        String data;
        do {
            System.out.print(text + " (Y/N): "); //abc x

            data = sc.nextLine().trim().toUpperCase();
            if (!"Y".equals(data) && !"N".equals(data)) {
                System.out.println("Invalid syntax, try again.");
            }
        } while (!"Y".equals(data) && !"N".equals(data));
        return "Y".equals(data);
    }

    public static YearMonth inputDate(String msg) {
        while (true) {
            try {
                System.out.print(msg + "(MM/yyyy): ");
                String data = sc.nextLine().trim();
                if (data.trim().isEmpty()) {
                    return null;
                }
                //return LocalDate.parse(data);
                return YearMonth.parse(data, DateTimeFormatter.ofPattern("MM/yyyy"));

            } catch (java.time.DateTimeException dtpe) {
                System.out.println("Invalid format, try again.");
            }
        }
    }

    public static boolean isNumeric(String code) {
        if (code.trim().isEmpty()) {
            return false;
        }
        try {
            code = code.trim();
            Integer.parseInt(code);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
