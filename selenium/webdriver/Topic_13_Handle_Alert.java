package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_13_Handle_Alert {
    WebDriver driver;

    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    // Define username, pass dùng chung cho các TCs
    String username = "admin";
    String password = "admin";

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
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Accept_Alert() {
        //Accept alert: không cho user cancel
        // Viết trên console: alert("Accept alert")
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        // Chờ cho alert pressent >> Dùng hàm wait, cụ thể: expliciWait
        // Nếu trong time chờ mà xuất hện alert thì tự switch vào
        // Nếu ko xuất hiện thì fail

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        sleepInSeconds(3);

      //  Alert alert = driver.switchTo().alert(); // Tuy có switch vào nhưng ko có wait


        // Các hàm trong thư viện Alert:
        //  1. Dùng để Cancel Alert >> Dùng cho Confirm alert vs Pormpt alert:
        //   void dismiss();
        //  2. Dùng để accept >> Dùng cho Accept alert vì accept alert ko cancel được
        //   void accept();
        //   String getText(); Get text trong alert
        //   void sendKeys(String keysToSend); Nhập text vào alert
        // Thư viện alert không dùng cho Authentication alert

        Assertion assertion = new Assertion();
        assertion.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept(); // Khi accept / cancel alert thì alert sẽ mất >> text trong alert sẽ bị mất đi
        // Khi đó dùng thư viện alert gọi các hàm khác sẽ không được >> sẽ báo alert present
        // Không nói là alert visable mà nói là alert present là vì alert là 1 loại của javascript nên chỉ cần nó present trong HTML là có thể verify được mà ko cần hiển thị trên giao diện
        // Còn những element inspect được lên giao dện thì mới gọi là visiable
        sleepInSeconds(3);

        // Get text để verify
        assertion.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");

    }
    @Test
    public void TC_02_Confirm_Alert() {
        //Confirm alert: user được chọn yes or no >> gửi lại thông tin lên server
        //Viết trên console: confirm("Bạn có chắc chắn không?")

        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        sleepInSeconds(3);
        Assertion assertion = new Assertion();
        assertion.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        sleepInSeconds(2);
        assertion.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");


    }
    @Test
    public void TC_03_Prompt_alert() {
        //prompt alert: cho nhập thông tin để submit lên server
        //Viết trên console: prompt("nhập số CCCD", "1234") >> có 2 tham số
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInSeconds(3);

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assertion assertion = new Assertion();
        assertion.assertEquals(alert.getText(), "I am a JS prompt");

        String text = "Uyến xinh đẹp";
        alert.sendKeys(text);
        sleepInSeconds(3);

        alert.accept();
        sleepInSeconds(2);

        assertion.assertEquals(driver.findElement(resultText).getText(),"You entered: "+ text);




    }
    @Test
    public void TC_04_Authentication_alert() {
        //Authentication alert: user nhập thông tin về username/pass vào, để bảo mật
        // Thư viện Alert không sử dụng cho authentication alert vì liên quan đến bảo mật
        // Dùng DevTool Protocol (CDP) trên Chrome/ Edge vì dùng (Chromium): giả lập lại những thứ mình test trên devtool

        // Cách 1: Truyền trực tiếp username/pass vào url theo cú pháp:
        // http/https:// + username + : + password + @ URL
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

        Assertion assertion = new Assertion();
        assertion.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

    }
    @Test
    public void TC_05_Authentication_Navigate_alert() {
        driver.get("http://the-internet.herokuapp.com/");
        String basicAuth = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        driver.get(getAuthenticationUrl(basicAuth, username, password));
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(
                By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).getText(),
                "Congratulations! You must have the proper credentials.");
    }

    public String getAuthenticationUrl(String link, String username, String password) {
        String[] linkArray = link.split("//");
        link = linkArray[0] + "//" + username + ":" + password + "@" + linkArray[1];
        return link;
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
