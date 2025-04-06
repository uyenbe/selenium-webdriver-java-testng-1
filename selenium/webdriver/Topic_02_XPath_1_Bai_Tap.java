package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_XPath_1_Bai_Tap {
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

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/desktops");

    }
    @Test
    public void TC_01_Tech01() {
        //Tìm theo cú pháp của XPath
    driver.findElement(By.xpath("//div[@class='footer-block information']"));
    }
    @Test
    public void TC_02_ParentNode() {
    driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Desktops ']"));
    }
    @Test
    public void TC_03_Tech03_1() {
        //Lấy tuyệt đối
    driver.findElement(By.xpath("//strong[text()='Customer service']"));
    }
    @Test
    public void TC_04_Tech04() {
        //Lấy tương đối
        driver.findElement(By.xpath("//strong[contains(text(),'account')]"));
    }
    @Test
    public void TC_04_Tech04_1() {
        // start with()
    driver.findElement(By.xpath("//a[starts-with(text(),'Privacy')]"));
    }
    @Test
    public void TC_05_Tech05() {

    }
    @Test
    public void TC_06_Tech06() {

    }
    @Test
    public void TC_07_Tech07() {

    }


    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
