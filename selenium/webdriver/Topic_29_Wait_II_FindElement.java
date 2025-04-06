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

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_29_Wait_II_FindElement {
    WebDriver driver;
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
        driver.manage().window().maximize();
       // explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
      //  driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");


    }
    @Test
    public void TC_01_FindElement() {
        // Implicit Wait áp dụng trực tiếp và duy nhất cho việc tìm element (findElements và findElement số ít hoặc số nhiều)
        // Implicit Wait là Wait ngầm định, vì nó không wait cho bất kỳ điều kiện hay trạng thái nào của element mà chỉ wait cho việc tìm kếm element
        // Và hàm findElement chỉ nhận giá trị ImplicitWait của lệnh Wait ngay trên nó thôi.
        // Và nếu bên dưới hàm Wait đó có nhiều TestCase >> các TCs sau sẽ nhận cùng 1 giá trị Wait (cả frame work vì nó ăn theo driver)
        // Ví dụ dưới đây thì giá trị Wait nhận là 5s vì lệnh Wait gần nhất có giá trị = 5s
        //Notes: ví dụ dưới tổng time setup Wait là 5s, nhưng tổng time chạy TCs lại >5s là vì:
        // + ngoài bước findElement ra TC còn chạy nhiều bước khác >> Tổng time chạy TCs >5s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //-- Set up giá trị Wait đầu tiên
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Gán lại giá trị Wait = 8
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Gán lại giá trị Wait = 10
        // TH set ImplicitWait = 0 hoặc không setup implicitWait(không có dòng driver.manage().timeout().implicitlyWait(...)
        // >> thì chạy Testcase sẽ báo fail luôn
        //
        // Demo 3 TH
        //TH1. Nếu tìm thấy duy nhất 1 element
        // Out put: Trả về đúng element đó
        // Không cần chờ hết time của implicit
      //  driver.findElement(By.cssSelector("input#FirstName"));

        //TH2. Nếu tìm thấy >1 element
        // Lấy ra element ở vị trí đầu tiên để thao tác
        //Notes: Để tránh TH tìm thấy >1 element thì cần lưu ý:
        //+ Tìm element duy nhất
     //   driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Testing");
//
//        //TH3. Nếu không tìm thấy element
//        // Vào tìm mà không thấy >> tìm lại element cho đến khi hết time set, tìm lại vẫn không thấy >> fail testcase
//        //Show lỗi: NoSuchElementException
       //driver.findElement(By.cssSelector("input#RememberMe"));
    }
    @Test
    public void TC_02_FindElements() {
        // Khai báo list Element
        List<WebElement> elements = null;

        // TH1. Tìm thấy duy nhất 1 element
        elements = driver.findElements(By.cssSelector("input#FirstName"));
        System.out.println(elements.size());

        // TH2. Tìm thấy >1 element
        // Trả về hết toàn bộ element được tìm thấy
        elements = driver.findElements(By.cssSelector("input[type='text']"));
        System.out.println(elements.size());

        //TH3. Không tìm thấy element:
        // Lần đầu vào tìm không thấy:
        // Tìm lại mà thấy element thì không cần chờ hết tổng time còn lại
        // Tìm lại mà không thấy element và chờ hết tổng time setup:
        //+ Trả về list element = 0
        //+ Không đánh FAIL Testcase
        elements = driver.findElements(By.cssSelector("input#RememberMe"));
        System.out.println(elements.size());
        Assertion assertion = new Assertion();
        assertion.assertEquals(elements.size(), 0);


    }
    @Test
    public void TC_03_Demo() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.findElement(By.xpath("//button[text() = 'Start']")).click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      // String getText = driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText();
        Assertion assertion = new Assertion();
       // assertion.assertEquals(getText, "Hello World!");
        assertion.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());


    }

    @Test
    public void TC_04_Presence() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a#u_0_5_vq")).click();
        // Present: có trong HTML
        // 1. Element có trên UI và có trên DOM
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form#u_0_m_L4 input#email")));

        // 2. Element không có trên UI nhưng có trên DOM
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form#u_0_m_L4 input#password")));


    }

    @Test
    public void TC_05_Staleness() {
        //Bước 1. Thao tác cho element xất hiện trong HTML
        //Bước 2. Thao tác cho element không xuất hiện trong HTML nữa
        //Bước 3. Dùng wait để check cho element không xuất hiện trong DOM nữa
        // Ví dụ của thầy méo minh hoạ được
        // Thường thì Staleness ít khi sử dụng
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("form#u_0_m_L4 input#email"))));

    }
    @Test
    public void TC_06_findElement_and_findElements() {
        // khác nhau giữa findElement (Web và findElements
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
