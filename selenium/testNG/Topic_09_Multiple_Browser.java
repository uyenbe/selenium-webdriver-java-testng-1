package testNG;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Topic_09_Multiple_Browser {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    By emailTextbox = By.xpath("//*[@id='email']");
    By passwordTextbox = By.xpath("//*[@id='pass']");
    By loginButton = By.xpath("//*[@id='send2']");
    String username = "admin";
    String password = "111111";
    String domainURL;

    @BeforeClass
    @Parameters({"server","browser"})
    // Lấy giá trị-value của parameter từ bên file xml, lấy ra 1 value thì khai báo theo kiểu ("server")
    // TH lấy ra nhiều value thì truyền kiểu mảng - {"sever","browser"} và sẽ tạo ra 2 biến trong BeforeClass
    // Vì đây là kiểu dữ liệu String nên khi ánh xạ vào hàm sẽ là kiểu String
    // Notes:
    // - Parameters có thể dùng cho beforeClass, Test, AfterClass
    //- Parameters không dùng cho beforeSuit, afterSuit
    // Và để chạy được thì phải qua file xml để Run
    //TH không set giá trị của parameter bên file xml thì dùng @Optional để khai báo mặc định một môi trường/browser
    //Parallel: Không nên chạy trên 1 máy, nên chạy trên nhiều máy vì dễ bị xung đột nhiều tính nắng: download/upload/delete...
    //Cloud testing là hay nhất để chạy parallel
    // Cái phù hợp nhất để chạy Parralel: Selenium Grid: Local/Docker/Cloud...

    public void beforeClass(String serverName, @Optional("Chrome") String browserName) {
        // Xử lý dữ liệu lấy từ Parameter
        if (serverName.equalsIgnoreCase("Dev")) {
            domainURL = "http://dev.techpanda.org";
        }else if (serverName.equalsIgnoreCase("Testing")) {
            domainURL = "http://testing.techpanda.org";
        }else if (serverName.equalsIgnoreCase("Staging")) {
            domainURL = "http://staging.techpanda.org";
        }else if (serverName.equalsIgnoreCase("Production")) {
            domainURL = "http://live.techpanda.org";
        }else {
            throw new RuntimeException("Server name is not valid " );
        }

        //Notes:
        // Đối với môi trường thì mỗi lần chạy thì chỉ chạy được 1 môi trường, hoặc là Dev hoặc là Testing
        // Nhưng 1 môi trường thì có thể chạy được nhiều browser trong cùng 1 lần chạy

        // Switch - case
        switch (browserName){
            case "Chrome":
                driver = new ChromeDriver();
                break;
                case "Firefox":
                    driver = new FirefoxDriver();
                    break;
                    case "Edge":
                        driver = new EdgeDriver();
                        break;
                        default:
                            throw new RuntimeException("Unsupported browser: " + browserName);
        }
        // Khởi tạo browser

    }
        // Parameter nếu chỉ dùng cho 1 test thì khai báo luôn trong thẻ test
        // Còn dùng cho nhiều test thì khai báo ra ngoài chung cho các thẻ test
    @Test()
    public void TC_01_loginMultipleBrowser()  {
        driver.get(domainURL + "/index.php/customer/account/login/");

        driver.findElement(emailTextbox).sendKeys(username);
        driver.findElement(passwordTextbox).sendKeys(password);
        driver.findElement(loginButton).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}

