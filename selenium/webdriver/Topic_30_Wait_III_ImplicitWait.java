package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;

public class Topic_30_Wait_III_ImplicitWait {
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
        driver.manage().window().maximize();
        // Nên dùng hàm implicitWait ở BeforClass vì chỉ nên gọi 1 lần hàm Wait
        // và thời gian setup trong hàm Wait nên là thời gian ước lượng lâu nhất để element xuất hiện
        // vì nếu tìm thấy ngay element thì tự không thoát khỏi hàm Wait rồi
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


    }
    // Cơ chế wait của Selemium:
    // Implicit Wait và Explici Wait. Trong đó:
    // Implicit Wait: là wait ngầm định vì nó chỉ wait cho việc tìm element
    // Implicit Wait không sửa được pool
    // Explicit Wait bao gồm:
    // - WebDriver Wait
    // - Fluent Wait
    @Test
    public void TC_00_Dont_Setup() {
        //Bài tập TC02 này chia làm 4 case để check time wait: không set, nhỏ hơn, bằng và lớn hơn
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();


        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_01_Less_Than() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }
    @Test
    public void TC_02_Equals() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }
    @Test
    public void TC_03_Greater_Than() {
        // Trong TH chạy 2 TCs 02 và 03 mà tại TCs 03 không gọi lại URL thì sẽ bị lỗi vì:
        // tìm được element trên DOM nhưng không click vào được bởi:
        // hàm Wait chỉ tác động đến hàm findElement thôi còn hàm click thì không
        // >> phải gọi lại url
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_04_BaiTap() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.cssSelector("div#start>button")).click();
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");



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
