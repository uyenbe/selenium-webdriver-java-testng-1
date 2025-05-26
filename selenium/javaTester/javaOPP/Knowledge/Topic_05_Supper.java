package javaTester.javaOPP.Knowledge;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Topic_05_Supper extends BaseOOP {
    //supper: truy cập đến Class cha trực tiếp: - demo BaseOPP
    //1. Varibale: Gọi trực tiếp đến thuộc tính của Class cha
    // Nếu ko dùng supper thì sẽ lấy biến của lớp hiện tại

    //Global
    private WebDriver driver;
    private long shortTimeout = 15;
    private long longTimeout = 45;
    // Method
    public void setImplicitWait(){
        long shortTimeout = 10;
        driver.manage().timeouts().implicitlyWait(super.longTimeout, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(this.shortTimeout, TimeUnit.SECONDS);

        //Case ko truy cập được biến private driver vì nó đang set access modifier = private
//        super.driver.manage().timeouts().implicitlyWait(super.longTimeout, TimeUnit.SECONDS);
    }
    //2. Method: class con và class cha có cùng 1 hàm thì dùng super đến phân biệt
    //Thực tế thì ít dùng từ khoá super, toàn gọi trực tiếp tên method
    public void clickToElement(){
        //ko dùng từ khoá super nó sẽ gọi đến method của class hiện tại - class kế thừa
        setImplicitWait();

        // dùng super thì sẽ gọi đến method của class cha - class được kế thừa hoặc class cha của cha (Đa kế thừa)
        super.setImplicitWait();
    }

    //3. Contructor
    //super: sẽ luôn gọi qua contructor của Class cha
    //
    //Trong TH Class con ko dùng supper >>###########
    // vẫn tự động gọi qua constructor của class cha trước  rồi mới gọi đến constructor của class con
    //Trong TH Class cha có nhiều constructor >> dùng super để tuỳ chọn xem gọi qua constructor nào
    //TH class con ko dùng super để gọi qua class cha thì:
    // khi chạy ở class con thì nó sẽ chạy vào constructor mặc định KHÔNG THAM SỐ của Class cha
    //TH Class cha ko có/ko define constructor >> Class con không cần dùng super
    //Chỉ được gọi 1 lần super
    public Topic_05_Supper(){
        //Luôn nằm ở steps đầu tiên
        super("Chrome");
        System.out.println("Constructor tại class con");
    }

    //4. Constructor
    //Tên của Constructor phải trùng với tên Class, không có kiểu dữ liệu trả về kể cả void
    //Nếu trong class ko có hàm constructor thì khi chạy, Java sẽ tự cung cấp một hàm constructor default
    //Mục đích của constructor là để khởi tạo giá trị ban đầu cho các thuộc tính của đối tượng.
    //Không được kế thừa bởi các subClasses, chỉ được gọi

    //Không dùng với các keyword: abstract/final/static/synchronize
    //Dùng được với các keyword Access Modifier: public, protected, private, default
    //Để hạn chế việc new instance thì có thể dùng private
    //TH 1 class con kế thừa class cha mà class cha có construcutor thì:
    //bắt buộc Class con cũng phải có hàm khởi tạo và gọi qua 1 constructor nào đó của class cha
    //Có thể có hoặc ko có tham số
    //luôn được gọi vào đầu tiên khi new object/instance
    // this và super keyword phải nằm ở dòng đầu tiên trong constructor
    // (super gọi qua constructor thì k cần dùng super. - cái super.biến hoặc super.hàm)




    public static void main(String[] args) {
        Topic_05_Supper topic = new Topic_05_Supper();

    }
}
