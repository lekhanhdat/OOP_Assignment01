package Asm02;

public class User {
    private String name;
    private String customerId;

    // Constructor
    public User(String name, String customerId) {
        this.name = name;
        setCustomerId(customerId); // Đảm bảo CCCD hợp lệ khi khởi tạo
    }

    // Getter và Setter cho name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter cho customerId
    public String getCustomerId() {
        return customerId;
    }

    // Setter cho customerId (chỉ cập nhật nếu hợp lệ)
    public void setCustomerId(String customerId) {
        if (isValidCustomerId(customerId)) {
            this.customerId = customerId;
        } else {
            System.out.println("CCCD không hợp lệ. Vui lòng nhập lại!");
        }
    }

    // Phương thức kiểm tra CCCD hợp lệ
    private boolean isValidCustomerId(String customerId) {
        return customerId != null && customerId.matches("\\d{12}");
    }

    // Phương thức hiển thị thông tin người dùng
    public void displayUserInfo() {
        System.out.println("Tên: " + name);
        System.out.println("CCCD: " + customerId);
    }
}
