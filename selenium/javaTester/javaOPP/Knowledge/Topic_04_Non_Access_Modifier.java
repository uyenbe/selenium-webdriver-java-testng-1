package javaTester.javaOPP.Knowledge;

public class Topic_04_Non_Access_Modifier {
    //1. Static: Variable/Method
    //Dùng cho tất cả các instance/object
    //Phạm vi cho toàn bộ system sử dụng nó
    //Có thể Override được - gán lại
    //Lưu ý: Không có static Class. Chỉ dùng trong TH Nested Class - Lớp lồng
    public static class NestedClass {

    }

    static String browerName = "Chrome";

    //Non-static
    String serverName = "Testing";

    //2. Final Class/Variable/Method
    // Tránh việc ghi đè dữ liệu
    //Final Variable: Hằng số - phải gán value ngay khi khởi tạo
    //Final Methods: Tránh TH override - ko cho các class khác ghi đè
    //Final Class: Tránh TH kế thừa, vẫn có thể truy cập thông qua việc tạo mới object/instance
    //Notes: Chỉ dùng hoặc final class hoặc abstract class chứ ko dùng final abstract class >> như này là sai
    //Nếu dùng với abstract class >> ko cho khởi tạo đối tượng
    //Nếu dùng final class >> ko cho kế thừa nhưng cho khởi tạo đối tượng

    // Hằng số
    final String colorCar = "Red";

    //Final Static
    final static String REGISTER_BUTTON = "Register";

    //3. Abtract ariable/Class/Method
    //Abstract Class: Ko được phép new, Cho phép kế thừa
    // Ko có abstract Variable
    // Abstract method >> ko có phần thân, các class con kế thừa thì phải override lại

    //4. Synchronized
    //Class: Bắt buộc các đối tượng truy cập đến method này theo thứ tự -
    // ko cho phép truy cập song song - áp dụng cho việc xử ký parallel testing/sing

    //5. Enum - Hằng số/Giá trị cố định (giống static final)
    //Trong framework dùng Enum: đại diện cho tập hợp danh sách những giá trị cố định:
    // tên các loại server/..
    //Thứ trong tuần
    //Mùa trong năm
    //Loại OS
    //Viết hoa các giá trị
    //Truy cập trực tiếp từ tên class

    public static void main(String[] args) {
        System.out.println(browerName);
        browerName = "Firefox";
        System.out.println(browerName);

        Topic_04_Non_Access_Modifier topic = new Topic_04_Non_Access_Modifier();
        System.out.println(topic.serverName);

        topic.clickToElement("Button");

        //1 hàm static có thể gọi trực tiếp 1 hàm static khác bên trong Class
        sendkeyToElement("Link");

        //Final Variable
        System.out.println(topic.colorCar);


    }

    //Non-static method
    public void clickToElement(String elementName) {
        System.out.println(elementName);
    }

    //Static method
    public static void sendkeyToElement(String elementName) {
        System.out.println(elementName);
    }

    //Final method ví dụ ở class Topic_04_1
    //


}
