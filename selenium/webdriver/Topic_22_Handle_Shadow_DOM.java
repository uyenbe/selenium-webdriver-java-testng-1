package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_22_Handle_Shadow_DOM {
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
    public void TC_01_Shadow_DOM() {
        driver.get("https://automationfc.github.io/shadow-dom/");

        //Thuộc DOM cha bên ngoài
       // driver.findElement(By.xpath(""));

        //Cách handle shadow DOM:
        //B1. Lấy locator của shadow host parent (thằng chứa shadow root có element mình cần tìm)
        //B2. Từ shadow host tìm được getShadowRoot để lấy ra shadowRoot chứa element mình cần tìm (dạng Search context)
        //B3. Từ shadow root vừa get ra sẽ tìm element bên trong nó qua hàm findElement
        //B4. Trong TH có chứa shadow root con thì cũng thực hiện getShadowRoot như các bước trên.
        // Nhưng sẽ dựa vào thằng shadow root cha để lấy

        // Lấy loactor chứa shadow host parent (thằng chứa shadow root)
        WebElement shadowHostParent = driver.findElement(By.cssSelector("div#shadow_host"));

        //Lấy ra element shadow root
        SearchContext firstShadow = shadowHostParent.getShadowRoot();

        //Dùng shadow root vừa lấy để tìm element.
        // Nếu dùng driver để tìm element sẽ không được và bị báo lỗi vì:
        // driver không thể thao tác vào các element trong Shadow DOM (cụ thể là shadow-root) được
        //Notes: SHADOW-DOM không dùng Xpath được, chỉ chạy được với CSS
        firstShadow.findElement(By.cssSelector("span.info"));

        //Verify element hiển thị
        Assertion assertion = new Assertion();
        assertion.assertTrue(firstShadow.findElement(By.cssSelector("span.info")).isDisplayed());

        // Lấy ra shadow root thứ 2 là con của shadow root trên
        //B1. Lấy ra element chứa shadow host thứ 2
        WebElement secondHostShadow = firstShadow.findElement(By.cssSelector("div#nested_shadow_host"));

        //B2. Lất ra element chứa shadow root thứ 2
        SearchContext secondShadow = secondHostShadow.getShadowRoot();
        assertion.assertTrue(secondShadow.findElement(By.cssSelector("div#nested_shadow_content")).isDisplayed());

    }

    @Test
    public void TC_02_Shadow_DOM() {
        driver.get("https://books-pwakit.appspot.com/");

        //Bài này đi qua 2 shadow-root
        //B1. Lấy element shadow host parent chứa shadow-root cần tìm
        WebElement firstShadowHostElement = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']"));

        //B2. Lấy ra shadow-root đầu tiên (cha của shadow-root cần tìm)
        SearchContext firstShadowRoot = firstShadowHostElement.getShadowRoot();

        //B3. Sendkey
        firstShadowRoot.findElement(By.cssSelector("input#input")).sendKeys("Harry Potter");

        //B3. Lấy element chứa shadow-host thứ 2 - cái cần tìm
        WebElement secondShadowHostElement = firstShadowRoot.findElement(By.cssSelector("book-input-decorator"));

        //B4. Lấy ra shadow-root thứ 2 -  cái cần tìm
        SearchContext secondShadowRoot = secondShadowHostElement.getShadowRoot();

        //B6. Click icon Search
        secondShadowRoot.findElement(By.cssSelector("div.icon")).click();





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
