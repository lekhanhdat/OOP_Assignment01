package Asm02;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Scanner;

public class Bank {
    private String id;
    private List<Customer> customers;
    private static final Scanner scanner = new Scanner(System.in);

    // Constructor
    public Bank() {
        this.id = generateId();
        this.customers = new ArrayList<>();
    }

    // Phương thức tạo ID ngẫu nhiên cho ngân hàng
    private String generateId() {
        return UUID.randomUUID().toString();
    }

    // Getter cho id
    public String getId() {
        return id;
    }

    // Thêm khách hàng mới nếu số CCCD chưa tồn tại
    public boolean addCustomer(Customer newCustomer) {
        if (isCustomerExisted(newCustomer.getCustomerId())) {
            System.out.println("Khách hàng đã tồn tại!");
            return false;
        }
        customers.add(newCustomer);
        return true;
    }

    // Kiểm tra khách hàng có tồn tại trong danh sách hay không
    public boolean isCustomerExisted(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }

    // Lấy danh sách tất cả khách hàng của ngân hàng
    public List<Customer> getCustomers() {
        return customers;
    }


    // Kiểm tra xem số tài khoản đã tồn tại hay chưa
    public boolean isAccountNumberExists(String accountNumber) {
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Chức năng 1: Thêm khách hàng vào ngân hàng
    public void addCustomer() {
        System.out.print("Nhap ten khach hang: ");
        String name = scanner.nextLine();

        String customerId;
        while (true) {
            System.out.print("Nhap so CCCD (12 chu so): ");
            customerId = scanner.nextLine();
            if (isValidCCCD(customerId)) {
                break;
            }
            System.out.println("CCCD khong hop le! Vui long nhap lai.");
        }

        if (this.isCustomerExisted(customerId)) {
            System.out.println("Khach hang da ton tai!");
        } else {
            Customer newCustomer = new Customer(name, customerId);
            this.addCustomer(newCustomer);
            System.out.println("Da them khach hang thanh cong!");
        }
    }
    // Kiểm tra CCCD hợp lệ
    private static boolean isValidCCCD(String cccd) {
        return cccd.matches("\\d{12}"); // Chỉ chứa 12 chữ số
    }

    // Chức năng 2
    public void addAccountToCustomer() {
        System.out.println("Nhập CCCD của khách hàng:");
        String customerId;
        Customer customer = null;

        // Bước 1: Kiểm tra CCCD hợp lệ và tồn tại
        while (true) {
            customerId = scanner.nextLine();
            customer = this.findCustomerById(customerId);
            if (customer != null) {
                break;
            } else {
                System.out.println("CCCD không tồn tại. Vui lòng nhập lại:");
            }
        }

        // Bước 2: Nhập số tài khoản (6 chữ số) và kiểm tra trùng lặp
        String accountNumber;
        while (true) {
            System.out.println("Nhập số tài khoản (6 chữ số):");
            accountNumber = scanner.nextLine();

            if (accountNumber.matches("\\d{6}") && !this.isAccountNumberExists(accountNumber)) {
                break;
            } else {
                System.out.println("Số tài khoản không hợp lệ hoặc đã tồn tại. Vui lòng nhập lại:");
            }
        }

        // Bước 3: Nhập số dư tài khoản (tối thiểu 50.000 VNĐ)
        double balance;
        while (true) {
            System.out.println("Nhập số dư tài khoản (tối thiểu 50.000 VNĐ):");
            balance = scanner.nextDouble();
            scanner.nextLine(); // Clear buffer

            if (balance >= 50_000) {
                break;
            } else {
                System.out.println("Số dư không hợp lệ. Vui lòng nhập số tiền lớn hơn hoặc bằng 50.000 VNĐ:");
            }
        }

        // Bước 4: Tạo tài khoản và thêm vào khách hàng
        Account newAccount = new Account(accountNumber, balance);
        customer.addAccount(newAccount);
        System.out.println("Thêm tài khoản thành công!");
    }

    // Chức năng 3
    public void displayCustomers() {
        for (Customer customer : customers) {
            String customerType = customer.isPremiumAccount() ? "Premium" : "Normal";
            System.out.printf("%-12s | %-20s | %-9s | %,dđ%n",
                    customer.getCustomerId(), customer.getName(), customerType, (long) customer.getBalance());

            int index = 1;
            for (Account account : customer.getAccounts()) {
                System.out.printf("%d %10s |                               %,dđ%n", index++, account.getAccountNumber(), (long) account.getBalance());
            }
            System.out.println(); // Xuống dòng giữa các khách hàng
        }
    }

    // Chức năng 4
    public Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public void searchCustomerById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập CCCD khách hàng: ");
        String customerId = scanner.nextLine();

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("❌ Không tìm thấy khách hàng với CCCD: " + customerId);
            return;
        }

        // Hiển thị thông tin theo format chức năng 3
        String customerType = customer.isPremiumAccount() ? "Premium" : "Normal";
        System.out.printf("%-12s | %-20s | %-9s | %,dđ%n",
                customer.getCustomerId(), customer.getName(), customerType, (long) customer.getBalance());

        int index = 1;
        for (Account account : customer.getAccounts()) {
            System.out.printf("%d %10s |                               %,dđ%n", index++, account.getAccountNumber(), (long) account.getBalance());
        }
        System.out.println();
    }

    // Chức năng nâng cao
    public void addAccount(String customerId, Account account) {
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Khách hàng không tồn tại!");
        }
        customer.addAccount(account);
    }

    // Chức năng 5
    public void findCustomersByName(String keyword) {
        boolean found = false;
        for (Customer customer : customers) {
            if (customer.getName().toLowerCase().contains(keyword.toLowerCase())) {
                found = true;
                displayCustomerInfo(customer);
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy khách hàng nào với từ khóa: " + keyword);
        }
    }

    // Tách riêng phần hiển thị thông tin khách hàng (dùng lại cho các chức năng khác)
    private void displayCustomerInfo(Customer customer) {
        String customerType = customer.isPremiumAccount() ? "Premium" : "Normal";
        System.out.printf("%-12s | %-20s | %-9s | %,dđ%n",
                customer.getCustomerId(), customer.getName(), customerType, (long) customer.getBalance());

        int index = 1;
        for (Account account : customer.getAccounts()) {
            System.out.printf("%d %10s |                               %,dđ%n",
                    index++, account.getAccountNumber(), (long) account.getBalance());
        }
        System.out.println(); // Xuống dòng giữa các khách hàng
    }


}
