package javaTester.javaBasic;

public class Topic_01_Variables {
    static int studentNumber;
    static  boolean status;
    static int studentPrice;
    static final String Browser_Name = "Chrome";//constant


    // Hàm khởi tạo
    public Topic_01_Variables(){
        String member; // biến trong hàm này cũng được gọi là biến cục bộ
    }


    static public void main(String[] args) {
        int studentPrice = 5;
        System.out.println(studentNumber);
        System.out.println(status);
        System.out.println(Topic_01_Variables.Browser_Name); // Biến static
        System.out.println(studentPrice);
        Topic_01_Variables topic = new Topic_01_Variables();
        System.out.println(topic.studentName);// Biến global
    }
    String studentName = "UyenNT7";

    // Hàm Getter - lấy dữ liệu: getText/getTitle/getAttribute/getSize...
    // return dùng trong hàm trả về dữ liệu
    public String getStudent (){
        return this.studentName;

    }
    // Hàm Setter - gán dữ liệu
    //hàm void dùng cho các action: click/sendkey/clear/select/back/...
    public void setStudent (String student){
        this.studentName = student;
    }

    //I. Cách đặt tên biến:
    //- Không có khoảng trắng và ký tự đặc biệt
    //- Không được bắt đầu bằng số
    //- Không trùng nhau và không được trùng các keyword trong java

    //II. Kiểu dữ liệu trong java có 2 kiểu:
    /*2.1 Kiểu dữ liệu nguyên thuỷ: Primitive type
     - Có 8 kiểu dữ liệu:
     1. Kiểu số nguyên: byte - short - int - long >> Số không có phần thập phân
     2. Kiểu số thực: có phần thập phân
     float: fNumber = 9.99f (phải có chữ f/F ở phía sau để phân biệt với double
     double : dNumber = 9.99d
     2. Kiểu ký tự: char
     3. Kiểu logic: boolean
    */

    /* 2.2 Kiểu dữ liệu tham chiếu/ không nguyên thuỷ: Reference Type
    1. Class
    2. Object
    3. Array
    4. String
    5. Collection
    * */

    /* III. Cách khai báo biến để lưu trữ 1 kiểu dữ liệu:
    1. Phạm vi truy cập: public, private, protected, default
    2. Kiểu dữ liệu
    3. Tên biến
    4. Giá trị của biến: gán với phép =
    Nếu như không gán giá trị >> lấy dữ liệu mặc định
    VD: public int studentNumber = 200;
    * */

    //IV. Các loại biến:
    //1. Biến cục bộ - local:
    //- Chỉ sử dụng trong phạm vi của TCs/của hàm. Khai báo ở đâu thì được gọi ở đó
    //- Biến local không có giá trị mặc định nên phải set giá trị mặc định chi nó
    //- Nếu khai báo trong hàm thì không cần dùng keyword (key sau từ public: static/String/number...)
    // nhưng khai báo bên ngoài hàm thì phải dùng keyword trước kiểu dữ liệu để khai báo: static in number = 2
    // VÀ khi khai báo biến này phải setup gia trị cho nó thì mới dùng được
    // TH biến chứa key khác key của hàm mà muốn truy cập thì phải thông qua class: Ví dụ ở topic 02

    //2. Biến toàn cục - golbal/ instance/field của class
    // - Là biến ko được khai báo với từ khoá static
    // - Không được khai báo trong method/block code hay hàm khởi tạo
    // - Biến instance được dùng khi đối tượng của class được khởi tạo và bị mất đi khi đối tượng của class bị mất đi
    // Tức là biến này dùng cho đối tượng của class
    //- Biến instance được gán giá trị mặc định, cụ thể:
    // - Kiểu số: mặc định = 0
    // - Kiểu boolean: mặc định = false
    // - Kiểu tham chiếu: mặc định = null
    // - Biến instance có thể được truy cập bởi các method trong cùng class (trừ static method) và truy cập từ class bên ngoài tuỳ theo access modifier

    //3. Biến static - biến tĩnh/biến class - thuộc phạm vi class
    // - khai báo biến kèm từ khoá "static"
    // - Được tạo khi ctrinh bắt đầu chạy và tự huỷ khi chương trình kết thúc/tắt
    // - Biến static được truy cập giống như biến instance nhưng có thể truy cập thông qua class
    // thay vì thông qua object với cú pháp: ten_class.ten_bien

    //4. Biến Hằng số - final - constant(const)
    // Khi khai báo là biến hằng số >> không được thay đổi giá trị trong suốt chương trình
    // Giúp chương trình an toàn hơn
    // Sẽ cảnh báo nếu user cố tình thay đổi giá trị của biến final sau này (đảm bảo tính toàn vẹn của giá trị)



}
