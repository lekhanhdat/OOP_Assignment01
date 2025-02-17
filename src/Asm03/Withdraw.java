package Asm03;

import java.util.Scanner;

public class Withdraw {
    public static void withdrawMoney(Customer customer, Scanner scanner) {
        if (customer.getAccounts().isEmpty()) {
            System.out.println("⚠️ Khách hàng không có tài khoản nào.");
            return;
        }

        // Hiển thị danh sách tài khoản
        System.out.println("Chọn tài khoản muốn rút tiền:");
        customer.displayAccounts();

        System.out.print("Nhập số tài khoản: ");
        String accountNumber = scanner.nextLine();
        Account account = customer.getAccountByAccountNumber(accountNumber);

        if (account == null) {
            System.out.println("⚠️ Tài khoản không tồn tại.");
            return;
        }

        // Nhập số tiền cần rút
        System.out.print("Nhập số tiền muốn rút: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer

        if (account.withdraw(amount)) {
            System.out.println("✅ Rút tiền thành công.");
            logTransaction(account, amount);
        } else {
            System.out.println("❌ Rút tiền thất bại.");
        }
    }

    // Ghi log giao dịch (hiển thị biên lai)
    private static void logTransaction(Account account, double amount) {
        System.out.println("====== BIÊN LAI GIAO DỊCH ======");
        System.out.printf("Số tài khoản: %s\n", account.getAccountNumber());
        System.out.printf("Số tiền rút: %,.2f VND\n", amount);
        System.out.printf("Số dư còn lại: %,.2f VND\n", account.getBalance());
        System.out.println("===============================");
    }
}

