package Asm04;

import java.util.Scanner;

public class Asm04 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank(); // Tạo đối tượng DigitalBank

    public static void main(String[] args) {
        while (true) {
            System.out.println("+--------------+--------------------------+------------+");
            System.out.println("| NGAN HANG SO | FX20411@v3.0.0                        |");
            System.out.println("+------------+----------------------------+------------+");
            System.out.println("1. Xem danh sach khach hang");
            System.out.println("2. Nhap danh sach khach hang");
            System.out.println("3. Them tai khoan ATM");
            System.out.println("4. Chuyen tien");
            System.out.println("0. Thoat");
            System.out.println("+------------+----------------------------+------------+");
            System.out.print("Chuc nang: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Bỏ ký tự xuống dòng

            switch (choice) {
                case 1:
                    // Hiển thị danh sách khách hàng
                    activeBank.showCustomers();
                    break;
                case 2:
                    // Nhập danh sách khách hàng từ tệp
                    System.out.print("Nhập đường dẫn tệp khách hàng: ");
                    String filePath = scanner.nextLine();
                    activeBank.importCustomersFromFile(filePath);
                    break;
                case 3:
                    // Thêm tài khoản ATM
                    System.out.print("Nhập mã số ID khách hàng: ");
                    String customerIdForAccount = scanner.nextLine(); // Đổi tên biến để tránh trùng
                    activeBank.addAtmAccount(customerIdForAccount);
                    break;
                case 4:
                    // Chuyển tiền
                    System.out.print("Nhập mã số ID của khách hàng: ");
                    String customerIdForTransfer = scanner.nextLine(); // Đổi tên biến để tránh trùng
                    activeBank.transferMoney(customerIdForTransfer);
                    break;

                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Chức năng chưa hỗ trợ.");
            }
        }
    }
}
