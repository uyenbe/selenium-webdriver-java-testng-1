package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.concurrent.TimeUnit;

public class Topic_04_WebElement {  //Tên class
    // 2. Khai báo biến golbal: toàn cục # local: cục bộ
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }
    // phần if/else này bắt buộc phải viết trước phần khai báo ChromeDriver.
    // Vì selenium phải chạy các setting các file thực thi trước rồi mới khởi tạo Driver

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Web_Element() {
        // Để tương tác đươợc với element >> cần phải tìm được element đó
        // >> Để tìm được element thì phải thông qua các locator (findElement()):
        //By. id/class/name/xpath/css/tagname/linktext/... >> chủ yếu dùng Css vs XPath
        driver.get("https://demo.nopcommerce.com/");
        //Khi element được dùng lại nhiều lần -> khai báo biến
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("uyenngt7@msb.com.vn");
        email.clear();
        email.isDisplayed();
        // Element chỉ dùng 1 lần -> không cần khai báo biến
        driver.findElement(By.id("pass"));
    }
    @Test
    public void TC_02_Demo() {
        //1. Khai báo biến
        WebElement element = driver.findElement(By.className("")) ;
        //2. Các hàm demo:
        //2.1 Clear(): dùng cho các ô textbox/textarea/dropdown nhưng ở dạng có thể edit được (nhập value vào được)
            //Mục đích của hàm clear(): Xoá dữ liệu đi trước khi nhập text
        element.clear();//*

        //2.2 SendKey: cũng dùng cho các ô giống như Clear(). Thường đi kèm với hàm Clear()
            // Mục đích: nhập ký tự vào ô
        element.sendKeys("");//**

        //2.3 Click(): dùng để click vào các button/checkbox/radiobutton,...
        element.click(); //**



        //2.4 findElement(): selenium cho phép tìm nhiều element
        element.findElement(By.className(""));

        //2.5 getAttribute:dùng để tìm các attribute chứa cái mà mình cần tìm, có thể không cần khai báo biến
         String searchAttribute = element.getAttribute("placeholder"); //**

         //2.6 getCssValue: dùng để verify các Css của attribute bất kỳ nào. Giá trị truyền vào là String
        // Dùng để test GUI: font-size/color/position... >> ít dùng hàm này (2.6 >> 2.9)
        element.getCssValue("background-color");//*

        //2.7 getLocation: lấy ra vị trí của element so với trang web (ở bên ngoài) >> ít dùng hàm này
        Point point = element.getLocation();
        point.x = 23;
        point.y= 24;

        //2.8 getSize(): lấy ra kích thước của element (bên trong) >> ít dùng hàm này
        Dimension dimension = element.getSize();
        dimension.getHeight();
        dimension.getWidth();
        System.out.println(dimension.height); // in ra chiều cao của element

        //2.9 getRect(): Lấy ra cả location + size của element
        Rectangle rectangle = element.getRect();
        System.out.println(rectangle.height);
        rectangle.getDimension();

        //2.10 getScreenShortAs(): chụp màn hình khi TCs bị fail. Hỗ trợ làm report
        // Còn tuỳ thuộc vào outputType. Có 3 loại OutputType:
        element.getScreenshotAs(OutputType.BASE64);//*
        element.getScreenshotAs(OutputType.BYTES);//*
        element.getScreenshotAs(OutputType.FILE);//*

        //2.11 getTagName(): lấy ra tên thẻ HTML của element.
        // Thường dùng cho các TH lấy locator bằng ID/Name/Css bởi vì lấy bằng các cách này cta không biết tagName chưa element.
        // Trong TH dùng getTagName() khi đang dùng XPath để xác định locator thì không hợp lý vì cta đã biết tagName của thẻ HTML chưa element rồi
        // Phù hợp với các TH lấy locator theo ID/Class/Name/Css >> hay dùng vì chưa biết tagName
        driver.findElement(By.id("email")).getTagName();
        driver.findElement(By.name("Email")).getTagName();
        driver.findElement(By.className("email")).getTagName();
        driver.findElement(By.cssSelector("email")).getTagName();
        element.getTagName();
        
        // -> Lấy ra tagName rồi truyền vào cho locator khác
        String emailTextboxTagName = driver.findElement(By.id("email")).getTagName();
        driver.findElement(By.xpath("//"+ emailTextboxTagName + "[@id='email']"));

        //2.12 getText(): dùng để lấy text từ các error message/ success message/ label/ hearder...
        element.getText();//**

        //Vậy khi nào thì dùng getText() khi nào dùng getAttribute()
        //Khi cái value mình cần lấy nó nằm bên ngoài (không gắn với attribute nào) >> dùng getText()
        // Ví dụ: <p class="required">* Required Fields</p> =>> Text "* Required Fields" không nằm trong attribute nào cả (nằm bên ngoài) >> dùng getText()
        //Khi cái value cần lấy nằm bên trong (gắn với attribute cụ thể) >> dùng getAttribute()
        //<input id="search" type="search" name="q" value="" class="input-text required-entry" maxlength="128"
        // placeholder="Search entire store here..." autocomplete="off">
        // => Text "Search entire store here..." đang nằm trong attribute placeholder >> dùng getAttribute()

        //2.13 isDisplayed(): Dùng để verify xem 1 element có hiển thị hay không
        // Phạm vi: áp dụng cho tất cả các element
        // Khi dùng hàm này cần kết hợp với hàm assertTrue/assertFalse để trả về kết quả là có hay không
        Assertion asserts = new Assertion(); // vì assert mới update từ assert >> assertion
                                            // nên nếu muốn dùng hàm assert thì phải khai báo biến
        asserts.assertTrue(element.isDisplayed());
        asserts.assertFalse(element.isDisplayed());

        //2.14 isEnabled(): Dùng để verify xem 1 element có thao tác được hay không. Nếu thao tác được thì enable, còn không thì disable
        // Phạm vi: áp dụng cho tất cả các element
        // Khi dùng hàm này cần kết hợp với hàm assertTrue/assertFalse để trả về kết quả là có hay không
        asserts.assertTrue(element.isEnabled());
        asserts.assertFalse(element.isEnabled());

        //2.15 isSelected: Dùng để verify xem 1 element đã được chọn hay chưa
        //Phạm vi: Áp dụng cho các element: checkbox/radio
        asserts.assertTrue(element.isSelected());
        asserts.assertFalse(element.isSelected());

        //2.16 element.submit(): Dùng cho các element nằm trong thẻ form
        //Tương ứng với hành vi của End User là submit (nhấn Enter)
        //Thường thì ít dùng, và người ta hay dùng hàm get.Click() vì độ chính xác cao

        element.submit();

    }
    @Test
    public void TC_03_() {

    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
