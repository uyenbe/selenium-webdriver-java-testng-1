package javaTester.javaOPP.Knowledge;

public class Topic_03_Variable_Property_Method {

    //I. Thuộc tính-property: Đi với object
    // Có các hàm getter/setter kết hợp
//=================================================
    //II. Các thành phần của biến:
    //1.Access Modifier: có thể ko cần khai báo
    //2. Data type
    //3. Varibale name
    //4. Varriable value

    private String studentName = "UyenNT7"; // biến toàn cục - global variable

    // Access Modifier = default
    int countNumber = 30;

    // Static variable: Dùng trong hàm static và có thể gọi trực tiếp trong hàm static
    // Có thể gán lại value của biến trong hàm static
    // public static: Dùng và gán lại được cho tất cả các hàm static ở trong và ngoài class
    public static String studentAddress = "Ha Noi";

    // private static: Dùng và gán lại cho tất cả các instance/object trong class
    private static String stdPhone = "0987654321";

    // Final variable: ko cho phép gán lại/Override lại
    // Cố định dữ liệu, ko được phép thay đổi trong quá trình chạy
    // Ko gọi trực tiếp trong hàm static được mà phải new biến này lên rồi thông qua biến new đó gọi trong hàm static
    final String country = "Viet Nam";

    // Static final varibale: là hằng số (constant)
    // Ko được ghi đè
    // Có thể truy cập rộng rãi cho tất cả các instance/thread
    static final float PI_NUMBER = 3.13234f;

    //Varibale có các loại:
    //1. Instance Varibale (Global):
    //- Được khai báo trong phạm vi class nhưng ở ngoài method/constructor/block code
    //- Được tạo ra khi 1 object được khởi tạo với từ khoá new
    //- Mất đi khi object bị huỷ
    //- Sử dụng Access Modifier
    //- Có default value:
    //+int: 0
    //+String: null
    //+Boolean: false

    //2. Local variable
    //- Được khai báo trong Method/constructor hoặc block code
    //- Được tạo khi method/constructor/block được chạy và sẽ bị huỷ khi thoát khỏi method/constructor/block
    //- Không sử dụng với Access modifier
    // Chỉ sử dụng trong phạm vi method/constructor/block được khai báo
    // Không có giá trị mặc định >> phải khởi tạo value cho biến

    //3. Static varibales
    //- ĐƯợc khởi tạo với keyword static trong một Class, ko khai báo trong method/constructor/block code
    //- Thuộc Class quản lý chứ ko quản lý bởi object/instance
    //- Được sử dụng như một hằng số với modifier: public/private/final/static - ko thay đổi giá tị trong quá trình chạy code
    //Bao gồm:
    //+ public static final: Cho tất cả các class khác truy cập
    //+ private static final: Chỉ dùng cho các instance của Class
    //- Default value tương tự Instance varibale
    //- Có thể truy cập từ TênClass.TênVaribale
    //- Khi khai báo public staic final thì nên viết hoa và phân các bởi gạch dưới

//============================================================
    //III. Method - Hàm
    //1. Cách khai báo:
    //- Access Modifier: public/private/protected...
    //- Return Type: int/String/void...
    //+ Các hàm Action: void (click/sendkey/close...)
    //+Các hàm Get: Trả về kiểu dữ liệu cụ thể: int/By/String/... (getStudent/...)
    //- Method name: Đặt tên theo Camel case - viết thường chữ đầu
    //- Parameter List: Tham số truyền vào của hàm, ko bắt buộc
    //- Method body: Xử lý code - phần thân
    //- Exception List: Exception trả về của hàm (Opinion)

    //2. Hàm Static và Non-Static
    //- Static Method Không cần object để truy cập mà truy cập trực tiếp từ Class
    //- Trong cùng 1 Class:
    //+ Hàm Static có thể gọi qua hàm Static khác, ko thể gọi qua hàm non-static
    //+ Hàm Static có thể gọi các thuộc tính Static, ko thể gọi các thuộc tính non-static
    //+ Hàm static cần tạo object để truy cập vào các hàm/thuộc tính non-static

    //3. Abstract Method (demo tại OPP/Computer.java và OPP/IComputer.interface và OPP/Laptop.class)
    //- Abstract Method Chỉ có trong Abstract Class và Interface
    //- Abstract Method không có phần thân hàm,
    // nó giống như một hàm khung để cho các Class con kế thừa nó phải override lại(impliment lại)
    //- Trong Abstract Method chứa cả non-abstract method (normal method)
    // Lưu ý: Tất cả các hàm trong Interface đều là Hàm Abtract, không chứa hàm normal trong interface

    //4. Overloading - Chồng hàm Ví dụ: thư viện Assert
    //- Chồng hàm là phạm vi 1 Class có thể định nghĩa nhiều method cùng tên nhưng:
    //+ Cùng số lượng tham số nhưng phải khác kiểu dữ liệu
    //+ Khác sô lượng tham số nhưng không cần khác kiểu dữ liệu

    // Hàm non static
    public void display(){
        String studentName = "UyenNT7"; // local variable
    }

    // Hàm non-static
    void showCarName(){
        System.out.println("Honda");
    }

    static void showCarColor(){
        System.out.println("Black");
    }

    // Constructor
    public Topic_03_Variable_Property_Method(){
        String studentName = "UyenNT7"; // local variable

        if (this.studentName.equals("UyenNT7")){
            System.out.println(studentName);
        }
    }
    // Hàm static
    public static void main(String[] args) {
        //Local variable: bắt buộc phải khởi tạo giá trị thì mới dùng được
        String studentName = "UyenNT7";

        //block code
        if (studentName.equals("UyenNT7")){

            //Local variable - Biến cục bộ trong block code
            int number = 100;
        }
        // Static variable
        studentAddress = "Da Nang";

        //Static Method: Gọi vào trong 1 hàm static khác được
        showCarColor();

        //Non-Static method: Không gọi trực tiếp được mà phải thông qua instance/object
        Topic_03_Variable_Property_Method obj = new Topic_03_Variable_Property_Method();
        obj.showCarName();

    }

}
