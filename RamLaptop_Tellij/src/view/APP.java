package view;

import Tool.*;
import MODEL.RAMItem;
import Repository.RAMManagementSystem;

public class APP {
    public static void main(String[] args) {
        int choice;
        boolean comfirm;
        String path = "RAMModules.dat";
        RAMManagementSystem ram = new RAMManagementSystem();
        ram.loadFile(path);
        String[] options = {"Add RAM item", "Search RAM item", "Update RAM Information", "Delete RAM item", "Show all RAM item",
                "Save to file", "Exit"};
        do {
            System.out.println("===LAPTOP RAM MANAGEMENT SYSTEM===");
            choice = menu.getChoice(options);
            switch (choice) {
                case 1:
                    ram.addRAMItem();
                    break;
                case 2:
                    ram.searchRAMItem();
                    break;
                case 3:
                    ram.update();
                    break;
                case 4:
                    ram.delete();
                    break;
                case 5:
                    ram.printALL();
                    break;
                case 6:
                    ram.saveToFile(path);
                    break;
                case 7:
                    comfirm = Inputter.confirmation("Do you want to exit the program?");
                    if (comfirm) {
                        System.out.println("Exiting...");
                        break;
                    }else{
                        choice = -1;
                    }
                    break;
                    default:
                        System.out.println("Invalid option.Try again.");
            }
        }while (choice != 7);
    }
}
