package gui;

import utils.Inputter;
import bus.RamBus;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
           enuManagerment() {


    }

    static public void enuManagerment() {
        RamBus ramBus = new RamBus();
        Scanner sc = new Scanner(System.in);
        try {
            int choice;

            do {
                menu.ShowMenu(); // Hiển thị menu
                System.out.print("Enter your choice: ");
                choice = sc.nextInt(); // Nhập lựa chọn từ người dùng

                switch (choice) {
                    case 1:

                        ramBus.addRam();

                        break;

                    case 2:

                        ramBus.searchRam();
                        break;

                    case 3:
                        do {
                            ramBus.updateRam();
                            System.out.print("Do you want to update another RAM item? (yes/no): ");
                        } while (sc.next().equalsIgnoreCase("yes"));
                        break;

                    case 4:

                        do {
                            ramBus.deleteRam();
                            System.out.print("Do you want to delete another RAM item? (Y/N): ");
                        } while (sc.next().equalsIgnoreCase("Y"));
                        break;

                    case 5:

                        ramBus.printActiveRam();
                        break;

                    case 6:

                        ramBus.saveToFile();
                        System.out.println("Data saved to file.");
                        break;

                    case 7:

                        boolean confirm = Inputter.confirmation("Do you want to exit this system?");
                        if (confirm) {
                            ramBus.saveToFile();
                            break;
                        } else {
                            choice = -1;
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 7);
        } catch (Exception e) {
            System.out.println("invalid format input");
            MENU();
        }
    }
}
