/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.util.Scanner;
/**
 *
 * @author ryliz
 */
public class Menu {
    //mảng String options có thể có tuỳ ý bn phần tử cũng được, nhưng sau khi khia báo thì không thay đồi đc nữa
    public static int getChoice(String[] options){ //options {"Add", "Update", "Delete"}
        //getchoice =>
        /*
        1. Add.
        2. Update.
        3. Delete.
        Choose 1 of 3: 1
        => khai báo ở main:
        String[] options
        int choice = Menu.getChoice(options);
        switch (choice){
        case 1: abc
        case 2: xyz...
        }
        */
        for(int i=0;i<options.length;i++){
            System.out.println((i+1)+". "+options[i]+".");
        }
        System.out.print("Choose 1 of "+options.length+": ");
        Scanner sc=new Scanner(System.in);
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException nfe) {
        return -1;
        }
        
    }
}