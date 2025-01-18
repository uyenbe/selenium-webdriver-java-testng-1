package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_14_Action_III {
    WebDriver driver;

    // Khai báo thư viện Action
    Actions actions; // dùng thư viện Actions chứ không dùng Action

    // Khởi tạo assert chung cho các TCs bên dưới để verify
    Assertion assertion = new Assertion();

    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    Keys keys;

    // Define 1 biến resultText dùng chung cho cả 3 TCs bên dưới
    By resultText = By.xpath("//p[@id='result']");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new ChromeDriver();
        // Khởi tạo Action
        actions = new Actions(driver); // tham số của action là webdriver

        // Check hệ điều hành để check phím xem dùng phím Control hay Command
        // Áp dụng trong những case action dùng đến bàn phím

        if (osName.contains("Windows")) {
            keys = Keys.CONTROL;
        }else {
            keys = Keys.COMMAND;
        }

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       // driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Drag_And_Drop_HTML4() {

        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement smallCircle = driver.findElement(By.id("draggable"));
        WebElement bigCircle = driver.findElement(By.id("droptarget"));
        actions.dragAndDrop(smallCircle, bigCircle).perform();
        sleepInSeconds(3);

        //Verify: text và background
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.id("droptarget")).getText(), "You did great!");

        assertion.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toUpperCase(),
                "#03A9F4");


    }
    @Test
    public void TC_02_Drag_And_Drop_HTML5() {
        // Khi nào cần thì quay lại bài này học lại
        //Notes: Khi chạy testcase có liên quan đến Action thì ko được dùng/di chuyển chuột, bàn phím vì như thế sẽ làm fail TCs
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        WebElement smallCircle = driver.findElement(By.id("column-a"));
        WebElement bigCircle = driver.findElement(By.id("column-b"));

        actions.dragAndDrop(smallCircle, bigCircle).perform();


    }
    @Test
    public void TC_03_ScrollIntoView(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        sleepInSeconds(1);

        actions.scrollToElement(driver.findElement(By.xpath("//legend[text()='Your profile']"))).perform();

        // Verify
//        Assertion assertion = new Assertion();
//        assertion.assertEquals(driver.findElement(By.xpath("//legend[text()='Your profile']")).getText(),"Hello Automation Guys!");

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
