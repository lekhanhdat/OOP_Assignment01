package Asm04;

import java.io.*;
import java.util.*;

public class DigitalBank {
    private List<String[]> customersData = new ArrayList<>();  // Lưu trữ dữ liệu khách hàng dưới dạng mảng

    // Phương thức nhập dữ liệu khách hàng từ tệp
    public void importCustomersFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String currentCustomerId = null;
            double totalBalance = 0;
            boolean isNewCustomer = false;  // Kiểm tra khách hàng mới

            while ((line = br.readLine()) != null) {
                line = line.trim();

                // Bỏ qua dòng trống
                if (line.isEmpty()) {
                    continue;
                }

                String[] customerData = line.split("\\|");

                // Kiểm tra xem dòng có phải là dữ liệu khách hàng (ID khách hàng gồm 12 chữ số)
                if (customerData.length == 4 && customerData[0].trim().matches("\\d{12}")) {
                    // Đọc thông tin khách hàng
                    currentCustomerId = customerData[0].trim();
                    String customerName = customerData[1].trim();
                    totalBalance = Double.parseDouble(customerData[3].trim());  // Lấy tổng số dư

                    // Kiểm tra khách hàng đã tồn tại
                    boolean customerExists = false;
                    for (String[] existingCustomerData : customersData) {
                        if (existingCustomerData[0].equals(currentCustomerId)) {
                            customerExists = true;
                            break;
                        }
                    }

                    // Thông báo nếu khách hàng đã tồn tại
                    if (customerExists) {
                        System.out.println("Khách hàng " + currentCustomerId + " đã tồn tại, thêm khách hàng không thành công.");
                    } else {
                        // Lưu thông tin khách hàng vào danh sách
                        String[] customerInfo = {currentCustomerId, customerName, customerData[2].trim(), String.valueOf(totalBalance)};
                        customersData.add(customerInfo);
                        System.out.println("Đã thêm khách hàng " + currentCustomerId + " vào danh sách khách hàng.");
                    }

                    // Đặt flag khách hàng mới là true
                    isNewCustomer = true;
                }
                // Kiểm tra dòng tài khoản ngân hàng (số thứ tự tài khoản như 1, 2, 3...)
                else if (customerData.length == 4 && customerData[0].trim().matches("\\d+")) {
                    // Thêm tài khoản vào danh sách tài khoản (hiển thị cứng là SAVINGS)
                    String accountNumber = customerData[1].trim();
                    double accountBalance = 0;

                    try {
                        accountBalance = Double.parseDouble(customerData[3].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi khi định dạng số dư tài khoản: " + e.getMessage());
                        continue;  // Bỏ qua tài khoản này nếu có lỗi
                    }



                    // Lưu tài khoản vào danh sách tài khoản ngân hàng
                    String accountInfo = String.format("  %-12s | %-10s | %-8s | %,.0fđ", customerData[0].trim(), accountNumber, "SAVINGS", accountBalance);
                    customersData.add(new String[]{accountInfo});
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tệp không tồn tại");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc tệp khách hàng: " + e.getMessage());
        }
    }

    // Hiển thị thông tin tất cả khách hàng
    public void showCustomers() {
        if (customersData.isEmpty()) {
            System.out.println("Chưa có khách hàng nào trong hệ thống.");
            return;
        }

        System.out.println("+--------------+--------------------------+-----------------+----------------+");
        System.out.println("| Mã khách hàng | Tên khách hàng          | Loại khách hàng | Tổng số dư     |");
        System.out.println("+--------------+--------------------------+-----------------+----------------+");

        // Duyệt qua tất cả khách hàng
        for (String[] customerData : customersData) {
            if (customerData.length == 4) {
                // Lấy thông tin khách hàng
                String customerId = customerData[0];
                String customerName = customerData[1];
                String customerType = customerData[2];
                double totalBalance = 0;

                // Tính tổng số dư của khách hàng bằng cách cộng tất cả số dư tài khoản của khách hàng này
                List<String[]> userAccounts = new ArrayList<>();
                for (String[] accountData : customersData) {
                    if (accountData[0].equals(customerId)) {
                        userAccounts.add(accountData); // Lưu thông tin tài khoản ngân hàng của khách hàng
                        totalBalance += Double.parseDouble(accountData[3]); // Cộng dồn số dư tài khoản
                    }
                }

                // Hiển thị thông tin khách hàng
                System.out.printf("%-15s | %-20s | %-15s | %,.0fđ\n", customerId, customerName, customerType, totalBalance);

                // Hiển thị tất cả các tài khoản ngân hàng của khách hàng
                int accountNumber = 1;
                for (String[] accountData : userAccounts) {
                    // Đảm bảo hiển thị đúng thông tin: Số tài khoản, loại tài khoản (SAVINGS), và số dư của tài khoản đó
                    String accountNumberStr = accountData[1];  // Đây mới là số tài khoản
                    double accountBalance = Double.parseDouble(accountData[3]);  // Đây là số dư của tài khoản ngân hàng

                    // Hiển thị thông tin tài khoản ngân hàng (chỉ hiển thị "SAVINGS" cho loại tài khoản)
                    System.out.printf("  %-12d | %-10s | %-8s | %,.0fđ\n", accountNumber, accountNumberStr, "SAVINGS", accountBalance);
                    accountNumber++;
                }
            }
        }

        System.out.println("+-------------------------------------------------------------------------------+");
    }



