package Asm03;  // Thay bằng package thực tế của bạn

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private String name;
    private boolean isPremium;
    private List<Account> accounts = new ArrayList<>();
    private long balance;


    public Customer(String customerId, String name, boolean isPremium) {
        this.customerId = customerId;
        this.name = name;
        this.isPremium = isPremium;
        accounts.add(new Account("123456", 1_000_000));
        accounts.add(new Account("234567", 10_000_000));
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public boolean isPremiumAccount() { return isPremium; }
    public List<Account> getAccounts() { return accounts; }
    public long getBalance() {
        return (long) this.balance;
    }


    public void displayInformation() {
        String customerType = isPremium ? "Premium" : "Normal";
        System.out.printf("%-12s | %15s | %-9s | %,dđ%n",
                getCustomerId(), getName(), customerType, (long) getBalance());



        int index = 1;
        for (Account account : accounts) {
            System.out.printf("%d %10s |                               %,.0fđ%n",
                    index++, account.getAccountNumber(), account.getBalance());
        }
        System.out.println();
    }

    public boolean hasAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    public void addAccount(Account account) {
        if (!accounts.contains(account)) {
            accounts.add(account);
        } else {
            System.out.println("⚠️ Tài khoản đã tồn tại.");
        }
    }

    public boolean isPremium() {
        return getBalance() >= 10_000_000;  // Ví dụ: khách hàng có từ 10 triệu trở lên là Premium
    }

    public void displayAccounts() {
        System.out.println("Danh sách tài khoản của bạn:");
        for (Account account : accounts) {
            System.out.printf("- Số tài khoản: %s | Số dư: %,.0f VND\n", account.getAccountNumber(), account.getBalance());
        }
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }




}
