package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Bai_Tap_XPath_3 {
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
        driver.get("https://automationfc.github.io/basic-form/");

    }
    @Test
    public void TC_07_Tech07() {
        //hàm Inside parent và Position()
        driver.findElement(By.xpath("//li[2]"));
        //hàm Outside parent >> có demo bên Topic_02
    }
    @Test
    public void TC_07_Tech07_1() {
        //hàm Last()
        driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee'][last()]"));

        //
    }
    @Test
    public void TC_08_Tech08() {
        //hàm contains(.,'..') và contains(string(),'..')
        driver.findElement(By.xpath("//h5[contains(.,'Michael Jackson')]"));
        driver.findElement(By.xpath("//h5[contains(string(),'Michael Jackson')]"));
        driver.findElement(By.xpath("//h5[contains(.,'(Ignore Me)')]"));
    }
    @Test
    public void TC_05_Tech05_4() {
        //hàm concat()
        driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", ',\"What's happened?\")]"));

    }

    @Test
    public void TC_06_Tech06_1() {
        // hàm AND vs OR
        driver.findElement(By.xpath("//input[@type='email' and @name='user_email']"));
        driver.findElement(By.xpath("//input[@type='email' or @name='user_email']"));

    }
    @Test
    public void TC_06_Tech06_2() {
        // hàm NOT
        driver.findElement(By.xpath("//input[@type='email' and @name='user_email']"));
        driver.findElement(By.xpath("//input[@type='email' or @name='user_email']"));

    }


    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
