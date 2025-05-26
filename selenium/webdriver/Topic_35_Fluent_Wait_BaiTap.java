package webdriver;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;

public class Topic_35_Fluent_Wait_BaiTap {
    WebDriver driver;
    FluentWait<WebDriver> driverFluentWait;
    FluentWait<WebElement> elementFluentWait;



    @BeforeClass
    public void beforeClass() {

        driver = new ChromeDriver();

        driverFluentWait = new FluentWait<>(driver);


        driver.manage().window().maximize();
    }


    @Test
    public void TC_01_Dynamic_Loading_01_getText() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // Yêu cầu:
        //1. Click vào Start button >> có thể tự lấy locator bình thường hoặc viết hàm
        //2. Dùng Fluent wait để check text Hello World hiển thị hay chưa
        // Tại bước 2, để check được text hiển thị hay chưa cần có 2 cách:
        //- getText() sau khi click sau đó so sánh >> R: String
        //- check hiển thị >> return (R): isDisplayed()
        // T: driver
        // Viết hàm bên dưới sau đó gọi lên đây để dùng
        driver.findElement(By.cssSelector("div#start > button")).click();
        Assertion assertion = new Assertion();
        assertion.assertEquals(getElementString(By.xpath("//div[@id='finish']//h4")),"Hello World!");

    }

    @Test
    public void TC_01_Dynamic_Loading_02_isDisplayed() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // Yêu cầu:
        //1. Click vào Start button
        //2. Dùng Fluent wait để check text Hello World hiển thị hay chưa
        // Tại bước 2, để check được text hiển thị hay chưa cần có 2 cách:
        //- getText() sau khi click sau đó so sánh >> R: String
        //- check hiển thị >> return (R): isDisplayed()
        // T: driver
        // Viết hàm bên dưới sau đó gọi lên đây để dùng

        driver.findElement(By.cssSelector("div#start > button")).click();
        Assertion assertion = new Assertion();
        assertion.assertTrue(isDisplayedElement(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")));

    }
    @Test
    public void TC_02_Count_Down() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        //Yêu cầu:
        // Đợi cho đến khi countdown phần giây = 00s được xuất hiện
        //Element trong TH này đã được xác định rồi - element nằm ở bên ngoài và ko cần xác định nữa
        //Ở đây check kết thúc phần giây = 00 hiển thị =>> R = isDisplayed()
        //T = webElement
        // Viết hàm bên dưới và gọi ở trên này
        WebElement countdownTime = findElement(By.id("javascript_countdown_time"));
        Assertion assertion = new Assertion();
        assertion.assertTrue(isElementMatching(countdownTime));


    }


    @AfterClass
    public void afterClass() {
        // driver.quit();
    }

    //findElement
    public  WebElement findElement(By by){
        driverFluentWait.withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(50)).
                ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, WebElement>() {

            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
    }

    public boolean isDisplayedElement(By by) {
        driverFluentWait.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(by).isDisplayed();//isDisplayed() là điều kiện trả về
            }
        });
    }

    public String getElementString(By by){
        driverFluentWait.withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(NoSuchElementException.class);

       return driverFluentWait.until(new Function<WebDriver, String>() {

            @Override
            public String apply(WebDriver driver) {
                return driver.findElement(by).getText();
            }
        });
    }

    //Countdown

    public  boolean isElementMatching(WebElement element){

        elementFluentWait = new FluentWait<>(element);

        elementFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(50));

       return elementFluentWait.until(new Function<WebElement, Boolean>() {

            @Override
            public Boolean apply(WebElement element) {
                return element.getText().endsWith("00");
            }
        });


    }








}
