package javaTester.javaBasic;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Topic_12_String {
    public static void main(String[] args) {
//        String s1 = "Java";
//
//        String s2 = s1;
//
//        // Gán giá trị mới cho s1 >> giá trị này được lưu tại 1 vùng nhớ mới
//        s1 = "Uyennt";
//
//        String s3 = new String("Java");
//
//        System.out.println(s1 == s2);// so sánh giá trị và vùng nhớ
//        System.out.println(s1 == s3);
//        System.out.println(s1.equals(s3));// so sánh giá trị-value chứ không so sánh vùng nhớ
//        System.out.println(s2==s3);

// ======================================= //

       // WebDriver driver = new ChromeDriver();
        String schoolName = "Automation Testing Advanced";
        String schoolAddress = "Ha Noi City";
        String schoolCity = "AUTOMATION TESTING ADVANCED";
        // Phương thức:
        //- Độ dài - length
        System.out.println("Độ dài chuỗi là: "+ schoolName.length());

        //- Lấy ra 1 ký tự nào đó - charAt(index)
        System.out.println("Lấy ra 1 ký tự: " + schoolName.charAt(0));
        System.out.println("Lấy ra 1 ký tự: " + schoolName.charAt(1));

        //- Nối chuỗi - Concat hoặc dấu +
        System.out.println("Nối 2 chuỗi =: "+ schoolName.concat(schoolAddress));
        System.out.println("Nối 2 chuỗi =: "+ schoolName + schoolAddress);

        //- So sánh tuyệt đối - equals: phân biệt hoa thường
        System.out.println("Kiểm tra bằng nhau tuyệt đối: "+ schoolName.equals(schoolAddress));
        System.out.println("Kiểm tra bằng nhau tuyệt đối: "+ schoolName.equals("Automation Testing Advanced"));

        //So sánh tương đối - equalsIgnoreCase(): không phân biệt hoa thường
        System.out.println("Kiểm tra bằng nhau tuyệt đối: "+ schoolName.equals(schoolCity));

        //- Bắt đầu/Kết thúc/Chứa ký tự: startsWith()/endsWith/contains
        System.out.println("Bắt đầu bằng 1 ký tự hoặc 1 chuỗi ký tự: " + schoolName.startsWith("A"));
        System.out.println("Bắt đầu bằng 1 ký tự hoặc 1 chuỗi ký tự: " + schoolName.startsWith("Automation"));
        System.out.println("Bắt đầu bằng 1 ký tự hoặc 1 chuỗi ký tự: " + schoolName.startsWith("T"));

        System.out.println("Kết thúc bằng 1 ký tự hoặc 1 chuỗi ký tự: " + schoolName.endsWith("ced"));

        System.out.println("Chứa 1 ký tự hoặc một chuỗi ký tự: "+ schoolName.contains("Testing"));

        //- Vị trí của từ trong 1 chuỗi - index: Rất ít khi dùng
        // Trả về index của ký tự/chuỗi muốn tìm trong chuỗi
        System.out.println("Vị trí của 1 ký tự hoặc chuỗi ký tự trong chuỗi: " + schoolName.indexOf("Testing"));
        System.out.println("Vị trí của 1 ký tự hoặc chuỗi ký tự trong chuỗi: " + schoolName.indexOf("Automation"));
        System.out.println("Vị trí của 1 ký tự hoặc chuỗi ký tự trong chuỗi: " + schoolName.indexOf("A"));

        //- Chuỗi con từ vị trí - subString: Hàm tách chuỗi
        // Lấy ra 1 ký tự/chuỗi ký tự từ chuỗi cho trước
        // Rất hay dùng
        System.out.println("Tách chuỗi: " + schoolName.substring(11, 15));
        System.out.println("Tách chuỗi: " + schoolName.substring(11));
        //System.out.println("Tách chuỗi: " + schoolName.substring(30));


        //- Tách chuỗi thành mảng dựa vào ký tự hoặc chuỗi ký tự
        String result = "Viewing 48 of 132 results";
        String results[] = result.split(" "); // tách chuỗi thành mảng dựa vào ký tự  space: " "
        System.out.println(results[1]);


        //- Thay thế - replace: Hay dùng
        //
        String productPrice = "$100.00";
        productPrice = productPrice.replace("$", "");
        System.out.println(productPrice);

        //Ép kiểu sang float để thực hiện tính toán. Vì sau khi replace thì biến vẫn ở kiểu String
        float productPriceF = Float.parseFloat(productPrice);
        System.out.println(productPriceF);

        //Convert từ float sang String
        productPrice = String.valueOf(productPriceF);
        System.out.println(productPrice);

        //- Sắp xếp - sort

        //- Hoa/thường - upper/lower case
        // Giúp handle multiple OS: MAC/Windows
//        String osName = System.getProperty("os.name");
//        System.out.println(osName);
//        if (osName.toLowerCase().contains("windows")) {
//            Keys keys = Keys.CONTROL;
//        }else {
//            Keys keys = Keys.COMMAND;
//        }
        //Multiple browser: hay dùng hàm toUpperCase

        //- Cắt bỏ khoảng trắng/tab/xuống dòng đầu và cuối chuỗi - trim
        String helloWord = "  \n \t Hello World       ";
        System.out.println(helloWord.trim());
        System.out.println(helloWord);

        //Dynamic locator - Sau học


    }
}
