package webdriver;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_19_Handle_Random_Popup_BaiTap {
    WebDriver driver;
    Actions actions;

    WebDriverWait explicitWait;
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
        actions = new Actions(driver);

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_() {

        driver.get("https://vnk.edu.vn/");
        sleepInSeconds(5);

        By popup = By.cssSelector("div.pum-container");

        if (driver.findElements(popup).size() > 0 &&driver.findElements(popup).get(0).isDisplayed()){
            System.out.printf("To do IF");
            driver.findElement(By.cssSelector("button.pum-close")).click();

        }
        System.out.printf("Ignore IF");
        driver.findElement(By.xpath("//li[contains(@class,'mega-menu-item')]//a[text()='Liên hệ']")).click();




    }
@Test
    public void TC_02_() {
        driver.get("https://www.javacodegeeks.com/");
        sleepInSeconds(20);

        By popup = By.xpath("//div[text()='Want to take your Java skills to the next level?']");
        if (driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()){
            System.out.printf("To do IF");
            driver.findElement(By.xpath("//div[@class='lepopup-element-html-content']//a[text()='×']")).click();
        }




}
@Test
public void TC_03(){
        driver.get("https://dehieu.vn/");
        sleepInSeconds(2);

        By popup = By.cssSelector("div.modal-content");
        if(driver.findElements(popup).size() > 0
                && driver.findElements(popup).get(0).isDisplayed()){
            System.out.printf("To do IF");
            driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
        }
        System.out.printf("Ignore IF");
        driver.findElement(By.xpath("//a[text()=' Tất cả khóa học']")).click();

        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.getCurrentUrl(),"https://dehieu.vn/courses");

}



    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
