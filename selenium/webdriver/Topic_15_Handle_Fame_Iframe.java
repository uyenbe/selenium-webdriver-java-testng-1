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

public class Topic_15_Handle_Fame_Iframe {
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
    public void TC_01_Handle_Iframe() {

        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        sleepInSeconds(3);// hiện tại dùng sleep để tắt dialog trên web vì chưa học cái này

        // Phải click vào ảnh của iframe thì mới loading iframe đó ra
        driver.findElement(By.xpath("//img[@alt='Campus Safety Survey']")).click();

        //Swich qua iframe:
        // Có 3 cách swich qua iframe:
        //1. Dựa vào index: page hiện tại có nhiều iframe/frame thì frame đầu tiên sẽ có index = 0 và tăng dần lên
        // khi thêm,sửa,xoá iframe thì index của iframe sẽ bị sai
        //driver.switchTo().frame(0);

        //2. Dựa vào name or ID: phù hợp với site nào mà frame có ID hoặc name (name hoặc ID mà thay đổi thì dùng attribute khác)
        //driver.switchTo().frame("");

        //3. Dựa vào weblement của iframe: có thể hover cả 2 cách trên >> ưu tiên dùng cách này
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frame-one85593366']")));

        //Thực hiện các thao tác trên trang
        Select year = new Select(driver.findElement(By.xpath("//select[@id='RESULT_RadioButton-2']")));
        year.selectByVisibleText("Sophomore");

        //Có thể viết ngắn gọn đoạn Select như sau:
        new Select(driver.findElement(By.xpath("//select[@id='RESULT_RadioButton-3']"))).selectByVisibleText("East Dorm");

        driver.findElement(By.xpath("//label[text()= 'Male']")).click();

        // Quay lại từ B > A
        driver.switchTo().defaultContent();

        // Thao tác tiếp trên page A: Click btn Login
        driver.findElement(By.xpath("//a[contains(@class, 'fs-btn--transparent-kashmir')]")).click();


        // Không nhập dữ liệu tại fomr Login > click btn Login
        driver.findElement(By.id("login")).click();

        // Verify sau khi click btn Login mà ko nhập dữ liệu
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),
                "Username and password are both required.");


        //Notes:
        // Có 3 case cần lưu ý khi thao tác với frame/Iframe
        // Case1: Switch vào frame/Iframe như thế nào (Muốn thao tác vào element trong iframe ntn?)
        // Case2: Đã switch rồi và muốn quay lại để thao tác với pages bên ngoài như thế nào
        // Case3: Từ page A -> switch vào iframe -> trong iframe đó lại chứa 1 iframe khác thì
        // thao tác kiểu gì và quay lại kiểu gì
        // Trả lời cho 3 case trên:
        // 1. Switch vào iframe/frame như thế nào:
        // Khi thao tác từ page A mà có page B được nhúng vào page A (iframe/frame)
        // Muốn thao tác vào các element của B thì cần switch vào thẻ chứa page B ( luôn là thẻ iframe/frame)
        // Tại vì khi mở page A lên thì driver của mình đang đứng ở page A -> không thể thao tác lên element của page B được
        // bắt buộc phải switch sang page B để driver chuyển sang page B thì lúc đó mới thao tác lên element của page B được

        // 2. Đã switch rồi và muốn quay lại để thao tác với pages bên ngoài như thế nào:
        // Dùng lệnh để back lại page A thì driver sẽ quay lại page A:
        // driver.switchTo().defaultContent()

        // 3. Từ page A -> switch vào iframe -> trong iframe đó lại chứa 1 iframe khác thì thao tác kiểu gì và quay lại kiểu gì
        // ví dụ: Page A chứa iframe B và trong B lại chứa iframe C => Từ A có switch qua C được không và ngược lại?
        // Từ A -> C và C -> A không được nha
        // Đề từ A mà sang được C thì cần phải switch từ A -> B sau đó switch từ B -> C
        // Nhưng lệnh quay lại từ C > B lại khác từ B > A. Bởi vì:
        // A là page chính nên khi B > A thì dùng lệnh: driver.switchTo().defaultContent()
        // Còn C là iframe con của B nên khi C > B sẽ dùng lệnh: driver.switchTo().parentFrame()


    }
@Test
    public void TC_02_Handle_Iframe_ToiDiCodeDao() {
        driver.get("https://toidicodedao.com/");
        sleepInSeconds(2);

        WebElement iframeFB = driver.findElement(By.xpath("//iframe[@title='fb:page Facebook Social Plugin']"));

        //scroll into iframe facebook >> dùng hàm action để scroll
        actions.scrollToElement(iframeFB).perform();
        sleepInSeconds(1);

        // Lấy ra số lượng follower: swich vào iframe >> lấy ra số lượng follower
        driver.switchTo().frame(iframeFB);
        sleepInSeconds(1);

        // cách này dùng luôn attribute text() để lấy ra element thay vì dùng class
        WebElement follower = driver.findElement(By.xpath("//div[@class='lfloat']//div[text()]"));

        Assertion assertion = new Assertion();

        //Verify iframe Facebook hiển thị
        assertion.assertTrue(iframeFB.isDisplayed());

        //Verify số lượng follower
        assertion.assertEquals(follower.getText(),"404,154 followers");


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
