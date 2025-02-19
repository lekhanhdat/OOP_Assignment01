package Asm03;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionLogger {

    public static void printReceipt(String accountType, String accountNumber, double amount, double balance, double fee) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String transactionDate = sdf.format(new Date());

        System.out.println("+----------+------------------------------+----------+");
        System.out.printf("%30s\n", "BIEN LAI GIAO DICH " + accountType.toUpperCase());
        System.out.println("----------------------------------------------------");
        System.out.printf("%-10s: %s\n", "NGAY G/D", transactionDate);
        System.out.printf("%-10s: %s\n", "ATM ID", "DIGITAL-BANK-ATM 2022");
        System.out.printf("%-10s: %s\n", "SO TK", accountNumber);
        System.out.printf("%-10s: %,.0fđ\n", "SO TIEN", amount);
        System.out.printf("%-10s: %,.0fđ\n", "SO DU", balance);
        System.out.printf("%-10s: %,.0fđ\n", "PHI + VAT", fee);
        System.out.println("+----------+------------------------------+----------+");
    }
}
