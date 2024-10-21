package bus;

import utils.*;
import Model.RAMmodel;
import Repository.RAMrepository;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RamBus {

    private RAMrepository RamRepo;

    public RamBus() {
        RamRepo = new RAMrepository();
        loadFromFile();
    }

    public String generateCode(String type) {
        boolean isExist;
        String prefix = "RAM";
        String code;
        int index = 1;
        do {
            code = prefix + type + "_" + index; // Tạo mã RAM
            isExist = false;

            // Lấy danh sách các module RAM từ RamRepo
            for (RAMmodel item : RamRepo.read().values()) {
                if (item.getCode().equalsIgnoreCase(code)) {
                    index++; // Tạo mã code khác
                    isExist = true;
                    break;
                }
            }
        } while (isExist);
        return code;
    }

    public void addRam() {
        boolean confirm;
        String type, brand;
        int bus, quantity;
        YearMonth production;

        do {
            type = Inputter.inputNonBlankStr("Enter RAM type: ");
            type = type.toUpperCase();
            bus = Inputter.inputIntLimit("Enter speed bus (in MHz): ", 0, 10000);
            brand = Inputter.inputNonBlankStr("Enter brand RAM: ");
            quantity = Inputter.inputIntLimit("Enter quantity RAM: ", 0, Integer.MAX_VALUE);
            production = Inputter.inputDate("Enter production date : ");

            confirm = Inputter.confirmation("Do you want to add this RAM?");

            if (confirm) {
                RAMmodel newRam = new RAMmodel(generateCode(type), type, brand, bus, quantity, production);
                int result = RamRepo.create(newRam);
                if (result == 1) {
                    System.out.println("RAM module added successfully.");
                } else {
                    System.out.println("Failed to add RAM module. Please try again.");
                }
            } else {
                System.out.println("Action discarded.");
            }

            confirm = Inputter.confirmation("Do you want to add another product?");
            if (!confirm) {
                System.out.println("Returning to menu...");
            }
        } while (confirm);
    }

    public void updateRam() {
        String code = Inputter.inputNonBlankStr("Enter RAM code to update: ");
        RAMmodel existingRam = RamRepo.read().get(code); // Giả sử read() trả về HashMap<String, RAMmodel>

        if (existingRam != null) {
            boolean confirm;
            String type, brand;
            int bus, quantity;
            YearMonth production;

            System.out.println("Current details: " + existingRam);

            // Nhập thông tin mới
            type = Inputter.inputNonBlankStr("Enter new RAM type (leave blank to keep current): ");
            if (!type.isEmpty()) {
                existingRam.setType(type.toUpperCase());
            }

            bus = Inputter.inputIntLimit("Enter new speed bus (in MHz, leave 0 to keep current): ", 0, 10000);
            if (bus > 0) {
                existingRam.setBus(bus);
            }

            brand = Inputter.inputNonBlankStr("Enter new brand RAM (leave blank to keep current): ");
            if (!brand.isEmpty()) {
                existingRam.setBrand(brand);
            }

            quantity = Inputter.inputIntLimit("Enter new quantity RAM (leave 0 to keep current): ", 0, Integer.MAX_VALUE);
            if (quantity > 0) {
                existingRam.setQuantity(quantity);
            }

            production = Inputter.inputDate("Enter new production date (leave blank to keep current): ");
            if (production != null) {
                existingRam.setProductionDate(production);
            }

            confirm = Inputter.confirmation("Do you want to update this RAM?");
            if (confirm) {
                int result = RamRepo.update(existingRam); // Giả sử có phương thức update trong RAMrepository
                if (result == 1) {
                    System.out.println("RAM module updated successfully.");
                } else {
                    System.out.println("Failed to update RAM module. Please try again.");
                }
            } else {
                System.out.println("Update action discarded.");
            }
        } else {
            System.out.println("RAM code not found. Please check the code and try again.");
        }
    }
// ---------------------------------------------------------------- SEARCH 

    public void searchRam() {
        int choice;
        do {
            System.out.println("Select search option:");
            System.out.println("1. Search by RAM type");
            System.out.println("2. Search by RAM brand");
            System.out.println("3. Search by RAM bus speed");
            System.out.println("4. Exit search menu");

            choice = Inputter.inputIntLimit("Enter your choice (1-4): ", 0, 4);

            switch (choice) {

                case 1:
                    searchByType();
                    break;
                case 2:
                    searchByBrand();
                    break;
                case 3:
                    searchByBus();
                    break;
                case 4:
                    System.out.println("Exiting search menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private void searchByType() {
        String type = Inputter.inputNonBlankStr("Enter RAM type to search: ");
        List<RAMmodel> results = RamRepo.searchByType(type);
        if (!results.isEmpty()) {
            
            for (RAMmodel ram : results) {
                String status = "ACTIVE";
                if (!ram.isActive()) {
                    status = "INACTIVE";
                }
                System.out.println("Found RAM: " + ram + "[" + status + "]");
            }
        } else {
            System.out.println("No RAM module found with type: " + type);
        }
    }

    private void searchByBrand() {
        String brand = Inputter.inputNonBlankStr("Enter RAM brand to search: ");
        List<RAMmodel> results = RamRepo.searchByBrand(brand);
        if (!results.isEmpty()) {
            
            for (RAMmodel ram : results) {
                String status = "ACTIVE";
                if (!ram.isActive()) {
                    status = "INACTIVE";
                }
                System.out.println("Found RAM: " + ram+"[" + status + "]");
            }
        } else {
            System.out.println("No RAM module found with brand: " + brand);
        }
    }

    private void searchByBus() {
        int bus = Inputter.inputIntLimit("Enter RAM bus speed to search (in MHz): ", 0, 10000);
        List<RAMmodel> results = RamRepo.searchByBus(bus);
        if (!results.isEmpty()) {

            for (RAMmodel ram : results) {
                String status = "ACTIVE";

                if (!ram.isActive()) {
                    status = "INACTIVE";
                }
                System.out.println("Found RAM: " + ram+"[" + status + "]");
            }
        } else {
            System.out.println("No RAM module found with bus speed: " + bus);
        }
    }
//----------------------load vs save ------------------------------------

    public void saveToFile() {
        RamRepo.saveToFile(); // Gọi phương thức saveToFile từ RAMrepository
    }

    public void loadFromFile() {
        RamRepo.loadFromFile(); // Gọi phương thức loadFromFile từ RAMrepository
    }
//--------------------delete-----------------------------------------------

    public void deleteRam() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Here is List RAM actice ");
        for (RAMmodel ram : RamRepo.read().values()) {
            if(ram.isActive()){
                System.out.println(ram);
            }
        }
        String code = Inputter.inputNonBlankStr("Enter RAM ID to delete: "); // Nhập mã RAM
        
        int result = RamRepo.delete(code); // Gọi phương thức delete từ RAMrepository
        if (result == 1) {
            System.out.println("RAM module with code " + code + " set INACTIVE.");
        } else {
            System.out.println("No RAM module found with code: " + code + " or it is already inactive.");
        }
    }
    //-------------------print-------------------------

    public void printActiveRam() {
    List<RAMmodel> act = new ArrayList<>();
    System.out.println("Active RAM Modules:");

    for (RAMmodel ram : RamRepo.read().values()) {
        if (ram.isActive()) {
            act.add(ram);
        }
    }

    act.sort((o1, o2) -> {
        int cType = o1.getType().compareToIgnoreCase(o2.getType());
        if (cType != 0) {
            return cType;
        }
        int cBus = Integer.compare(o1.getBus(), o2.getBus());
        if (cBus != 0) {
            return cBus;
        }
        return o1.getBrand().compareToIgnoreCase(o2.getBrand());
    });

    for (RAMmodel rAMmodel : act) {
        System.out.println(rAMmodel);
    }
}
   
}
