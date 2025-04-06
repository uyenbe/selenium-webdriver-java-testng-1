package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_16_Handle_Fixed_Popup {
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
    public void TC_01_NgoaiNgu24h() {

        //Popup cố định: Popup hiển thị or ko hiển thị theo yêu cầu/bussiness >> mở hoặc đóng theo yêu cầu của mình
        //Popup random: Có 2 TH:
        // TH1: Nó sẽ luôn hiển thị >> mình có thể đóng và action tiếp
        // TH2: Nó ko hiển thị luôn, ví dụ như những popup được set theo từng thời kỳ sau thời kỳ đó thì sẽ ko hiển thị nữa

        driver.get("https://ngoaingu24h.vn/");
        sleepInSeconds(2);
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSeconds(2);

        By dialogLogin = By.cssSelector("div.MuiDialog-container>div.MuiPaper-root");

        Assertion assertion = new Assertion();
        // Kiểm tra 1 element hiển thị có trong HTML
        // Hiển thị trên UI >> true
        // Không hiển thị trên UI >> false
        assertion.assertTrue(driver.findElement(dialogLogin).isDisplayed());

        driver.findElement(By.cssSelector("input[autocomplete='username']")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("automationfc");
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("form>div>button.dialog-button")).click();
        sleepInSeconds(2);

        assertion.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("svg[data-testid='CloseIcon']")).click();
        sleepInSeconds(3);
        assertion.assertEquals(driver.findElements(dialogLogin).size(), 0);



    }
@Test
    public void TC_02_Kyna() {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        sleepInSeconds(4);

        Assertion assertion = new Assertion();
        assertion.assertTrue(driver.findElement(By.cssSelector("div.right")).isDisplayed());
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        sleepInSeconds(1);

        assertion.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");



}
@Test
public void TC_03_Tiki(){
        driver.get("https://tiki.vn/");
        sleepInSeconds(2);

        //popup này là popup marketing:
        // Hiển thị cố định khi vừa mở trang/site ra
        // Khi đóng popup thì ko hiển thị trên HTML nữa
        // Khi reload lại trang thì lại hiển thị popup
        driver.findElement(By.cssSelector("img[alt='close-icon']")).click();
        sleepInSeconds(1);

        driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
        sleepInSeconds(1);

        Assertion assertion = new Assertion();

        By loginPopup = By.cssSelector("div.styles__Root-sc-2hr4xa-0");
        assertion.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        sleepInSeconds(1);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSeconds(1);
        assertion.assertEquals(driver.findElement(By.xpath("//input[@name='email']//parent::div//following-sibling::span[1]")).getText(),
                "Email không được để trống");
        assertion.assertEquals(driver.findElement(By.xpath("//input[@name='email']//parent::div//following-sibling::span[2]")).getText(),
            "Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("button.btn-close")).click();
        sleepInSeconds(1);

        // verify popup sau khi đóng không còn trên DOM
        // Dùng findElements với size = 0,
        // cách dùng findElements với check popup có hiển thị trên DOM/ có trên cây HTML cũng được, nhưng dùng với size = 1.
        // Nhưng cách này sẽ ko chắc chắn là element đó có hiển thị trên UI hay không vì nó chỉ đang check là có hiển thị hay ko trên cây HTML thôi
        assertion.assertEquals(driver.findElements(loginPopup).size(), 0);

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
