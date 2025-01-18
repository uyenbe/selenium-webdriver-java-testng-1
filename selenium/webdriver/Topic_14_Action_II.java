package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_14_Action_II {
    WebDriver driver;

    // Khai báo thư viện Action
    Actions actions; // dùng thư viện Actions chứ không dùng Action

    // Khởi tạo assert chung cho các TCs bên dưới để verify
    Assertion assertion = new Assertion();

    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    Keys keys;

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

        // Check hệ điều hành để check phím xem dùng phím Control hay Command
        // Áp dụng cho những case action dùng bàn phím

        if (osName.contains("Windows")) {
            keys = Keys.CONTROL;
        }else {
            keys = Keys.COMMAND;
        }

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       // driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Click_and_hold_Block() {
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

        driver.get("https://automationfc.github.io/jquery-selectable/");

        List <WebElement> allNumber = driver.findElements(By.xpath("//ol[@class='ui-selectable']//li"));
        actions.clickAndHold(allNumber.get(0))   //Click vào element số 1 và giữ chuột
                .moveToElement(allNumber.get(3))  //Click vào element số 4 và giữ chuột
                .release()   // Nhả chuột trái ra - kết thúc cho sự kiện clickAndHold()
                .perform();  // thực thi các câu lệnh trên
        sleepInSeconds(2);

        //Verify trước khi click anh hold
        Assertion assertion = new Assertion();
        assertion.assertEquals(allNumber.size(), 20);

        // Lấy ra list element đã được clickAndHold()
        List <WebElement> allNumberChoice = driver.
                findElements(By.xpath("//ol[@class='ui-selectable']//li[contains(@class,'ui-selected')]"));
        // Verify sau khi click and hold

        assertion.assertEquals(allNumberChoice.size(), 4);

    }
    @Test
    public void TC_02_Click_and_hold_Random() {
        //Notes: Khi chạy testcase có liên quan đến Action thì ko được dùng/di chuyển chuột, bàn phím vì như thế sẽ làm fail TCs
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List <WebElement> allNumber = driver.findElements(By.xpath("//ol[@class='ui-selectable']//li"));
        //Verify trước khi click anh hold
        Assertion assertion = new Assertion();
        assertion.assertEquals(allNumber.size(), 20);

        // Click key Control và chưa nhả ra
        actions.keyDown(keys).perform();

        // Click random các số: 1,4,8,11,16 >> dùng hàm click chứ ko dùng clickAndHold trong TH này
        actions.click(allNumber.get(0))
                .click(allNumber.get(3))
                .click(allNumber.get(7))
                .click(allNumber.get(10))
                .click(allNumber.get(15))
                .pause(Duration.ofSeconds(3))
                .perform();
        sleepInSeconds(2);

        // Nhả nút Control ra
        actions.keyUp(keys).perform();

        // Verify các giá trị sau khi click
        //1. Lấy ra list number được chọn
        List <WebElement> allNumberChoice = driver.findElements(By
                .xpath("//ol[@class='ui-selectable']//li[contains(@class,'ui-selected')]"));
        //2. Verify
        assertion.assertEquals(allNumberChoice.size(), 5);

    }
    @Test
    public void TC_03_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        actions.moveToElement(driver.findElement(By.xpath("//legend[text()='JavaScript Alerts']")))
                .pause(Duration.ofSeconds(3))
                .doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        sleepInSeconds(3);

        // Verify
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(),"Hello Automation Guys!");

    }
    @Test
    public void TC_04_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        //Click chuột phải vào element
        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']")))
                .perform();
        sleepInSeconds(3);

        // Verify Quit menu hiển thị
        Assertion assertion = new Assertion();
        assertion.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        //Hover vào Quit menu
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        sleepInSeconds(3);

        //Verify Quit menu có trạng thái visible + hover
        assertion.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover.context-menu-visible")).isDisplayed());

        //Click Quit menu
        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();

        // Vì khi click Quit menu có hiển thị alert
        driver.switchTo().alert().accept();
        sleepInSeconds(3);

        //Verify Quit menu không còn hiển thị nữa
        assertion.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

    }
    @Test
    public void TC_05_Drag_And_Drop() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        actions.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droptarget")))
                .release()
                .perform();

        //Verify:
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath("//div[@class = 'k-header painted']")).getText(),"You did great!");
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
