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

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class Topic_33_Wait_IV_ExplicitWait_Ajax {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    // Lấy ra đường dẫn lưu folder file
    String uploadFolder = projectPath + File.separator + "uploadFiles" + File.separator;

    //Khai báo tên các ảnh
    String dom = "DOM.png";
    String domTree = "dom-tree.png";
    String test1 = "Test1.jpg";

    // Khai báo filePath
    String domPath = uploadFolder + dom;
    String domTreePath = uploadFolder + domTree;
    String test1Path = uploadFolder + test1;



    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new ChromeDriver();
        // set timeout = 10s, mặc định pooling time = 0,5
         explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // set thời gian timeout = 10s và sau 0,3s thì tìm lại 1 lần (pooling time = 0,3s)
        //Dùng cách nào cũng được
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));
        driver.manage().window().maximize();


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
    public void TC_01_Calendar() {

        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        // Wait cho đến khi element calendar hiển thị
        // vì hàm wait này trả về web element nên có thể viết gộp lại vào hàm verify luôn
        //explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));

        //Wait and Verify calendar element is displayed
        Assertion assertion = new Assertion();
        assertion.assertTrue(
                explicitWait.until(ExpectedConditions.
                        visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1"))).isDisplayed());


        // Wait and verify text >> đoạn này ko nên dùng textTobePresent vì nó chỉ check element có trong DOM là được,
        // ko quan tâm đến text có hiển thị trên UI hay ko
        // còn textTobe >> quan tâm đến text có hiển thị trên UI vì
        // hàm đó dùng hàm getText() trong doccument của hàm để lấy ra value nên
        // >> phải có text hiển thị thì mới getText được
        assertion.assertTrue(explicitWait.until(ExpectedConditions.
                textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),
                "No Selected Dates to display.")));

        // Wait and Click vì
        // hàm wait trả về kiểu dữ liệu webElement >> không cần phải viết lại hàm findElement rồi click() nữa
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td//a[text()='31']"))).click();

        // Wait and Verify cho ajax loading invisible
        // ajax chỉ load 1 phần trong trang >> chỉ cần bắt phần loading biến mất là được
        assertion.assertTrue(explicitWait.until(ExpectedConditions.
                invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1']>div.raDiv"))));
        // câu cssSelector có nghĩa là: "thẻ div có kết thúc bằng RadCalendar1
        // "//div[contains(@id,'RadCalendar1')]//div[@class='raDiv']" >> dùng xpath

        // Verify text sau khi chọn ngày
        assertion.assertTrue(explicitWait.until(ExpectedConditions.
                textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),
                        "Monday, March 31, 2025")));

        // Có nhiều cách để handle text sau khi chọn ngày. Trong cách dưới đây thì phải làm như thế này:
        //1. Khai báo element text và element trước khi click và element này dùng để check tiếp sau khi click chọn ngày
        // vì thực chất nó là 1 element chỉ có điều là nội dung text nó thay đổi

//        WebElement textDate = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
//        assertion.assertEquals(textDate.getText(), "No Selected Dates to display.");
//
//        //2. Click vào ngày muốn chọn
//        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td//a[text()='31']"))).click();

        //3. Tìm lại 1 lần nữa element chứa text sau khi chọn ngày. Sau đó dùng getText() để verify
        // Tại sao tại bước này không lấy luôn element được khai báo bên trên để getText rồi verify?
        // Tại vì nếu dùng luôn element khai báo bên trên getText() rồi verify nội dung text thì sẽ bị fail bởi vì:
        // Khi khai báo element ở bên trên mình đã lưu trạng thái của element tại lúc click
        // Sau đó khi mk click chọn ngày >> hệ thống loading >> hiển thị text mới >> Lúc này cả cụm đã được loading lại
        // thì lúc này trạng thái của element đã khác rồi vì bị load lại rồi
        // Vì thế nên khi dùng element khai báo để getText() ở bước này sẽ không getText() được và sẽ báo lỗi
        // cách xử lý: tìm lại element textDate này 1 lần nữa sau khi loading biến mất để update lại trạng thái
        // Nhưng cách này KHÔNG HỢP LÝ >> dùng Wait trong bài này là hợp lý nhất
        // NOtes:
        // Trong TH mình không dùng hàm Wait cho ajax chạy thành công thì TCs này sẽ bị fail VÌ:
        // khi loading chưa hết >> textDate chưa được update giá trị mà code đã chạy đến dòng verify text
        // Lúc này nội dung text đang là text cũ, chưa phải text được update sau khi click >> Verify sai


//        assertion.assertTrue(explicitWait.until(ExpectedConditions.
//                invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1']>div.raDiv"))));
//
//        textDate =  driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
//
//        assertion.assertEquals(textDate.getText(), "Monday, March 31, 2025");

    }

    @Test
    public void TC_02_Upload_Files() {
        driver.get("https://gofile.io/?t=uploadFiles");
        Assertion assertion = new Assertion();
        assertion.assertTrue(explicitWait.until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("div.text-center.cursor-pointer"))).isDisplayed());

        By inputBy = By.xpath("//input[@type='file']");

        //Thực hiện upload fiels
        driver.findElement(inputBy).sendKeys(test1Path+ "\n" + domPath + "\n" + domTreePath);

        //Verify file được upload lên
        //1. Verify hiển thị tên file được upload (wait cho hết loading)
