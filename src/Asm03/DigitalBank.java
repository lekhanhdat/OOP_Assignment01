package Asm03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DigitalBank {
    private List<Customer> customers = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public DigitalBank() {
        customers.add(new Customer("048203002547", "FUNIX", true));
    }

    public Customer getCustomerById(String id) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        for (Customer customer : customers) {
            Account acc = customer.getAccountByAccountNumber(accountNumber);
            if (acc != null) {
                return acc;
            }
        }
        return null;
    }

    public void addAtmAccount(String customerId) {
        System.out.println("===== THÊM TÀI KHOẢN ATM =====");

        // Nhập số tài khoản
        String accountNumber;
        while (true) {
            System.out.print("Nhập số tài khoản (6 chữ số): ");
            accountNumber = scanner.nextLine();

            if (!accountNumber.matches("\\d{6}")) {
                System.out.println("⚠️ Số tài khoản phải có đúng 6 chữ số.");
                continue;
            }

            if (isAccountExists(customerId, accountNumber)) {
                System.out.println("⚠️ Số tài khoản đã tồn tại.");
                continue;
            }
            break;
        }

        // Nhập số dư ban đầu
        long initialBalance;
        while (true) {
            System.out.print("Nhập số dư ban đầu: ");
            try {
                initialBalance = Long.parseLong(scanner.nextLine());  // ✅ Dùng scanner.nextLine() để tránh lỗi trôi dòng
                if (initialBalance <= 0) {
                    System.out.println("⚠️ Số dư phải lớn hơn 0.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Vui lòng nhập số hợp lệ.");
            }
        }

        // Thêm tài khoản vào danh sách khách hàng
        Account newAccount = new Account(accountNumber, initialBalance);
        if (addAccountToCustomer(customerId, newAccount)) {
            System.out.println("✅ Thêm tài khoản ATM thành công!");
        } else {
            System.out.println("❌ Thêm tài khoản thất bại.");
        }

        System.out.println("================================");
    }


    public boolean isAccountExists(String customerId, String accountNumber) {
        Customer customer = getCustomerById(customerId);
        if (customer != null) {
            return customer.hasAccount(accountNumber);
        }
        return false;
    }

    public boolean addAccountToCustomer(String customerId, Account account) {
        Customer customer = getCustomerById(customerId);
        if (customer != null) {
            customer.addAccount(account);
            return true;
        }
        return false;
    }

    public void showCustomer(String customerId) {
        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            System.out.println("⚠️ Không tìm thấy khách hàng.");
            return;
        }

        // Hiển thị thông tin khách hàng
        System.out.printf("%-12s | %-10s | %-8s | %,.0fđ\n",
                customer.getCustomerId(), customer.getName(),
                customer.isPremium() ? "Premium" : "Normal", customer.getBalance());

        // Hiển thị thông tin tài khoản của khách hàng
        int index = 1;
        for (Account acc : customer.getAccounts()) {
            System.out.printf("%-3d %-8s | %-10s |            %,.0fđ\n",
                    index++, acc.getAccountNumber(), acc.getAccountType(), acc.getBalance());
        }
    }


    public void addCreditAccount(String customerId) {
        System.out.println("===== THÊM TÀI KHOẢN TÍN DỤNG =====");

        // Nhập số tài khoản
        String accountNumber;
        while (true) {
            System.out.print("Nhập số tài khoản (6 chữ số): ");
            accountNumber = scanner.nextLine();

            if (!accountNumber.matches("\\d{6}")) {
                System.out.println("⚠️ Số tài khoản phải có đúng 6 chữ số.");
                continue;
            }

            if (isAccountExists(customerId, accountNumber)) {
                System.out.println("⚠️ Số tài khoản đã tồn tại.");
                continue;
            }

            break;
        }

        // Nhập hạn mức tín dụng
        long creditLimit;
        while (true) {
            System.out.print("Nhập hạn mức tín dụng: ");
            try {
                creditLimit = Long.parseLong(scanner.nextLine());
                if (creditLimit <= 0) {
                    System.out.println("⚠️ Hạn mức tín dụng phải lớn hơn 0.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Vui lòng nhập số hợp lệ.");
            }
        }

        // Tạo tài khoản tín dụng
        CreditAccount newAccount = new CreditAccount(accountNumber, creditLimit);
        if (addAccountToCustomer(customerId, newAccount)) {
            System.out.println("✅ Thêm tài khoản tín dụng thành công!");
        } else {
            System.out.println("❌ Thêm tài khoản thất bại.");
        }

        System.out.println("================================");
    }

}
