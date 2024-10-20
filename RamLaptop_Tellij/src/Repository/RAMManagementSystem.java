package Repository;

import MODEL.RAMItem;
import Tool.Inputter;
import Tool.menu;

import java.io.*;
import java.time.YearMonth;
import java.util.*;

public class RAMManagementSystem extends ArrayList<RAMItem>  {

    static Scanner sc = new Scanner(System.in);
    public String generateCode(String type) {
        String prefix = "RAM";
        String code;
        int index = 1;

        // Create a base code
        do {
            code = prefix + type + "_" + index; // code ram
            index++; // Increment index for the next check
        } while (isCodeExists(code)); // Check if the code already exists

        return code;
    }

    private boolean isCodeExists(String code) {
        for (RAMItem item : this) { // Use 'this' to refer to the current list
            if (item.getCode().equalsIgnoreCase(code)) {
                return true; // Code exists
            }
        }
        return false; // Code does not exist
    }

    public void addRAMItem() {
        boolean confirm;
        String type;
        int bus;
        String brand;
        int quantity;
        YearMonth productionMonthYear;
        do {
            type = Inputter.inputNonBlankStr("Enter RAM type: ");
            bus = Inputter.inputIntLimit("Enter BUS SPEED (in MHz): ", 0, 10000);
            brand = Inputter.inputNonBlankStr("Enter RAM brand: ");
            quantity = Inputter.inputIntOrBlank("Enter RAM quantity: ", 0, 999999);
            productionMonthYear = Inputter.inputDate("Enter RAM production month/year: ");

            confirm = Inputter.confirmation("Do you want to add this RAM item?");
            if (confirm) {
                String code = generateCode(type.toUpperCase());
                RAMItem item1 = new RAMItem(code, type.toUpperCase(), bus, brand, quantity, productionMonthYear.toString());
                this.add(item1); // Use 'this' to add to the current list
                System.out.println("RAM item added successfully.");
            } else {
                System.out.println("Item not added.");
            }
            confirm = Inputter.confirmation("Do you want to add another RAM item? ");
            if (!confirm) {
                System.out.println("Returning to MENU..........");
            }
        } while (confirm);
    }



