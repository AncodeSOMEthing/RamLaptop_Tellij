package gui;

import bus.RamBus;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        RamBus ramBus = new RamBus();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            menu.ShowMenu(); // Hiển thị menu
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt(); // Nhập lựa chọn từ người dùng

            switch (choice) {
                case 1:
                    // Thêm module RAM
                    do {
                        ramBus.addRam(); // Gọi phương thức addRam
                        System.out.print("Do you want to add another RAM item? (yes/no): ");
                    } while (scanner.next().equalsIgnoreCase("yes"));
                    break;

                case 2:
                    // Tìm kiếm module RAM
                    do {
                        ramBus.searchRam(); // Gọi phương thức searchRam
                        System.out.print("Do you want to search another RAM item? (yes/no): ");
                    } while (scanner.next().equalsIgnoreCase("yes"));
                    break;

                case 3:
                    // Cập nhật thông tin module RAM
                    do {
                        ramBus.updateRam(); // Gọi phương thức updateRam
                        System.out.print("Do you want to update another RAM item? (yes/no): ");
                    } while (scanner.next().equalsIgnoreCase("yes"));
                    break;

                case 4:
                    // Xóa module RAM
                    do {
                        ramBus.deleteRam(); // Gọi phương thức deleteRam
                        System.out.print("Do you want to delete another RAM item? (yes/no): ");
                    } while (scanner.next().equalsIgnoreCase("yes"));
                    break;

                case 5:
                    // Hiển thị tất cả các module RAM
                    ramBus.printActiveRam();
                    break;

                case 6:
                    // Lưu vào tệp
                    ramBus.saveToFile();
                    System.out.println("Data saved to file.");
                    break;

                case 7:
                    // Thoát
                    System.out.println("Exiting the program.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7); // Lặp lại cho đến khi người dùng chọn thoát

        scanner.close(); // Đóng scanner
    }
}