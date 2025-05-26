package webdriver;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.File;
import java.time.Duration;
import java.util.Date;

public class Topic_35_Fluent_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;

    // Các kiểu khai báo, viết dưới dạng java genneric:
    // T: data type - kiểu dữ liệu
    // khai báo với kiểu dữ liệu nào thì khởi tạo với kiểu dữ liệu đó
    FluentWait<WebDriver> driverFluentWait;
//    FluentWait<WebElement> elementFluentWait;
//    FluentWait<String> stringFluentWait;



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
        //Khởi tạo Fluent Wait
        // Dựa vào từng yêu cầu để xách định xem truyền gì vào Fluent wait (String, driver,...)
        // Mục đích sử dụng: Fluent wait là wait theo dạng tần số hoặc chu kỳ
        // còn imlicit wait và webdriver wait là wait theo dạng polling time(thời gian lặp)
        // Cơ chế của fluent wait giống như Implicit và explcit nhưng có thể custom lại polling time
        // Khác với Implicit/Explicit:
        // Với Fluent nó không tự động ignore exception trong quá trình xử lý.
        // Còn implicit và expilicit thì mặc định ignore exception rồi, chỉ fail khi hết timeout
        // Nếu Fluent wait không ignore exception trong quá trình xử lý thì sẽ fail ngay tần số đầu tiên
        //Cách viết hàm Fluent Wait: kiểu function Generic = Function(T, R)
        // Trong đó:
        // T: Tham số của hàm apply trong function
        // R: kiểu dữ liệu trả về của hàm apply trong function
        // Ví dụ: bến dưới @After


        driverFluentWait = new FluentWait<>(driver);

        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Dynamic_Loading_01() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        findElement(By.cssSelector("div#start>button")).click();
        Assertion assertion = new Assertion();
        assertion.assertEquals(getElementString(By.cssSelector("div#finish>h4")),"Hello World!");

    }

    @Test
    public void TC_01_Dynamic_Loading_02() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        findElement(By.cssSelector("div#start>button")).click();
        Assertion assertion = new Assertion();
        //Vì ở đây nếu chỉ dùng div#finish>h4 để check text "hello world" hiển thị thì
        // chưa thể chắc chắn được nội dung có đúng = hello world không.
        // Vậy nên truyền luôn nội dung của text vào trong locator để check
        // thì sẽ đảm bảo được element vừa hiển thị và hiển thị đúng nội dung
        // => dùng xpath để lấy text
        assertion.assertTrue(isElementDisplayed(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")));

    }
    @Test
    public void TC_02_Count_Down() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        // Bài tập này element đã cho rồi, tức là element ở bên ngoài, mình ko phải tìm element nữa
        // Nhưng vẫn sử dụng các hàm findElement bên dưới được và viết hàm check bên dưới
        WebElement countDownTime = findElement(By.id("javascript_countdown_time"));
        Assertion assertion = new Assertion();
        assertion.assertTrue(isElementMatching(countDownTime));

    }


    @AfterClass
    public void afterClass() {
        // driver.quit();
    }

    // Viết hàm tìm element với timeout và polling tự set
    // Điều kiện của hàm sẽ là findElement >> điều kiện sẽ là kiểu trả về của hàm apply - R
    // Để findElement thì phải cần có driver >> driver sẽ là tham số của hàm apply - T
    public WebElement findElement(By by){
        driverFluentWait = new FluentWait<>(driver);

        // Set timeout theo kiểu tự set >> truyền long timeout vào trên hàm
        // Có thể viết liên tiếp các action/hàm với nhau như sau:
        driverFluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        // Set polling
        //driverFluentWait.pollingEvery(Duration.ofSeconds(polling));

        // Ignore exception
        //driverFluentWait.ignoring(NoSuchElementException.class);

        // Điều kiện
        // Notes: Các hàm interface ko new được nên phải override lại các hàm đó
        // Vì hàm until cũng đang trả về kiểu dữ liệu là WebElement nên có thể viết luôn kiểu return với hàm dưới như sau
        // Thêm WebElement vào trên hàm public và sau chỉ cần gọi public kia ra dùng

        return driverFluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by); // findElement ở đây chính là điều kiện cần tìm
            }
        });

    }

    // Kiểm tra 1 element hiển thị
    // isDisplayed => trả về kiểu boolean >> R = Boolean
    // Tham số là driver => T = driver

    public boolean isElementDisplayed(By by){
        driverFluentWait = new FluentWait<>(driver);
        driverFluentWait.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(by).isDisplayed(); // isDisplayed() ở đây chính là điều kiện cần tìm
            }
        });
    }

    // TH có element rồi, không cần phải tìm  mà chỉ cần check hiển thị thôi thì viết như sau
    public boolean isElementDisplayed(WebElement element){
        FluentWait<WebElement> driverFluentWait = new FluentWait<>(element);
        driverFluentWait.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                return element.isDisplayed(); // isDisplayed() ở đây chính là điều kiện cần tìm
            }
        });
    }

    // Lấy ra 1 text của element
    // getText => trả về kiểu String >> R = String
    // Tham số là driver => T = driver
    public String getElementString(By by){
        FluentWait<WebDriver> driverFluentWait = new FluentWait<>(driver);
        driverFluentWait.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver driver) {
                return driver.findElement(by).getText(); // getText() ở đây chính là điều kiện cần tìm
            }
        });
    }

    // Count down = 00s => lấy ra text của element có kết thúc = 00
    // T = webElement
    // R = getText() kết hợp với endWith() = 00 => Check hiển thị = 00 =>> isDisplayed()
    public boolean isElementMatching(WebElement element){
        FluentWait<WebElement> driverFluentWait = new FluentWait<>(element);
        driverFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100)); // cứ 1s thì kiểm tra 5 lần => 0,2s thì kiểm tra 1 lần
        // đoạn ignore này có thể không cần vì element đã được tìm thấy từ trước đó rồi
        //.ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                return element.getText().endsWith("00"); // getText().endsWith("00") ở đây chính là điều kiện cần tìm
            }
        });
    }


}