    public void searchRAMItem() {
        if (this.isEmpty()) {
            System.out.println("No RAM item found.");
            return;
        }
        String keyword;
        int value;
        boolean confirm, isExist;
        do {
            isExist = false;
            for (RAMItem item : this) {
                if (item.isActive()) {
                    isExist = true;
                }
            }
            if (!isExist) {
                System.out.println("RAM list is currently empty. Returning to MENU..........");
                return;
            }
            String[] options = {"Search by Type", "Search by Bus", "Seach by Brand", "Return"};
            int choice;
            do {
                choice = menu.getChoice(options);
                switch (choice) {
                    case 1:
                        keyword = Inputter.inputNonBlankStr("Enter RAM type to search: ");
                        for (RAMItem item : this) {
                            String act = item.isActive() ? "active" : "inactive";
                            if (item.getType().equalsIgnoreCase(keyword)) {//? can actice = true hay ko
                                System.out.println("[" + act + "] "+item);
                            }
                        }
                        break;
                    case 2:
                        value = Inputter.inputIntLimit("Enter BUS SPEED (in MHz): ", 0, 20000);
                        for (RAMItem item : this) {
                            String act = item.isActive() ? "active" : "inactive";
                            if (item.getBus() == value) {
                                System.out.println("[" + act + "] "+item);
                            }
                        }
                        break;
                    case 3:
                        keyword = Inputter.inputNonBlankStr("Enter RAM brand to search: ");
                        for (RAMItem item : this) {
                            String act = item.isActive() ? "active" : "inactive";
                            if (item.getBrand().equalsIgnoreCase(keyword)) {
                                System.out.println("[" + act + "] "+item);
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Returning..........");
                        break;
                }
            } while (choice != 4);
            confirm = Inputter.confirmation("Do you want to search another RAM item?");
            if (!confirm) {
                System.out.println("Returning to MENU..........");
            }
        } while (confirm);

    }

    public void printALL() {
        if (this.isEmpty()) {
            System.out.println("RAM list item is currently empty.");
            return;
        }
        int count = 0;
        for (RAMItem item : this) {
            if (item.isActive()) {
                System.out.println(item);
                count++;
            }
        }
//        Collections.sort(active, new Comparator<RAMItem>() {
//            @Override
//            public int compare(RAMItem o1, RAMItem o2) {
//                int type = o1.getType().compareToIgnoreCase(o2.getType());
//                if (type != 0) {
//                    return type;
//                }
//                int bus = o1.getBus().compareToIgnoreCase(o2.getBus());
//                if (bus != 0) {
//                    return bus;
//                }
//                return o1.getBrand().compareToIgnoreCase(o2.getBrand());
//            }
//        });
        if (count == 0) {
            System.out.println("RAM list item is currently empty.");
        } else {
            System.out.println("Total: " + count + " items.");
        }
    }

    public void update() {
        RAMItem ramUp = null;
        if (this.isEmpty()) {
            System.out.println("RAM list item is currently empty.Cannot update.");
            return;


        }
        boolean ok, confirm;
        String type, brand;
        int bus, quantity;
        YearMonth productionMonthYear;
        String ask = " (leave blank to retain old data) : ";
        do {
            ok = false;
            for (RAMItem item : this) {
                if (item.isActive()) {
                    ok = true;
                }
            }
            if (!ok) {
                System.out.println("Ram list item is currently empty.");
                return;
            }
            printALL();
            String code = Inputter.inputNonBlankStr("Input RAM code to update: ");

            for (RAMItem item : this) {
                if (item.getCode().equalsIgnoreCase(code) && item.isActive()) {
                    ramUp = item;
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                System.out.println("Item code does exist.");
            } else {
                type = Inputter.inputNonBlankStr("Enter RAM type to update: " + ask);
                type = type.toLowerCase();
                if (type.isEmpty()) {
                    type = ramUp.getType();
                }
                brand = Inputter.inputNonBlankStr("Enter RAM brand to update: " + ask);
                if (brand.isEmpty()) {
                    brand = ramUp.getBrand();
                }
                bus = Inputter.inputIntOrBlank("Enter new Bus speed " + ask, 0, 20000);
                if (bus == -1) {
                    bus = ramUp.getBus();
                }
                quantity = Inputter.inputIntOrBlank("Enter new Quantity " + ask, 0, 20000);
                if (quantity == -1) {
                    quantity = ramUp.getQuantity();
                }
//              productionMonthYear = YearMonth.parse(sc.nextLine());

                productionMonthYear = Inputter.inputDate("Enter RAM production month/year: ");
                String productionMonthYearStr = ramUp.getProduction_Month_Year();
                if (productionMonthYear == null) {
                    productionMonthYearStr = ramUp.getProduction_Month_Year();
                } else {
                    productionMonthYearStr = productionMonthYear.toString();
                }
                //---------------------------------------------
                ramUp.setType(type);
                ramUp.setBrand(brand);
                ramUp.setBus(bus);
                ramUp.setQuantity(quantity);
                ramUp.setProduction_Month_Year(productionMonthYearStr);//str
                System.out.println("RAM item updated successfully.");
            }
            confirm = Inputter.confirmation("Do you want to update the RAM item?");
        } while (confirm);
    }

    //    @SuppressWarnings("unche")
    public void  loadFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            RAMItem iList = (RAMItem) ois.readObject();
            this.add(iList);
            System.out.println("Load file successfully.");
        } catch (Exception e) {
            System.out.println("loadFile Error: " + e.toString());
        }
    }
//    public ItemList loadFile(String filename) {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
//            ItemList iList = (ItemList) ois.readObject();
//            System.out.println("Load file successfully.");
//            return iList;
//        } catch (Exception e) {
//            System.out.println("loadFile Error: " + e.toString());
//            return null;
//        }
//    }

    public void saveToFile(String path) {
        try (FileOutputStream fo = new FileOutputStream(path);
             ObjectOutputStream op = new ObjectOutputStream(fo)) {
            op.writeObject(this); // Save the current instance
            System.out.println("Save file completed.");
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
    public void delete (){
        if (this.isEmpty()){
            System.out.println("Ram list item is currently empty.Cannot delete.");
            return;
        }
        boolean Exist , confirm;
        String codeInPut;
        do {
            Exist = false;
            for (RAMItem item : this) {
                if (item.isActive()) {
                    Exist = true;

                }
            }
            if (!Exist) {
                System.out.println("Ram list item is currently empty.Return to menu......");
                return;
            }
            printALL();
            Exist = false;
            codeInPut = Inputter.inputNonBlankStr("Input RAM code to delete: ");
            for (RAMItem item : this) {
                if (item.getCode().equalsIgnoreCase(codeInPut)&& item.isActive()) {
                    Exist = true;
                    confirm = Inputter.confirmation("Do you want to delete this RAM item?");
                   if (confirm) {
                       item.setActive(false);// Ẩn ram đi
                       System.out.println("RAM item deleted successfully.");
                       break;
                   } else{
                       System.out.println("Action discarded.");
                       break;
                   }
                }
            }
            if(!Exist) {
                System.out.println("This item code does not exist.");
                return;
            }
            confirm = Inputter.confirmation("Do you want to delete another item?");
            if (!confirm) {
                System.out.println("Returning to MENU..........");
            }
        }while (confirm);

    }
    }


