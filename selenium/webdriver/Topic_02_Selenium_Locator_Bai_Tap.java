package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator_Bai_Tap {
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
        driver.get("https://shopee.vn/buyer/signup");

    }
    @Test
    public void TC_01_ID() {
        // tìm element có id = FirstName
       // driver.findElement(By.id("FirstName"));

    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("pDzPRp")).sendKeys("0385782504");
    }
    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("phone"));
    }
    @Test
    public void TC_04_TagName() {
        driver.findElement(By.tagName("input"));
    }
    @Test
    public void TC_05_LinkText() {
        driver.findElement(By.linkText("Trung Tâm Trợ Giúp"));
    }
    @Test
    public void TC_06_Partial_linkText() {
        driver.findElement(By.partialLinkText("Blog"));
    }
    @Test
    public void TC_07_Css() {   // handel được các case ở trên
// Css với ID
        //driver.findElement(By.cssSelector("input[id='FirstName']")); //input#FirstName/
// Css với Class
        driver.findElement(By.cssSelector("input[class='pDzPRp']")); //div.page-title/.page-title
// Css với Name
        driver.findElement(By.cssSelector("input[name='phone")); //
// Css với Tagname
        driver.findElement(By.cssSelector("input"));
// Css với link
        driver.findElement(By.cssSelector("a[href='https://help.shopee.vn/portal']")); //href="https://help.shopee.vn/portal"
// Css với Partial
      //  driver.findElement(By.cssSelector("a[href*='/shipping-returns")); //a[href*='info']
      //  driver.findElement(By.cssSelector("a[href*='info']"));
    }
    @Test
    public void TC_08_XPath() {
// XPath không cho viết tắt như Css
// XPath với ID
     //   driver.findElement(By.xpath("//input[@id='FirstName']"));
// XPath với Class
        driver.findElement(By.xpath("//input[@class='pDzPRp']")); //div.page-title/.page-title
// XPath với Name
        driver.findElement(By.xpath("//input[@name='phone']")); //
// XPath với Tagname
       driver.findElement(By.xpath("//input"));
// XPath với link
        driver.findElement(By.xpath("//a[@href='https://help.shopee.vn/portal']")); // Lấy full link
        driver.findElement(By.xpath("//span[text()='Trung Tâm Trợ Giúp']")); // Lấy theo text
// XPath với Partial
      //  driver.findElement(By.xpath("//a[contains(@href,'portal')]"));
        driver.findElement(By.xpath("//span[contains(text(),'Trung Tâm')]"));
    }
    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
