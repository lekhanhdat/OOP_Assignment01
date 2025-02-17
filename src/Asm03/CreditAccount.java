package Asm03;

public class CreditAccount extends Account {
    private double creditLimit;


    public CreditAccount(String accountNumber, double creditLimit) {
        super(accountNumber, 0); // Tài khoản tín dụng ban đầu có số dư 0
        this.creditLimit = creditLimit;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void displayInformation() {
        super.displayInformation();
        System.out.printf("Hạn mức tín dụng: %,.0f VND\n", creditLimit);
    }




}
