package javaTester;

public class Topic_08_Parameter {
    static String fullNameGlobal = "Nguyen Uyen";
    public static void main(String[] args) {
    // Đối số: Lấy hàm ra dùng.
    // Nhưng mọi người thường gọi chung là tham số
    setFullname("Uyen Xinh");
        System.out.println(getFullName());

    }
    public static void setFullname(String fullName){ //Khởi tạo hàm >> Tham số
        fullNameGlobal = fullName;
    }

    public static String getFullName(){
        return fullNameGlobal;
    }
}
