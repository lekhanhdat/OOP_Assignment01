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
        double creditLimit;
        while (true) {
            System.out.print("Nhập hạn mức tín dụng: ");
            try {
                creditLimit = Double.parseDouble(scanner.nextLine());
                if (creditLimit <= 50000) {
                    System.out.println("⚠️ Hạn mức tín dụng phải lớn hơn 50.000đ.");
                    continue;
                } else if (creditLimit >= 100000001) {
                    System.out.println("⚠️ Hạn mức tín dụng phải nhỏ hơn 100.000.000đ.");
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

    public void withdrawMoney(String customerId) {
        System.out.println("===== RÚT TIỀN =====");
        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            System.out.println("⚠️ Không tìm thấy khách hàng.");
            return;
        }

        // Hiển thị tài khoản của khách hàng
        System.out.println("Chọn tài khoản rút tiền:");
        int index = 1;
        List<Account> accounts = customer.getAccounts();
        for (Account acc : accounts) {
            System.out.printf("%d. Số tài khoản: %s | Loại: %-8s | Số dư: %,.0fđ\n", index++, acc.getAccountNumber(), acc.getAccountType(), acc.getBalance());
        }
        System.out.print("Chọn tài khoản (1 - " + accounts.size() + "): ");
        int choice = Integer.parseInt(scanner.nextLine());
        Account selectedAccount = accounts.get(choice - 1);

        // Kiểm tra số tiền cần rút
        double withdrawAmount = 0;
        while (true) {
            System.out.print("Nhập số tiền cần rút: ");
            try {
                withdrawAmount = Double.parseDouble(scanner.nextLine());
                if (withdrawAmount < 50000) {
                    System.out.println("⚠️ Số tiền rút phải lớn hơn hoặc bằng 50.000đ.");
                    continue;
                }
                if (selectedAccount instanceof Account) {  // Tài khoản ATM
                    if (withdrawAmount > 5000000 && !selectedAccount.isPremium()) {
                        System.out.println("⚠️ Số tiền rút không được quá 5.000.000đ đối với tài khoản thường.");
                        continue;
                    }
                    if (withdrawAmount % 10000 != 0) {
                        System.out.println("⚠️ Số tiền rút phải là bội số của 10.000đ.");
                        continue;
                    }
                    double remainingBalance = selectedAccount.getBalance() - withdrawAmount;
                    if (remainingBalance < 50000) {
                        System.out.println("⚠️ Số dư còn lại phải lớn hơn hoặc bằng 50.000đ.");
                        continue;
                    }
                } else {  // Tài khoản tín dụng
                    CreditAccount creditAccount = (CreditAccount) selectedAccount;
                    if (withdrawAmount > creditAccount.getCreditLimit()) {
                        System.out.println("⚠️ Số tiền rút không được vượt quá hạn mức tín dụng.");
                        continue;
                    }
                    double fee = selectedAccount.isPremium() ? 0.01 : 0.05;
                    double feeAmount = withdrawAmount * fee;
                    double totalDeducted = withdrawAmount + feeAmount;
                    if (totalDeducted > creditAccount.getCreditLimit()) {
                        System.out.println("⚠️ Số dư tín dụng không đủ để chi trả phí giao dịch.");
                        continue;
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Vui lòng nhập số hợp lệ.");
            }
        }

        // Cập nhật tài khoản sau khi rút tiền
        if (selectedAccount instanceof Account) {  // Tài khoản ATM
            selectedAccount.balance -= withdrawAmount;
            System.out.println("✅ Rút tiền thành công! Số dư còn lại: " + String.format("%.2f", selectedAccount.getBalance()) + "đ");
        } else {  // Tài khoản tín dụng
            CreditAccount creditAccount = (CreditAccount) selectedAccount;
            double fee = selectedAccount.isPremium() ? 0.01 : 0.05;
            double feeAmount = withdrawAmount * fee;
            creditAccount.balance -= (withdrawAmount + feeAmount);
            System.out.println("✅ Rút tiền thành công! Hạn mức còn lại: " + String.format("%.2f", creditAccount.getCreditLimit() - creditAccount.balance) + "đ");
        }
        System.out.println("================================");
    }


}
