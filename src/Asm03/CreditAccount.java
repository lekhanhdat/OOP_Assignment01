package Asm03;

public class CreditAccount extends Account {
    private long creditLimit;


    public CreditAccount(String accountNumber, long creditLimit) {
        super(accountNumber, 0); // Tài khoản tín dụng ban đầu có số dư 0
        this.creditLimit = creditLimit;
    }

    public long getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(long creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void displayInformation() {
        super.displayInformation();
        System.out.printf("Hạn mức tín dụng: %,d VND\n", creditLimit);
    }

    @Override
    public boolean withdraw(double amount) {
        double feeRate = isPremium() ? 0.01 : 0.05;
        long totalAmount = (long) (amount + (amount * feeRate)); // Ép kiểu tại đây
        long currentBalance = (long) getBalance();

        if (totalAmount > 100000000) {
            System.out.println("⚠️ Hạn mức không được vượt quá 100.000.000đ.");
            return false;
        }

        if (currentBalance - totalAmount < 50000) { // Kiểm tra hạn mức tối thiểu
            System.out.println("⚠️ Hạn mức còn lại sau khi rút phải >= 50.000đ.");
            return false;
        }

        // Cập nhật số dư mới
        setBalance(currentBalance - totalAmount);
        return true;
    }







}
