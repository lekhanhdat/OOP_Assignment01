package Asm02;

public class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        if (isValidAccountNumber(accountNumber)) {
            this.accountNumber = accountNumber;
        } else {
            throw new IllegalArgumentException("Số tài khoản không hợp lệ! Phải gồm 6 chữ số.");
        }
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        if (isValidAccountNumber(accountNumber)) {
            this.accountNumber = accountNumber;
        } else {
            throw new IllegalArgumentException("Số tài khoản không hợp lệ! Phải gồm 6 chữ số.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException("Số dư không thể âm!");
        }
    }

    public boolean isPremiumAccount() {
        return balance >= 10_000_000;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %,.0fđ", accountNumber, balance);
    }

    private boolean isValidAccountNumber(String accountNumber) {
        return accountNumber.matches("\\d{6}");
    }

    public static void main(String[] args) {
        Account account1 = new Account("123456", 10_000_000);
        System.out.println(account1);
//        System.out.println("Premium Account: " + acc1.isPremiumAccount());

        Account account2 = new Account("654321", 5_000_000);
        System.out.println(account2);
//        System.out.println("Premium Account: " + acc2.isPremiumAccount());
    }
}
