package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_26_JsExecutor_II_BaiTap {
    WebDriver driver;
    Random random;
    JavascriptExecutor js;
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
        random = new Random();
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_() {
        // Cách 1. Không dùng hàm và tự viết
        //Bước 1. Open url
        js.executeScript("window.location = 'http://live.techpanda.org/'");

        //Bước 2. lấy ra domain
        js.executeScript("return document.domain");

        //Verify domain sau khi lấy
        Assertion assertion = new Assertion();
        assertion.assertEquals(js.executeScript("return document.domain"),
                "live.techpanda.org");

        //Bước 3. Get url
        js.executeScript("return document.URL");
        assertion.assertEquals(js.executeScript("return document.URL"),
                "https://live.techpanda.org/");
        //Bước 4. open mobile page
        WebElement mobilePage = driver.findElement(By.xpath("//a[text()='Mobile']"));
        js.executeScript("arguments[0].click()", mobilePage);

        //Bước 5. add sản phẩm vào giỏ hàng
        WebElement samSung = driver.findElement(By.xpath(
                "//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div//button"));
        js.executeScript("arguments[0].click()", samSung);
        sleepInSeconds(1);

        String samSungText = (String) js.executeScript("return document.documentElement.innerText");
        assertion.assertTrue(samSungText.contains("Samsung Galaxy was added to your shopping cart."));

        //Bước 6. Open Customer Service page
        WebElement customerServicePage = driver.findElement(By.xpath("//a[text()='Customer Service']"));
        js.executeScript("arguments[0].click()", customerServicePage);
        sleepInSeconds(1);
        //Bước 7. Scroll to element in webpage
        WebElement newsletterPage = driver.findElement(By.cssSelector("input#newsletter"));
        js.executeScript("arguments[0].scrollIntoView(true)", newsletterPage);


    }
    @Test
    public void TC_02_Message() {
        js.executeScript("window.location = 'https://login.ubuntu.com/'");

        WebElement loginButton = driver.findElement(By.xpath("//button[@data-qa-id='login_button']"));

        //Case1. Empty Email
        loginButton.click();
        sleepInSeconds(1);
        Assertion assertion = new Assertion();
        String messageEmpty = (String) js.executeScript(
                "return document.querySelector(\"input[class='textType']\").validationMessage");
        assertion.assertEquals(messageEmpty, "Please fill out this field.");

        //Case2. Invalid Email
        //Case2.1
        String invalidEmail = "a";
        driver.findElement(By.cssSelector("input[class='textType']")).sendKeys(invalidEmail);
        loginButton.click();
        sleepInSeconds(1);

        String messageInvalid = (String) js.executeScript(
                "return document.querySelector(\"input[class='textType']\").validationMessage");

        //TH yêu cầu verify trên 2 trình duyệt và message trên 2 trình duyệt khác nhau
        if (driver.toString().contains("ChromeDriver")){
            assertion.assertEquals(messageInvalid,
                    "Please include an '@' in the email address. 'a' is missing an '@'.");
        }else {
            assertion.assertEquals(messageInvalid,
                    "Please include an '@' in the email address. 'a' is missing an '@'.");
        }

        //Case2.2
        invalidEmail = "uyen@";
        driver.findElement(By.cssSelector("input[class='textType']")).clear();
        driver.findElement(By.cssSelector("input[class='textType']")).sendKeys(invalidEmail);
        loginButton.click();
        sleepInSeconds(2);
        String messageInvalid2 = (String) js.executeScript(
                "return document.querySelector(\"input[class='textType']\").validationMessage");
        if (driver.toString().contains("ChromeDriver")){
            assertion.assertEquals(messageInvalid2,
                    "Please enter a part following '@'. '" + invalidEmail + "' is incomplete.");
        }else {
            assertion.assertEquals(messageInvalid2,
                    "Please enter a part following '@'. '"+ invalidEmail + "' is incomplete.");
        }

        //Case 3. Vailid Email
        String email = "uyen" + random.nextInt(10000) + "@gmail.com" ;
        driver.findElement(By.cssSelector("input[class='textType']")).clear();
        driver.findElement(By.cssSelector("input[class='textType']")).sendKeys(email);
        loginButton.click();
        sleepInSeconds(2);
        String messageValid = getElementValidationMessage(
                "//input[@class='textType']//parent::div//following-sibling::div//input[@id='id_password']");
//        String messageValid = (String) js.executeScript(
//                "return document.querySelector(\"input[name='password'][placeholder='Mật khẩu']\").validationMessage");

        assertion.assertEquals(messageValid,"Please fill out this field.");




    }
    @Test
    public void TC_03_() {

    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
    public String getElementValidationMessage(String locator) {
        return (String) js.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
