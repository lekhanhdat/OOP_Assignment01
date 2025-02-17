package Asm03;

public class Account {
    private String accountNumber;
    protected long balance;
    protected Customer customer;


    public Account(String accountNumber, long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void displayInformation() {
        System.out.printf("%-10s | %-6s | %,dđ\n", accountNumber, getAccountType(), balance);
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

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount % 10000 != 0) {
            System.out.println("⚠️ Số tiền rút phải là bội số của 10.000đ.");
            return false;
        }

        if (balance - amount < 50000) {
            System.out.println("⚠️ Số dư còn lại sau khi rút phải >= 50.000đ.");
            return false;
        }

        balance -= amount;
        return true;
    }

    public boolean isPremium() {
        return balance >= 10000000; // Ví dụ: Premium nếu số dư >= 10 triệu
    }


    protected void setBalance(long balance) {
        this.balance = balance;
    }


}
