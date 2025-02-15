import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class DigitalBankV1 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final Map<String, String> provinceCodes = new HashMap<>();

    static {
        provinceCodes.put("001", "Hà Nội");
        provinceCodes.put("002", "Hà Giang");
        provinceCodes.put("004", "Cao Bằng");
        provinceCodes.put("006", "Bắc Kạn");
        provinceCodes.put("008", "Tuyên Quang");
        provinceCodes.put("010", "Lào Cai");
        provinceCodes.put("011", "Điện Biên");
        provinceCodes.put("012", "Lai Châu");
        provinceCodes.put("014", "Sơn La");
        provinceCodes.put("015", "Yên Bái");
        provinceCodes.put("017", "Hòa Bình");
        provinceCodes.put("019", "Thái Nguyên");
        provinceCodes.put("020", "Lạng Sơn");
        provinceCodes.put("022", "Quảng Ninh");
        provinceCodes.put("024", "Bắc Giang");
        provinceCodes.put("025", "Phú Thọ");
        provinceCodes.put("026", "Vĩnh Phúc");
        provinceCodes.put("027", "Bắc Ninh");
        provinceCodes.put("030", "Hải Dương");
        provinceCodes.put("031", "Hải Phòng");
        provinceCodes.put("033", "Hưng Yên");
        provinceCodes.put("034", "Thái Bình");
        provinceCodes.put("035", "Hà Nam");
        provinceCodes.put("036", "Nam Định");
        provinceCodes.put("037", "Ninh Bình");
        provinceCodes.put("038", "Thanh Hóa");
        provinceCodes.put("040", "Nghệ An");
        provinceCodes.put("042", "Hà Tĩnh");
        provinceCodes.put("044", "Quảng Bình");
        provinceCodes.put("045", "Quảng Trị");
        provinceCodes.put("046", "Thừa Thiên Huế");
        provinceCodes.put("048", "Đà Nẵng");
        provinceCodes.put("049", "Quảng Nam");
        provinceCodes.put("051", "Quảng Ngãi");
        provinceCodes.put("052", "Bình Định");
        provinceCodes.put("054", "Phú Yên");
        provinceCodes.put("056", "Khánh Hòa");
        provinceCodes.put("058", "Ninh Thuận");
        provinceCodes.put("060", "Bình Thuận");
        provinceCodes.put("062", "Kon Tum");
        provinceCodes.put("064", "Gia Lai");
        provinceCodes.put("066", "Đắk Lắk");
        provinceCodes.put("067", "Đắk Nông");
        provinceCodes.put("068", "Lâm Đồng");
        provinceCodes.put("070", "Bình Phước");
        provinceCodes.put("072", "Tây Ninh");
        provinceCodes.put("074", "Bình Dương");
        provinceCodes.put("075", "Đồng Nai");
        provinceCodes.put("077", "Bà Rịa - Vũng Tàu");
        provinceCodes.put("079", "Hồ Chí Minh");
        provinceCodes.put("080", "Long An");
        provinceCodes.put("082", "Tiền Giang");
        provinceCodes.put("083", "Bến Tre");
        provinceCodes.put("084", "Trà Vinh");
        provinceCodes.put("086", "Vĩnh Long");
        provinceCodes.put("087", "Đồng Tháp");
        provinceCodes.put("089", "An Giang");
        provinceCodes.put("091", "Kiên Giang");
        provinceCodes.put("092", "Cần Thơ");
        provinceCodes.put("093", "Hậu Giang");
        provinceCodes.put("094", "Sóc Trăng");
        provinceCodes.put("095", "Bạc Liêu");
        provinceCodes.put("096", "Cà Mau");






        // Có thể thêm các mã tỉnh khác vào đây
    }

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            System.out.print("Chuc nang: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Loại bỏ ký tự xuống dòng

            switch (choice) {
                case 1:
                    enterCCCD();
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("+--------------------------------------------+");
        System.out.println("| NGAN HANG SO | FX20411@v1.0.0              |");
        System.out.println("+--------------------------------------------+");
        System.out.println("| 1. Nhap CCCD                               |");
        System.out.println("| 0. Thoat                                   |");
        System.out.println("+--------------------------------------------+");
    }

    private static void enterCCCD() {
        System.out.println("Chọn chế độ tạo mã bảo mật:");
        System.out.println("1. EASY (3 chữ số)");
        System.out.println("2. HARD (6 ký tự gồm chữ và số)");
        int securityLevel;
        while (true) {
            System.out.print("Nhập lựa chọn của bạn: ");
            securityLevel = scanner.nextInt();
            scanner.nextLine();

            if (securityLevel == 1 || securityLevel == 2) {
                break; // Thoát vòng lặp nếu nhập đúng
            }

            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn 1 hoặc 2.");
        }

        String securityCode;
        if (securityLevel == 1) {
            securityCode = String.format("%03d", random.nextInt(900) + 100);
        } else {
            securityCode = generateHardSecurityCode();
        }

        System.out.println("Mã bảo mật: " + securityCode);

        String userInput;
        do {
            System.out.print("Nhập mã bảo mật: ");
            userInput = scanner.nextLine();

            if (!userInput.equals(securityCode)) {
                System.out.println("Mã xác thực không đúng. Vui lòng thử lại!");
            }
        } while (!userInput.equals(securityCode));

        String cccd;
        do {
            System.out.print("Xác thực thành công! Nhập số CCCD: ");
            cccd = scanner.nextLine();
            if (!isValidCCCD(cccd)) {
                System.out.println("CCCD không hợp lệ. Vui lòng nhập lại!");
            }
        } while (!isValidCCCD(cccd));
        processCCCD(cccd);
    }

    private static String generateHardSecurityCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private static boolean isValidCCCD(String cccd) {
        return cccd.matches("\\d{12}");
    }

    private static void processCCCD(String cccd) {
        int choice;
        do {
            showCCCDMenu();
            System.out.print("Chọn chức năng: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    checkPlaceOfBirth(cccd);
                    break;
                case 2:
                    checkBirthYearAndGender(cccd);
                    break;
                case 3:
                    checkRandomNumber(cccd);
                    break;
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }

    private static void showCCCDMenu() {
        System.out.println("+--------------------------------------------+");
        System.out.println("| 1. Kiểm tra nơi sinh                       |");
        System.out.println("| 2. Kiểm tra năm sinh, giới tính            |");
        System.out.println("| 3. Kiểm tra số ngẫu nhiên                  |");
        System.out.println("| 0. Thoát                                   |");
        System.out.println("+--------------------------------------------+");
    }

    private static void checkPlaceOfBirth(String cccd) {
        String provinceCode = cccd.substring(0, 3);
        String province = provinceCodes.getOrDefault(provinceCode, "Không xác định");
        System.out.println("Nơi sinh: " + province);
    }

    private static void checkBirthYearAndGender(String cccd) {
        char genderCode = cccd.charAt(3);
        int birthYear = Integer.parseInt(cccd.substring(4, 6));
        String gender;
        int century;

        switch (genderCode) {
            case '0': case '1':
                century = 1900; gender = (genderCode == '0') ? "Nam" : "Nữ";
                break;
            case '2': case '3':
                century = 2000; gender = (genderCode == '2') ? "Nam" : "Nữ";
                break;
            case '4': case '5':
                century = 2100; gender = (genderCode == '4') ? "Nam" : "Nữ";
                break;
            case '6': case '7':
                century = 2200; gender = (genderCode == '6') ? "Nam" : "Nữ";
                break;
            case '8': case '9':
                century = 2300; gender = (genderCode == '8') ? "Nam" : "Nữ";
                break;
            default:
                century = 0; gender = "Không xác định";
        }
        System.out.print("Giới tính: " + gender + " | ");
        System.out.println("Năm sinh: " + (century + birthYear));
    }

    private static void checkRandomNumber(String cccd) {
        String randomNumber = cccd.substring(6);
        System.out.println("Số ngẫu nhiên: " + randomNumber);
    }
}
