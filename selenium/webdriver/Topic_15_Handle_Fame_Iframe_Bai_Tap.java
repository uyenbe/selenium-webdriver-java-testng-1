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

public class Topic_15_Handle_Fame_Iframe_Bai_Tap {
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
    public void TC_01_Handle_Iframe_ToiDiCodeDao() {

        driver.get("https://toidicodedao.com/");
        actions.scrollToElement(driver.findElement(By.cssSelector("aside#facebook-likebox-3"))).perform();

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='fb:page Facebook Social Plugin']")));

        Assertion assertion = new Assertion();
        assertion.assertTrue(driver.findElement(By.cssSelector("iframe[title='fb:page Facebook Social Plugin']")).isDisplayed());
        assertion.assertEquals(driver.findElement(By.cssSelector("div._1drq")).getText(), "404,135 followers");


    }
@Test
    public void TC_02_Handle_Iframe_Campus() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        sleepInSeconds(4);

        driver.findElement(By.cssSelector("img[alt='Campus Safety Survey']")).click();
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#frame-one85593366")));

        Select year = new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2")));
        year.selectByVisibleText("Sophomore");
        sleepInSeconds(1);

        Select residence  = new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3")));
        residence.selectByVisibleText("South Dorm");
        sleepInSeconds(1);

        driver.findElement(By.cssSelector("label[for='RESULT_RadioButton-4_0']")).click();

        driver.findElement(By.cssSelector("input.submit_button")).click();

        driver.switchTo().defaultContent();
        sleepInSeconds(1);

        driver.findElement(By.cssSelector("a.fs-btn--transparent-kashmir")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("button#login")).click();

        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");





}
@Test
public void TC_03_Frame(){
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame(driver.findElement(By.name("login_page")));
        driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("230303");
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSeconds(2);

        driver.switchTo().defaultContent();
        WebElement pass = driver.findElement(By.cssSelector("input#keyboard"));
        Assertion assertion = new Assertion();
        //driver.findElement(By.id("keyboard"));
        assertion.assertTrue(pass.isDisplayed());


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
