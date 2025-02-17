package Asm03;

import java.util.Scanner;

public class Asm03 {
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "048203002547";
    private static final String CUSTOMER_NAME = "FUNIX";
    private static Customer customer; // Thêm nếu chưa có




    public static void main(String[] args) {
        while (true) {
            System.out.println("+--------------+--------------------------+------------+");
            System.out.println("| NGAN HANG SO | FX20411@v3.0.0                        |");
            System.out.println("+------------+----------------------------+------------+");
            System.out.println("1. Thong tin khach hang");
            System.out.println("2. Them tai khoan ATM");
            System.out.println("3. Them tai khoan tin dung");
            System.out.println("4. Rut tien");
            System.out.println("5. Lich su giao dich");
            System.out.println("0. Thoat");
            System.out.println("+------------+----------------------------+------------+");
            System.out.print("Chuc nang: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Bỏ ký tự xuống dòng

            switch (choice) {
                case 1:
                    activeBank.showCustomer("048203002547");
                    break;
                case 2:
                    activeBank.addAtmAccount(CUSTOMER_ID);
                    break;
                case 4:
                    Withdraw withdraw = new Withdraw();
                    Customer customer = activeBank.getCustomerById("customerId"); // Lấy khách hàng từ danh sách
                    withdraw.withdrawMoney(customer, scanner);
                    break;
                case 6:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Chức năng chưa hỗ trợ.");
            }
        }
    }
}

