package Asm03;

public class Account {
    private String accountNumber;
    protected double balance;
    protected Customer customer;


    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void displayInformation() {
        System.out.printf("%-10s | %-6s | %,.0fđ\n", accountNumber, getAccountType(), balance);
    }

    public String getAccountType() {
        return this instanceof CreditAccount ? "LOAN" : "SAVINGS";
    }

    public boolean isAccountPremium() {
        return balance >= 10000000; // Premium nếu số dư >= 10 triệu
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    public boolean isPremium() {
        return balance >= 10000000; // Ví dụ: Premium nếu số dư >= 10 triệu
    }


}
