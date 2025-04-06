package webdriver;

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

public class Topic_18_Handle_Random_Popup {
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

        driver.get("https://www.javacodegeeks.com/");
        sleepInSeconds(5);

        By newsletterBook = By.xpath("//div[@data-title='Newsletter Free eBooks'" +
                " and not(contains(@style, 'display:none'))]");

        // Đối với popup random thì cần phải check 2 điều kiện: Có trên DOM và hiển thị trên UI(có kích thước)
        // Nếu popup hiển thị thì close đi rồi action tiếp
        //Câu lệnh if này là check nếu popup này có trên DOM và hiển thị trên UI thì thực hiện đóng popup
        // Và phải dùng findElements để check xem element có trên DOM hay ko, nếu có thì get là phần tử đầu tiên để check hiển thị trên UI
        if (driver.findElements(newsletterBook).size() > 0
                && driver.findElements(newsletterBook).get(0).isDisplayed()) {

            System.out.println("To do IF");
            driver.findElement(By.xpath("//div[@data-title='Newsletter Free eBooks' " +
                    "and not(contains(@style, 'display:none'))]//a[contains(@onclick, 'lepopup_close()')]")).click();

        }
        // Nếu popup không hiển thị thì action tiếp
        System.out.println("Ignore IF");
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Java");
        driver.findElement(By.cssSelector("button#search-submit")).click();


        Assertion assertion = new Assertion();
        assertion.assertTrue(driver.findElement(By.cssSelector("header h1.page-title")).isDisplayed());


    }
@Test
    public void TC_02_() {
        driver.get("https://vnk.edu.vn/");
        sleepInSeconds(2);

        By popup = By.xpath("//div[contains(@class, 'pum-container')]");

        // câu lệnh if này là dạng phủ định, và thay cho việc .size() > 0 >> dùng phủ định của .isEmpty()
        if (!driver.findElements(popup).isEmpty() && driver.findElements(popup).get(0).isDisplayed()) {
            System.out.println("To do IF");
            driver.findElement(By.xpath("//div[contains(@class, 'pum-container')]//button[@aria-label='Close']")).click();
            sleepInSeconds(2);
        }
        System.out.println("Ignore IF");
        driver.findElement(By.xpath("//ul[@id='mega-menu-primary']//a[@aria-current='page']")).click();


}
@Test
public void TC_03(){
        driver.get("https://dehieu.vn/");
        sleepInSeconds(2);

        Assertion assertion = new Assertion();
        By formPopup = By.cssSelector("div.modal-content");
        if (driver.findElements(formPopup).size() > 0 && driver.findElements(formPopup).get(0).isDisplayed()) {
            System.out.println("To do IF");
            driver.findElement(By.cssSelector("button.close")).click();
            sleepInSeconds(2);

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            // Vì sau khi close thì popup vẫn còn trong HTML -> dùng findElement và isDisplayed được
            assertion.assertFalse(driver.findElement(formPopup).isDisplayed());

        }
        System.out.println("Ignore IF");
        driver.findElement(By.xpath("//a[text()=' Đăng ký']")).click();

        assertion.assertTrue(driver.findElement(By.cssSelector("div.b-login")).isDisplayed());

        // Đoạn verify alert thông báo này để nghiên cứu sau
//        driver.findElement(By.cssSelector("button[type='button']")).click();
//        sleepInSeconds(3);
//        assertion.assertEquals(driver.findElement(By.xpath("//span[@data-notify='message'][1]")).getText(),
//                "Vui lòng nhập Số điện thoại");
//
//        assertion.assertEquals(driver.findElement(By.xpath("//span[@data-notify='message'][2]")).getText(),
//                "Họ tên không được bỏ trống");
//
//        assertion.assertEquals(driver.findElement(By.xpath("//span[@data-notify='message'][3]")).getText(),
//                "Chưa nhập đúng định dạng email");
//
//        assertion.assertEquals(driver.findElement(By.xpath("//span[@data-notify='message'][4]")).getText(),
//                "Mật khẩu phải lớn hơn 6 ký tự");






}

@Test
public void TC_04_Facebook(){
        driver.get("https://facebook.com/");
        sleepInSeconds(1);

        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(1);

        By loginPopup = By.cssSelector("div#content");
        Assertion assertion = new Assertion();
        assertion.assertTrue(driver.findElement(loginPopup).isDisplayed());


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
