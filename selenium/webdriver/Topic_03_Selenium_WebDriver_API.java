package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_03_Selenium_WebDriver_API {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    //Hàm By chưa tìm element ngay >> Khai báo ở đây giống như biến toàn cục nên có thể tái sử dụng trong các TCs phía sau
    By emailBy = By.id("Email");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
    //1. Các thành phần của selenium (selenium component):
    //Cấu trúc cũ:
    //Selenium có 4 component:
    //- Selenium RC (remote control)
    //- Selenium (ver 2.0) - webdriver
    //- Selenium Grid: hoạt động để phân tán TCs ra nhiều máy khác nhau
    // Plugin
    //Cấu trúc mới:
    // Gộp Selenium RC với Selenium webdriver làm 1
    // Selenium Grid
    // Selenium IDE (plugin)

    //tương tác với browser >> thông qua biến WebDriver driver
    //tương tác với Element >> thông qua biến WebElement element
    @Test
    public void TC_01(){
        //1. Để đóng browser, trong:
        // TH có 1 tab/cửa sổ >> Đóng Browser >>> giống hàm quit()
        // TH có >=2 tab/cửa sổ trình duyệt >> chỉ close tab nó đang đứng
        driver.close();//* : ít dùng
        // Để đóng trình duyệt, không quan tâm có bnhieu tab/cửa sổ >> đóng cả Browser
        driver.quit(); //** : hay dùng. TH không note * >> không dùng
        //2. Tìm Elements
        //2.1 Có thể lưu nó thành 1 biến để sử dụng về sau (gán biến bởi dấu =) >> Dùng nhiều lần
       WebElement email = driver.findElement(By.className("email")); // lấy element
        email.sendKeys(); // dùng lại được nhiều lần
        email.click();

        //2.2 TH không dùng biến: Có thể sử dụng luôn mà không cần tạo biến
        driver.findElement(By.className("email")).click();//**
        // làm chậm quá trình chạy script
        // Dùng trong TH chỉ xuất hiện/sử dụng 1 lần

        //2.3 Tìm nhiều WebElent
        List<WebElement> names = driver.findElements(By.className("name")) ;//*
        // Cho phép lấy ra các phần tử trùng nhau

        //3. Hàm get(): dùng để mở ra 1 URL nào đó >> action
        driver.get("https://www.facebook.com/");//**

        //4. Hàm getXXX(): để lấy dữ liệu về (có dữ liệu trả về)
        //4.1 Trả về URL của trang hiện tại
        driver.getCurrentUrl();
        // Ví dụ: lấy url Facebook ở trên
        //Click vào linktext Tiếng Việt
        // Trả về url của page hiện tại: >> so sánh xem link trả về có giống nh mong muốn
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");

        //4.2 Trả về Source code HTML của Page hiện tại
        driver.getPageSource();
        // Verify tương đối >> Dùng contains()
        Assert.assertTrue(driver.getPageSource().contains("Tạo Trang dành cho người nổi tiếng, thương hiệu hoặc doanh nghiệp.\n"));//

        //4.3 Trả về title của page hiện tại
        driver.getTitle();
        // verify:
        Assert.assertEquals(driver.getTitle(),"Facebook - Đăng nhập hoặc đăng ký");

        //4.4 Trả về ID của Tab/Windows mà driver đang đứng(active) tại đó
        //>> Dùng trong bài học WebDriver API - Windows/Tab
        //-Lấy ra 1 ID
        driver.getWindowHandle();
        String windownLogin = driver.getWindowHandle();//*
        //- Lấy ra tất cả ID của tab/windows
        driver.getWindowHandles();
        Set<String> allID = driver.getWindowHandles();//*
        // Chỉ lấy ra phần tử duy nhất và không trùng nhau

        //5. Hàm manage()
        //driver.manage(): trả về interface tên là Option >> chưa có thao liền
        // nếu để như này thì hàm manage chưa có thao tác liền để xử lý
        // >> cần khai báo biến cho nó, sau đó dùng biến để xử lý/thao tác
        WebDriver.Options opt = driver.manage();
        opt.getCookies();
       // opt.timeouts() >> kiểu dữ liệu trả về là Timeout >> có 2 cách dùng:
        // dùng trực tiếp không cần tạo biến:
        opt.timeouts().implicitlyWait(30,TimeUnit.SECONDS);//**
        // Khai báo biến cho nó
        WebDriver.Timeouts timeouts = opt.timeouts();
        // hàm timeout: khoảng thời gian chờ Elememt xất hiện trong vòng x giây
        //pageLoadTimeout: thời gian chờ để page được load xong trong vòng x giây
        timeouts.pageLoadTimeout(3,TimeUnit.SECONDS);
        // Thời gian chờ script thực thi xong trong vòng x giây
        // chỗ này chưa biết viết như nào
        //
        WebDriver.Window win = opt.window();
        win.fullscreen(); // Dùng web thường không dùng full screen mà dùng maximize
        win.maximize(); //**
        win.getPosition();

        //6. Hàm navigate >> trả về kiểu dữ liệu là naviagetion >> chưa có thao tác liền để ử lý
        // Cách 1: không khai báo biến mà dùng trực tếp:
        driver.navigate().forward();

        // Cách 2: Khai báo biến
        WebDriver.Navigation nav = driver.navigate();
        nav.forward();
        nav.back();
        nav.refresh();
        // đại diện cho 3 tính năng: forward/back/refresh
         nav.to("https://www.facebook.com/");
         //nav.to() dùng tương tự như driver.get(). Nhưng na.to() hỗ trợ cho 3 tính năng trên tốt hơn driver.get()
         driver.get("https://www.facebook.com/");


        //7. Hàm switchTo(): hàm target Locator
       WebDriver.TargetLocator tar = driver.switchTo();
       tar.alert();//*
       tar.frame("");//*
       tar.window("");//*

        //8. Sử dụng hàm By đã khai báo ở phía trên để tìm element
        driver.findElement(emailBy).sendKeys("abcd");









    }
    @AfterClass
    public void afterClass() {
        // driver.quit();
    }

}
