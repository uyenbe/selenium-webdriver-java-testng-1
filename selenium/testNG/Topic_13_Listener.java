package testNG;

import Listener.ScreenShotListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
//@Listeners(ScreenShotListener.class) //TH khai báo Listener bên xml thì dòng này bỏ đi
public class Topic_13_Listener {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    By emailTextbox = By.xpath("//*[@id='email']");
    By passwordTextbox = By.xpath("//*[@id='pass']");
    By loginButton = By.xpath("//*[@id='send2']");
    String username = "admin";
    String password = "111111";
    String domainURL;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // Khởi tạo browser

    }

    @Test(timeOut = 10000)
    public void TC_01_loginMultipleBrowser()  {
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
        driver.findElement(passwordTextbox).sendKeys("111111");
        driver.findElement(loginButton).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));

    }

// Dependence TCs: TCs này phụ thuộc vaào TCs kia
    // Những TCs độc lập là những TC ko phụ thuộc, test parallel hợp hơn
    // Những TCs luồng, UI là những TC phụ thuộc
    @AfterClass
    public void afterClass() {
        driver.quit();
    }


    public WebDriver getDriver() {
        return driver;
    }
}

