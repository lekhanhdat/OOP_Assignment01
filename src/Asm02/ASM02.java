package Asm02;

import java.util.Scanner;
import java.util.*;

public class ASM02 {
    private static final Bank bank = new Bank(); // Ngân hàng chung
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
            while (true) {
            // Hiển thị giao diện console
            System.out.println("+------------+------------------------------+------------+");
            System.out.println("| NGAN HANG SO | FX20411@v2.0.0                          |");
            System.out.println("+------------+------------------------------+------------+");
            System.out.println("1. Them khach hang");
            System.out.println("2. Them tai khoan cho khach hang");
            System.out.println("3. Hien thi danh sach khach hang");
            System.out.println("4. Tim theo CCCD");
            System.out.println("5. Tim theo ten khach hang");
            System.out.println("0. Thoat");
            System.out.println("+------------+------------------------------+------------+");
            System.out.print("Chuc nang: ");

            // Nhận lựa chọn từ người dùng
            int choice = scanner.nextInt();
            scanner.nextLine(); // Xử lý dòng trống

            switch (choice) {
                case 1:
                    bank.addCustomer();
                    break;
                case 2:
                    bank.addAccountToCustomer();
                    break;
                case 3:
                    bank.displayCustomers();
                    break;
                case 4:
                    bank.searchCustomerById();
                    break;
                case 5:
                    System.out.println("Nhap tu khoa tim kiem theo ten: ");
                    scanner.nextLine();
                    String keyword = scanner.nextLine();
                    bank.findCustomersByName(keyword);
                    break;
                case 0:
                    System.out.println("Cam on ban da su dung chuong trinh. Tam biet!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai!");
            }
        }
    }


}
