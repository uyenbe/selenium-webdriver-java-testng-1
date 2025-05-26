package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.File;
import java.time.Duration;
import java.util.Date;

public class Topic_36_Handle_Page_Ready {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    // Lấy ra đường dẫn lưu folder file
    String uploadFolder = projectPath + File.separator + "uploadFiles" + File.separator;


    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new ChromeDriver();
        // set timeout = 10s, mặc định pooling time = 0,5
        // explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // set thời gian timeout = 10s và sau 0,3s thì tìm lại 1 lần (pooling time = 0,3s)
        //Dùng cách nào cũng được
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();


    }

    // Để check 1 trang web đã ready hay chưa có 2 cách để check:
    // cách 1. Dùng hàm chờ cho icon loading biến mất/page đã hoàn thành - tuỳ vào trang web/hàm vì không phải trang web nào dùng hàm check nó cũng trả ra đúng kết quả
    //Cách 2. wait cho icon loading biến mất/hoàn thành =>> Cách này dùng được với mọi page
    // Nhưng cần phải viết hàm nhiều

    @Test
    public void TC_01_Handle_Page_Ready() {
        driver.get("https://admin-demo.nopcommerce.com");

        //Login page
        By emailText = By.cssSelector("input#Email");
        if (emailText != null){
            driver.findElement(emailText).clear();
        }
        driver.findElement(emailText).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");

        driver.findElement(By.cssSelector("button.login-button")).click();

        //Cách 1. Dùng wait để check icon loading biến mất
        // Khi icon loading biến mất thì page sẽ được fill đủ data
        Assertion assertion = new Assertion();
        assertion.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy"))));

        // Click logOut
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        // Verify form Login hiển thị
        assertion.assertTrue(driver.findElement(By.xpath("")).isDisplayed());

        // Cách 2. Dùng hàm để check
        // Nhưng thực tế thì dùng Wait cho dễ









    }

    @Test
    public void TC_02_Element_Not_Found_Only_Implicit_() {
    }
    @Test
    public void TC_03_Element_Not_Found_Only_Explicit_By() {


    }

    @Test
    public void TC_03_Element_Not_Found_Only_Explicit_02_WebElement() {


    }

    @Test
    public void TC_04_Element_Not_Found_Only_Explicit_Mix_Implicit() {


    }

    @Test
    public void TC_05_Demo() {

    }

    public String getDateTimeNow()
    {
        Date date = new Date();
        return date.toString();
    }


    @AfterClass
    public void afterClass() {
        // driver.quit();
    }


//    public void sleepInSeconds(long timeInSecond) {
//        try {
//            Thread.sleep(timeInSecond * 1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
}