//        assertion.assertTrue(explicitWait.until(ExpectedConditions.
//                visibilityOfElementLocated(By.xpath("//div[@class='file-item']//span[text() = 'Test1.jpg']")))
//                .isDisplayed());
//
//        assertion.assertTrue(explicitWait.until(ExpectedConditions.
//                        visibilityOfElementLocated(By.xpath("//div[@class='file-item']//span[text() = 'DOM.png']")))
//                .isDisplayed());
//
//        assertion.assertTrue(explicitWait.until(ExpectedConditions.
//                        visibilityOfElementLocated(By.xpath("//div[@class='file-item']//span[text() = 'dom-tree.png']")))
//                .isDisplayed());


        //2. Verify thanh loading hiển thị 100%
//        assertion.assertTrue(explicitWait.until(ExpectedConditions.
//                visibilityOfElementLocated(By.
//                        xpath("//div[@class='file-item']//span[text() = 'Test1.jpg']" +
//                                "//ancestor::div[@class='file-item']//div[@style='width: 100.00%;']"))).isDisplayed());
//
//        assertion.assertTrue(explicitWait.until(ExpectedConditions.
//                visibilityOfElementLocated(By.
//                        xpath("//div[@class='file-item']//span[text() = 'DOM.png']" +
//                                "//ancestor::div[@class='file-item']//div[@style='width: 100.00%;']"))).isDisplayed());
//
//        assertion.assertTrue(explicitWait.until(ExpectedConditions.
//                visibilityOfElementLocated(By.
//                        xpath("//div[@class='file-item']//span[text() = 'dom-tree.png']" +
//                                "//ancestor::div[@class='file-item']//div[@style='width: 100.00%;']"))).isDisplayed());

        //3. Verify text upload thành công
        assertion.assertEquals(explicitWait.until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("div.flex.justify-center.mb-3 h2"))).getText(),
                "Upload Complete");

        //4. Click vào download link
        driver.findElement(By.cssSelector("div.flex.items-center.text-sm a")).click();
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.truncate a")));

        // Verify từng file
        // file 1:
        assertion.assertTrue(driver.findElement(By
                .xpath("//a[text()='dom-tree.png']//parent::div" +
                        "//parent::div[contains(@class,'overflow-auto')]//following-sibling::div" +
                        "//button[contains(@class,'item_play')]")).isDisplayed());


        assertion.assertTrue(driver.findElement(By
                .xpath("//a[text()='dom-tree.png']//parent::div" +
                        "//parent::div[contains(@class,'overflow-auto')]//following-sibling::div" +
                        "//button[contains(@class,'item_download')]")).isDisplayed());

        //file 2:
        assertion.assertTrue(driver.findElement(By
                .xpath("//a[text()='DOM.png']//parent::div" +
                        "//parent::div[contains(@class,'overflow-auto')]//following-sibling::div" +
                        "//button[contains(@class,'item_play')]")).isDisplayed());


        assertion.assertTrue(driver.findElement(By
                .xpath("//a[text()='DOM.png']//parent::div" +
                        "//parent::div[contains(@class,'overflow-auto')]//following-sibling::div" +
                        "//button[contains(@class,'item_play')]")).isDisplayed());


        //file 3:
        assertion.assertTrue(driver.findElement(By
                .xpath("//a[text()='Test1.jpg']//parent::div" +
                        "//parent::div[contains(@class,'overflow-auto')]//following-sibling::div" +
                        "//button[contains(@class,'item_play')]")).isDisplayed());


        assertion.assertTrue(driver.findElement(By
                .xpath("//a[text()='DOM.png']//parent::div" +
                        "//parent::div[contains(@class,'overflow-auto')]//following-sibling::div" +
                        "//button[contains(@class,'item_play')]")).isDisplayed());
















    }
    @Test
    public void TC_03_Greater_Than() {

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
