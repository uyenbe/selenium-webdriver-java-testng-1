package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;

public class Topic_31_Wait_III_StaticWait {
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


    }
    // Cơ chế wait của Selemium:
    // Implicit Wait và Explici Wait. Trong đó:
    // Implicit Wait: là wait ngầm định vì nó chỉ wait cho việc tìm element
    // Explicit Wait bao gồm:
    // - WebDriver Wait
    // - Fluent Wait
    // Static Wait (sleep): Wait tĩng/Wait cứng >> không linh động, fix cứng thời gian.
    // - Không quan tâm đến steps đã finish chưa mà chỉ quan tâm đến đã hoàn thành xong thời gian được set chưa
    // Các TH sử dụng Static Wait:
    //- Dùng khi đang implement TCs (thử nghiệm TCs)
    // - Dùng với Window/Tab khi wait cho page mới load ra thành công (switch bằng title có >2 window/tab vì:
    //+ WebDriver không có hàm để wait cho all page load thành công
    //+ pageLoadTimeout() -> Dùng cho 1 window/tab mà driver đang active
    // (có nghĩa là chưa switch qua page mới load thì không dùng wait cho page mới được
    // vì driver đã nhảy sang page mới đâu >> không dùng được implicitWait, ExplicitWait, FluentWait)
    //- Dùng sleep cứng sau mỗi lần upload multiple file
    //- Dùng sleep cứng với trình duyệt IE vì nó chậm

    @Test
    public void TC_01_Less_Than() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        Thread.sleep(3000);

        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }
    @Test
    public void TC_02_Equals() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        Thread.sleep(5000);

        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }
    @Test
    public void TC_03_Greater_Than() throws InterruptedException {
        // Trong TH chạy 2 TCs 02 và 03 mà tại TCs 03 không gọi lại URL thì sẽ bị lỗi vì:
        // tìm được element trên DOM nhưng không click vào được bởi:
        // hàm Wait chỉ tác động đến hàm findElement thôi còn hàm click thì không
        // >> phải gọi lại url
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        Thread.sleep(10000);

        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_04_BaiTap() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        Thread.sleep(5000);

        Assertion assertion = new Assertion();
        assertion.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());

    }



    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
//    public void sleepInSeconds(long timeInSecond) {
//        try {
//            Thread.sleep(timeInSecond * 1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
}
