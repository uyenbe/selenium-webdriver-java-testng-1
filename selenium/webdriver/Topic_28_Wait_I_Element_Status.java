package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_28_Wait_I_Element_Status {
    WebDriver driver;
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
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }
    @Test
    public void TC_01_() {
        //I. Đồng bộ và bất đồng bộ (Sync/Async):
        // Đồng bộ là step trước hoàn thành rồi thì step sau mới start
        // Bất đồng bộ là step trước/tiến trình trước chưa hoàn thành nhưng step khác/nhiều tiến trình khác đã start
        // áp dụng hàm Wait để cho các TCs chạy theo dạng đồng bộ
        //Có 2 cách:
        //Cách 1. Chờ cho step trước hoàn thành
        // Cách 2. Chờ cho element của step sau xuất hiện (>> step trước đã hoàn thành)

        //------------
        //II. Element Condition - Điều kiện kiểm tra element
        //Các điều kiện check element:
        //1. Element vừa có trên UI và DOM ( cây HTML)
        //2. Element không có trên UI nhưng có trên DOM
        //3. Element không có trên UI và không có trên DOM
        // Trạng thái của element sẽ được kết hợp 1 trong 3 trạng thái trên

        //----------------
        //III. Element Status
        //Một số Element Status:
        // - Visibale: 1 element được xem là visible/display/hiển thị khi thoả mãn điều kiện 1 - có trên UI và DOM
        // - Invisible: 1 element được xem là invisible/undisplay/không hiển thị khi thoả mãn điều kiện 2 hoặc 3
            // - không có trên UI & có hoặc không có trên DOM
        // - Presence: 1 element được xem là presence/ khi thoả mãn điều kiện 1 hoặc 2,
            // chỉ cần element có trong DOM không quan tâm có trên UI hay không
        // - Staleness: có nghĩa là 1 element xuất hiện tại thời điểm A và sau đó tại thời điểm B thì element không còn trong DOM nữa
        // 1 element được xem là Staleness khi thoả mãn điều kiện 3 - element không còn trong DOM nữa

        //---------------------//
        // Cơ chế Wait: áp dụng chung cho áp dụng chung cho tất cả các loại Wait:
        // 1. Implicit:
            // polling time mặc định 0.5s không sửa được - thời gian tìm lại element mỗi lần ko tìm thấy element
        // 2. Explicit:
            // polling time mặc định 0.5s và có thể thay đổi được
        // 3. Fluent wait:
            // polling time tự set và thay đổi được
        // Nếu vào và tìm thấy element thì không cần chờ hết time set (ví dụ 13s)
        // Nếu vào mà không tìm thấy element ngay thì cứ 0.5s tìm lại 1 lần cho đến khi hết 13s thì sẽ có 2 TH sảy ra:
        //- TH1. Trong quá trình tìm lại mà thấy element thì không cần chờ hết tổng time còn lại nữa
        //- TH2. Trong quá trình tìm lại và không thấy element cho đến khi hết 13s thì đánh fail TCs


    }
    @Test
    public void TC_02_Visible() {
        // Khai báo và khởi tạo hàm Wait
        driver.get("https://www.facebook.com/");
        // 1. Element có trên UI và có trên DOM
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

    }
    @Test
    public void TC_03_Invisible() {
        driver.get("https://www.facebook.com/");
        //- Không có trên UI, có trên DOM >> thêm 1 vài thao tác để xuất hiện element thoả mãn điều kiện
        // Click de hien thi popup
        driver.findElement(By.cssSelector("a#u_0_5_vq")).click();

        //Click icon X de dong popup
        driver.findElement(By.cssSelector("div#u_0_l_Qo")).click();

        //Wait cho den khi email khong ton tai tren DOM
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("form#u_0_m_L4 input#email")));



    }

    @Test
    public void TC_04_Presence() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a#u_0_5_vq")).click();
        // Present: có trong HTML
        // 1. Element có trên UI và có trên DOM
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form#u_0_m_L4 input#email")));

        // 2. Element không có trên UI nhưng có trên DOM
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form#u_0_m_L4 input#password")));


    }

    @Test
    public void TC_05_Staleness() {
        //Bước 1. Thao tác cho element xất hiện trong HTML
        //Bước 2. Thao tác cho element không xuất hiện trong HTML nữa
        //Bước 3. Dùng wait để check cho element không xuất hiện trong DOM nữa
        // Ví dụ của thầy méo minh hoạ được
        // Thường thì Staleness ít khi sử dụng
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("form#u_0_m_L4 input#email"))));

    }
    @Test
    public void TC_06_findElement_and_findElements() {
        // khác nhau giữa findElement (Web và findElements
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
