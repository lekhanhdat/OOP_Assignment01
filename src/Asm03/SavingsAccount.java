//package Asm03;
//
//public class SavingsAccount extends Account {
//
//    public SavingsAccount(String accountNumber, double balance) {
//        super(accountNumber, balance);
//    }
//
//    @Override
//    public boolean withdraw(double amount) {
//        if (amount < 50000) {
//            System.out.println("⚠️ Số tiền rút phải lớn hơn hoặc bằng 50.000đ.");
//            return false;
//        }
//
//        if (amount % 10000 != 0) {
//            System.out.println("⚠️ Số tiền rút phải là bội số của 10.000đ.");
//            return false;
//        }
//
//        boolean isPremium = getBalance() >= 10_000_000;
//        if (!isPremium && amount > 5000000) {
//            System.out.println("⚠️ Tài khoản thường không được rút quá 5.000.000đ/lần.");
//            return false;
//        }
//
//        if (getBalance() - amount < 50000) {
//            System.out.println("⚠️ Số dư sau khi rút phải lớn hơn hoặc bằng 50.000đ.");
//            return false;
//        }
//
//        setBalance(getBalance() - amount);
//        System.out.printf("✅ Rút tiền thành công: -%,.0fđ | Số dư còn lại: %,.0fđ\n", amount, getBalance());
//        return true;
//    }
//}
