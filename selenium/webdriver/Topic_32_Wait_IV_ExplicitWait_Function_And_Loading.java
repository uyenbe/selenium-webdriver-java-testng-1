package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class Topic_32_Wait_IV_ExplicitWait_Function_And_Loading {
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
        // set timeout = 10s, mặc định pooling time = 0,5
        // explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // set thời gian timeout = 10s và sau 0,3s thì tìm lại 1 lần (pooling time = 0,3s)
        //Dùng cách nào cũng được
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));
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
    public void TC_00_Demo_LyThuyet() throws InterruptedException {
        // Các hàm dùng trong explicitWait:
        //1. stalenessOf: Wait cho element biến mất trong DOM (trước đó là có tồn tại)
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        //2. invisibilityOfElementLocated: Wait cho element ko hiển thị (còn/ko còn trong DOM đều thoả mãn)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        //3. visibilityOfElementLocated: Wait cho element được hiển thị (phải có trong UI và DOM
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));

        //4. elementToBeClickable: Wait cho element được phép click (radio/checkbox/button/link...)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        //5. urlToBe: wait cho URL của page tuyệt đối
        explicitWait.until(ExpectedConditions.urlToBe(""));

        //6. urlContains: wait cho URL của page tuyệt đối
        explicitWait.until(ExpectedConditions.urlContains(""));

        //7. urlMatches: wait cho URL thoả mãn biểu thức  (Regex)
        explicitWait.until(ExpectedConditions.urlMatches("*#..."));

        //8. jsReturnsValue: Wait cho 1 đoạn JS trả về kiểu d liệu String
        explicitWait.until(ExpectedConditions.jsReturnsValue("return arguments[0].click();"));

        //9. alertIsPresent: Wait cho alert hiển thị trong DOM
        explicitWait.until(ExpectedConditions.alertIsPresent());

        //10. titleIs: Wait cho title của page tuyệt đối (wait trước rồi getTitle() sau)
        explicitWait.until(ExpectedConditions.titleIs(""));

        //11. and: Wait thoả mãn 2 điều kiện
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.alertIsPresent()));

        //12.OR: Wait thoả mãn 1 trong 2 điều kiện
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.alertIsPresent()));

        //13.presenceOfElementLocated: Wait cho element có xuất hiện trong DOM (bắt buộc phải có trong DOM)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        //14.attributeContains: Wait cho element có thuộc tính chứa 1 giá trị nào đó
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""), "class", "email"));

        //15.attributeContains: Wait cho element có thuộc tính bằng 1 giá trị nào đó
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""), "class", "email"));

        //16.attributeContains: Wait cho element có thuộc tính không được rỗng
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("")),"class" ));

        //17. domAttributeToBe: Wait cho element có thuộc tính ở trong DOM bằng giá trị nào đó (những element ở trong tab Properties - Không hiển thị trên HTML)
        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")), "baseURL", "https://..."));

        //18.
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")), "id", "email"));

        //19.elementToBeClickable: Wait cho element đã được chọn thành công (checkbox/ radio/ dropdown item)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        //19.elementSelectionStateToBe: Wait cho element đã được chọn thành công/ thất bại (checkbox/ radio/ dropdown item)
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),true));

        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),false));

        //20. frameToBeAvailableAndSwitchToIt: Wait cho frame/ iframe xuất hiện và switch vào
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));

        //21. javaScriptThrowsNoExceptions (nhận vào 1 lệnh js: Wait cho 1 đoạn lệnh JS được thực thi ko trả về bất kỳ Exception nào
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("return arguments[0].click();"));

        //22. not: phủ định lại điều kiện Wait
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        //23. numberOfElementsToBe: Wait cho 1 list element bằng bao nhiêu item.
        // Hàm wait này nó đã thực hiện findElements rồi, sau đó trả về size của việc findElements
        // Vì thế mà có thể dùng hàm Wait trong việc findElements như sau:
        List<WebElement> elements = explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 2));
        Assertion assertion = new Assertion();
        assertion.assertEquals(elements.size(), "2");

        //24. numberOfElementsToBeLessThan: Wait cho 1 list item ít hơn bao nhiêu item (kiểu ước lượng)
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("div#name"), 6));
        //numberOfElementsToBeMoreThan: Wait cho 1 list item nhiều hơn bao nhiêu item (kiểu ước lượng)
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""), 7));

        //25. numberOfWindowsToBe: Wait cho số lượng Window/Tab bằng bao nhiêu
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));

        //26. textToBe: Wait cho 1 đoạn text bằng tuyệt đối
        // Dùng trước hàm getText()
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), ""));
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""), Pattern.compile("a*b")));

        //27. refreshed: Wait cho 1 element hay bị change/update/refresh lại
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));




    }
    //--------------
    // Điều kiện để set hàm wait:
    //- Điều kiện ở steps sau được xuất hiện
    //- Điều kiện ở steps trước được hoàn thành

    // Luận điểm 1: Cách set timeout:
    // - Nếu timeout mình set không đủ >> TCs bị fail như bình thường
    // >> thông báo lỗi: TimeoutException: Expected condition failed: waiting for ....(hàm mà mình dùng trong wait)
    // - Set vừa đủ timeout >> TCs pass - nhưng thường khó xác định được timeout vừa đủ
    //- Set dư timeout >> Ko cần chờ hết timeout

    // Luận điểm 2: Một bài toán có thể dùng 1 hoặc nhiều cách Wait, miễn sao nó phù hợp là được
    // Tips: Khi wait xong nó sẽ trả về 1 kiểu dữ liệu tương ứng: boolean/element/elements >> điều kiện mk đang cần
    // >> mình có thể lấy dữ liệu đó thao tác luôn bằng các define biến ứng với giá trị được trả về
    // Ví dụ: ở TCs 04

    @Test
    public void TC_01_Less_Than() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        // hàm get url không bị ảnh hưởng bởi hàm wait nên hàm wait để ở trên hay dưới đều getUrl() đều được
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        // Điều kiện Wait
        // Chờ cho element hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_02_Equals() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }
    @Test
    public void TC_03_Greater_Than() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_04_Demo() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        //Visible (Dành cho 1 element sắp xuất hiện)
        WebElement helloText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        Assertion assertion = new Assertion();
        assertion.assertEquals(helloText.getText(), "Hello World!");

        // Invisibale: Dành cho 1 element sắp biến mắt/kỳ vọng biến mất (loading icon kỳ vọng biến mất)
        boolean loadingIconStatus = explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        // Vì là kỳ vọng element invisible >> phải là assertTrue
        assertion.assertTrue(loadingIconStatus);

        //Text: Chờ cho text xuất hiện trong element
        boolean helloTextStatus = explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!"));
        assertion.assertTrue(helloTextStatus);


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
