package Asm02;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<Account> accounts;

    public Customer(String name, String customerId) {
        super(name, customerId);
        this.accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public boolean isPremiumAccount() {
        for (Account account : accounts) {
            if (account.isPremiumAccount()) {
                return true;
            }
        }
        return false;
    }

    public void addAccount(Account newAccount) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(newAccount.getAccountNumber())) {
                throw new IllegalArgumentException("Tài khoản đã tồn tại!");
            }
        }
        accounts.add(newAccount);
    }


    public double getBalance() {
        double totalBalance = 0;
        for (Account account : accounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    public void displayInformation() {
        String accountType = isPremiumAccount() ? "Premium" : "Normal";
        System.out.printf("%-13s | %-20s | %-10s | %,.0fđ%n", getCustomerId(), getName(), accountType, getBalance());

        for (int i = 0; i < accounts.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, accounts.get(i));
        }
    }

    public void setCustomerId(String customerId) {
        if (!isValidCCCD(customerId)) {
            throw new IllegalArgumentException("Số CCCD không hợp lệ!");
        }
        super.setCustomerId(customerId);
    }

    // Hàm kiểm tra CCCD hợp lệ
    private boolean isValidCCCD(String cccd) {
        return cccd.matches("\\d{12}"); // Giả sử CCCD có đúng 12 chữ số
    }

    public static void main(String[] args) {
        Customer customer = new Customer("Nguyễn Văn A", "012345678901");
        Account account1 = new Account("123456", 10_000_000);
        Account account2 = new Account("654321", 5_000_000);

        customer.addAccount(account1);
        customer.addAccount(account2);
        customer.displayInformation();
    }
}
