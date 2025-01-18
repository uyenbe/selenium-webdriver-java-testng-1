package webdriver;

import org.openqa.selenium.By;
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
import java.util.concurrent.TimeUnit;

public class Topic_14_Action_I {
    WebDriver driver;

    // Khai báo thư viện Action
    Actions actions; // dùng thư viện Actions chứ không dùng Action

    // Khởi tạo assert chung cho các TCs bên dưới để verify
    Assertion assertion = new Assertion();

    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

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
        // Khởi tạo Action
        actions = new Actions(driver); // tham số của action là webdriver

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       // driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Action_Hover() {
        // sau khi khởi tạo Action >> có thể gọi ra để dùng:
        // get - getter: lấy ra
        // set - setter: gán vào
        // Chỉ dùng cho các biến private

        // Một số hàm cần chú ý: Hàm mà không có tham số như: click()
        // >> thường ít dùng vì không có tham số dùng cho browser, và thường thì browser ít dùng các thao tác đó
        // còn có tham số dùng cho các element:
        //1. click - Sẽ hover element rồi mới click (hơi khác só với hàm click của webelement
        //2. clickAndHold - Click vào nhưng chưa thả chuột ra, thường dùng khi di chuyển các element sang vị trí khác kiểu kéo thả element
        //3. contextClick - Click chuột phải lên 1 element nào đó
        //4. doubleClick - Click 2 lần chuột trái
        //5. dragAndDrop - Dùng để di chuyển element kiểu ko thả element
        //6. moveToElement - Dùng để hover element,
        // TH dùng moveToElement để hover vào tooltip:
        // nếu trên 1 màn hình có >1 tooltip ở trước tooltip cần moveToElement, và chạy scrip bị lỗi bởi đang move đến các tooltip ở trước
        // >> thì cần mở rộng size của màn hình và thêm sleep để đợi
        //7. pause - Dùng để dừng lại 1 action
        //8. sendKey thường kết hợp với keyUp và keyDown
        //9. build - dùng khi cần kết hợp nhiều hàm cùng 1 lúc, but có thể ko cần dùng hàm này
        //10. perform - dùng cho tất cả các hàm để thực thi câu lệnh, nếu ko có hàm perform thì sẽ không chạy được
        //11. release - Dùng cho hàm clickAndHold vì hàm này có tính năng nhả chuột trái
        //12. scrollToElement - Scroll đến đúng element mà ko cần toạ độ
        //13. scrollByAmount - Scroll đến element nhưng cần toạ độ
        //14. scrollFromOrigin - Kết hợp của scrollToElement và scrollByAmount
        //15. keyDown - nhấn xuống
        // 16. keyUp - nhả chuột ra

        //Notes: Khi chạy testcase có liên quan đến Action thì ko được dùng/di chuyển chuột, bàn phím vì như thế sẽ làm fail TCs

        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTextbox = driver.findElement(By.xpath("//input[@id='age']"));
        actions.moveToElement(ageTextbox).perform();
        sleepInSeconds(2);

        // Verify tooltip sau khi hover chuột
        // cách 1: getText để verify >> chắc chắn nhất
        assertion.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText()
                ,"We ask for your age only for statistical purposes.");

        // Cách 2: dùng isDisplay >> check được mỗi element hiển thị thôi chứ chưa check được nội dung của tooltip
        assertion.assertTrue(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).isDisplayed());



    }
    @Test
    public void TC_02_Hover_Myntra() {
        driver.get("http://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
        sleepInSeconds(2);

        // Web Element cũng có hàm click
       // driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()= 'Home & Bath']")).click();

        // Hàm click của Action
        actions.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()= 'Home & Bath']"))).perform();
        sleepInSeconds(3);

        // Verify kết quả
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(), "Kids Home Bath");
        sleepInSeconds(10);

    }
    @Test
    public void TC_03_Hover_Fahasa(){
        driver.get("https://www.fahasa.com/");
        sleepInSeconds(50);
        actions.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();
        sleepInSeconds(3);

        actions.moveToElement(driver.findElement(By.xpath("//span[@class='menu-title' and text()='Làm Đẹp - Sức Khỏe']"))).perform();
        sleepInSeconds(2);
        actions.click(driver.findElement(
                By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Sản Phẩm Làm Đẹp']")))
                    .perform();
        sleepInSeconds(2);

        // Verify
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong")).getText(),"SẢN PHẨM LÀM ĐẸP");


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
