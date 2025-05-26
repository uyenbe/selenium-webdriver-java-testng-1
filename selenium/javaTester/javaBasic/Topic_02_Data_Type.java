package javaTester.javaBasic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class Topic_02_Data_Type {

    // Biến khai báo bên ngoài hàm main: Globle varibale
    static int number;
    String name = "UyenNT7";

    //II. Kiểu dữ liệu trong java có 2 kiểu:
//    2.1 Kiểu dữ liệu nguyên thuỷ: Primitive type
//     - Có 8 kiểu dữ liệu:
//     1. Kiểu số nguyên: byte - short - int - long >> Số không có phần thập phân
        byte bnumber;
        short snumber;
        int inumber;
        long lnumber;
//     2. Kiểu số thực: float - double có phần thập phân
         float fNumber;
        //= 9.99f; (phải có chữ f/F ở phía sau để phân biệt với double
         double  dNumber; // = 9.99d;
//     3. Kiểu ký tự: char >> chỉ chứa 1 ký tự
        char cChar = 'A';
//     4. Kiểu logic: boolean
        boolean bBoolean = true;

//        2.2 Kiểu dữ liệu tham chiếu/ không nguyên thuỷ: Reference Type - Do user tự định nghĩa ra
//        1. Class
            Topic_02_Data_Type topic;// topic là 1 biến đại diện cho class topic 02

//        2. Object: có thể convert qua các kiểu dữ liệu khác được
            Objects aObject;

//        3. String
               String[] address = {"Thai Binh"};
//
//        4. Array: là kiểu dữ liệu được define trước số lượng phần tử trong array (mảng)
            //
            String[] studentAddress= {Arrays.toString(address),"HN", "Thai Binh"};
            Integer[] studentNumber= {6,5,7};
//        5. Interface
           WebDriver driver;

//        6. Collection: dữ liệu kiểu list/set/Queue/Map
                List<WebElement> linkPage = driver.findElements(By.cssSelector(""));
                // Kiểu List lưu trùng data

                List<String> productName = new ArrayList<String>();

                Set<String> textPage = driver.getWindowHandles();
                // Kiểu Set không lưu trùng data

    // Phân biệt biệt biến nguyên thuỷ và biến tham chiếu:
    //1. Biến nguyên thuỷ:
    //- Không có function đi theo - VD minh hoạ bên hàm clickToElement
    // - Cách thức lưu trữ data: Kiểu nguyên thuỷ lưu trữ data trong chính bản thân nó (vùng nhớ Stack)



    //2. Biến tham chiếu
    // - Có những function đi theo, tuỳ vào loại biến - VD minh hoạ bên hàm clickToElement
    // - Cách thức lưu trữ data: Kiểu tham chiếu chỉ lưu trữ 1 giá trị là địa chỉ vùng nhớ mà nó tham chiếu đến (vùng nhớ Heap)



    public void clickToElement(){
        //address.length(2); // biến tham chiếu
       // bnumber.// biến nguyên thuỷ

    }









    public static void main(String[] args) {
        // Biến khai báo bên trong hàm main: Local variable
      int stdNumber = 2;
      System.out.println(number);
      System.out.println(stdNumber);

      // Muốn truy cập biến name (có kiểu - String khác với kiểu của hàm main - staic): truy cập thông qua đối tượng/class
      // Sau đó thông qua đối tượng đó để truy cập vào biến name
      Topic_02_Data_Type topic = new Topic_02_Data_Type();
      System.out.println(topic.name);
    }

    /* III. Cách khai báo biến để lưu trữ 1 kiểu dữ liệu:
    1. Phạm vi truy cập: public, private, protected, default
    2. Kiểu dữ liệu
    3. Tên biến
    4. Giá trị của biến: gán với phép =
    Nếu như không gán giá trị >> lấy dữ liệu mặc định
    VD: public int studentNumber = 200;
    * */

    


}