    public void addAtmAccount(String customerId) {
        // Kiểm tra xem mã số ID khách hàng có tồn tại trong hệ thống không
        boolean customerExists = false;
        String[] currentCustomer = null;

        // Tìm kiếm khách hàng và lấy thông tin của họ
        for (String[] customerData : customersData) {
            if (customerData[0].equals(customerId)) {
                customerExists = true;
                currentCustomer = customerData;  // Lưu thông tin khách hàng
                break;
            }
        }

        if (!customerExists) {
            System.out.println("Không tìm thấy khách hàng " + customerId + ", tác vụ không thành công.");
            return;
        }

        // Nếu khách hàng tồn tại, yêu cầu nhập số tài khoản và số dư
        Scanner scanner = new Scanner(System.in);

        // Lấy danh sách tài khoản của khách hàng
        int newAccountNumber = 1;
        for (String[] accountData : customersData) {
            // Tìm các tài khoản của khách hàng và tính số tài khoản mới
            if (accountData[0].equals(customerId)) {
                newAccountNumber++;
            }
        }

        System.out.print("Nhập số tài khoản (6 chữ số): ");
        String accountNumber = scanner.nextLine().trim();

        // Kiểm tra số tài khoản hợp lệ (6 chữ số)
        if (accountNumber.length() != 6 || !accountNumber.matches("\\d+")) {
            System.out.println("Số tài khoản không hợp lệ, phải gồm 6 chữ số.");
            return;
        }

        double accountBalance = 0;
        while (true) {
            System.out.print("Nhập số dư tài khoản (phải trên 50.000đ): ");
            try {
                accountBalance = Double.parseDouble(scanner.nextLine().trim());
                if (accountBalance >= 50000) {
                    break; // Nếu số dư hợp lệ, thoát khỏi vòng lặp
                } else {
                    System.out.println("Số dư tài khoản phải lớn hơn 50.000đ. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Số dư không hợp lệ. Vui lòng nhập lại.");
            }
        }

        // Thêm tài khoản vào hệ thống (xếp theo số thứ tự)
        String accountInfo = String.format("  %-12s | %-10s | %-8s | %,.0fđ", newAccountNumber, accountNumber, "SAVINGS", accountBalance);
        customersData.add(new String[]{accountInfo});

        // Cập nhật tổng số dư của khách hàng (tính tổng số dư tài khoản)
        double currentTotalBalance = Double.parseDouble(currentCustomer[3]);
        currentTotalBalance += accountBalance;
        currentCustomer[3] = String.valueOf(currentTotalBalance);  // Cập nhật tổng số dư của khách hàng

        System.out.println("Tạo tài khoản thành công!");
    }


    // Chuyển tiền giữa các tài khoản
    public void transferMoney(String customerId) {
        // Kiểm tra xem mã số ID khách hàng có tồn tại trong hệ thống không
        boolean customerExists = false;
        String[] currentCustomer = null;

        // Tìm kiếm khách hàng và lấy thông tin của họ
        for (String[] customerData : customersData) {
            if (customerData[0].equals(customerId)) {
                customerExists = true;
                currentCustomer = customerData;  // Lưu thông tin khách hàng
                break;
            }
        }

        if (!customerExists) {
            System.out.println("Không tìm thấy khách hàng " + customerId + ", tác vụ không thành công.");
            return;
        }

        // Hiển thị danh sách tài khoản ngân hàng của khách hàng
        System.out.println("Danh sách tài khoản của khách hàng " + customerId + ":");
        List<String> userAccounts = new ArrayList<>();
        for (String[] accountData : customersData) {
            if (accountData[0].equals(customerId)) {
                userAccounts.add(accountData[1]);  // Thêm số tài khoản vào danh sách
                System.out.println(accountData[1] + " | " + accountData[2] + " | " + accountData[3]);
            }
        }

        if (userAccounts.isEmpty()) {
            System.out.println("Khách hàng không có tài khoản ngân hàng.");
            return;
        }

        // Nhập số tài khoản gửi
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số tài khoản gửi: ");
        String sendAccount = scanner.nextLine().trim();

        // Kiểm tra số tài khoản gửi có thuộc danh sách tài khoản của khách hàng không
        if (!userAccounts.contains(sendAccount)) {
            System.out.println("Tài khoản gửi không tồn tại hoặc không hợp lệ.");
            return;
        }

        // Lấy số dư tài khoản gửi
        double sendAccountBalance = 0;
        for (String[] accountData : customersData) {
            if (accountData[0].equals(customerId) && accountData[1].equals(sendAccount)) {
                sendAccountBalance = Double.parseDouble(accountData[3]); // Lấy số dư tài khoản gửi
                break;
            }
        }

        // Nhập số tài khoản nhận
        System.out.print("Nhập số tài khoản nhận (6 chữ số): ");
        String receiveAccount = scanner.nextLine().trim();

        // Kiểm tra số tài khoản nhận hợp lệ (6 chữ số), không trùng với tài khoản gửi và có trong hệ thống
        if (receiveAccount.length() != 6 || !receiveAccount.matches("\\d+") || receiveAccount.equals(sendAccount)) {
            System.out.println("Số tài khoản nhận không hợp lệ hoặc trùng với tài khoản gửi.");
            return;
        }

        boolean receiveAccountExists = false;
        double receiveAccountBalance = 0;
        for (String[] accountData : customersData) {
            if (accountData[1].equals(receiveAccount)) {
                receiveAccountExists = true;
                receiveAccountBalance = Double.parseDouble(accountData[3]);
                break;
            }
        }

        if (!receiveAccountExists) {
            System.out.println("Tài khoản nhận không tồn tại.");
            return;
        }

        // Nhập số tiền chuyển
        System.out.print("Nhập số tiền chuyển: ");
        double transferAmount = 0;
        try {
            transferAmount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Số tiền không hợp lệ.");
            return;
        }

        if (transferAmount < 50000) {
            System.out.println("Số tiền chuyển phải lớn hơn hoặc bằng 50.000đ.");
            return;
        }

        if (sendAccountBalance < transferAmount) {
            System.out.println("Số dư tài khoản gửi không đủ để thực hiện giao dịch.");
            return;
        }

        // Xác nhận chuyển tiền
        System.out.println("Xác nhận thực hiện chuyển " + transferAmount + "đ từ tài khoản [" + sendAccount + "] đến tài khoản [" + receiveAccount + "] (Y/N): ");
        String confirm = scanner.nextLine().trim().toUpperCase();
        if (!confirm.equals("Y")) {
            System.out.println("Giao dịch đã bị hủy.");
            return;
        }

        // Thực hiện giao dịch
        // Cập nhật số dư tài khoản gửi và nhận
        for (String[] accountData : customersData) {
            if (accountData[0].equals(customerId) && accountData[1].equals(sendAccount)) {
                accountData[3] = String.valueOf(sendAccountBalance - transferAmount);
            }
            if (accountData[1].equals(receiveAccount)) {
                accountData[3] = String.valueOf(receiveAccountBalance + transferAmount);
            }
        }

        // Cập nhật lại tổng số dư của khách hàng (chỉ cộng số dư tài khoản gửi và nhận đúng một lần)
        for (String[] accountData : customersData) {
            if (accountData[0].equals(customerId) && accountData[1].equals(sendAccount)) {
                double currentTotalBalance = Double.parseDouble(accountData[3]);
                currentTotalBalance -= transferAmount;
                accountData[3] = String.valueOf(currentTotalBalance);
            }
            if (accountData[1].equals(receiveAccount)) {
                double currentTotalBalance = Double.parseDouble(accountData[3]);
                currentTotalBalance += transferAmount;
                accountData[3] = String.valueOf(currentTotalBalance);
            }
        }

        // In biên lai giao dịch
        System.out.println("Chuyển tiền thành công, biên lai giao dịch:");
        System.out.println("+--------------------------- BIÊN LAI GIAO DỊCH SAVINGS ---------------------------+");
        System.out.println("| NGÀY GD: " + new java.util.Date());
        System.out.println("| ATM ID: DIGITAL-BANK-ATM 2022");
        System.out.println("| SỐ TK GỬI: " + sendAccount);
        System.out.println("| SỐ TK NHẬN: " + receiveAccount);
        System.out.println("| SỐ TIỀN CHUYỂN: " + transferAmount);
        System.out.println("| SỐ DƯ TK GỬI SAU GIAO DỊCH: " + (sendAccountBalance - transferAmount));
        System.out.println("| SỐ DƯ TK NHẬN SAU GIAO DỊCH: " + (receiveAccountBalance + transferAmount));
        System.out.println("+-------------------------------------------------------------------------------+");
    }


}
