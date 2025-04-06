package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_27_Handle_Upload_File {
    private static final Logger log = LoggerFactory.getLogger(Topic_27_Handle_Upload_File.class);
    WebDriver driver;
    // Lấy ra đường dẫn tương đối của project
    String projectPath = System.getProperty("user.dir");

    // Lấy ra đường dẫn đến thư mục uploadFiles
    // Cách này là dùng cho hệ điều hành MAC/Linux
    //String uploadFolder = projectPath + "\\uploadFiles\\";

    // Muốn dùng cho tất cả các hệ điều hành thì dùng như sau: (tự detect ra được dấu suộc)
    String uploadFolder = projectPath + File.separator + "uploadFiles" + File.separator;

    // Khai báo tên các ảnh
    String test1 = "Test1.jpg";
    String dom = "DOM.png";
    String domTree = "dom-tree.png";

    // Khai báo đường dẫn của các file ảnh
    String test1Path = uploadFolder + test1;
    String domPath = uploadFolder + dom;
    String domTreePath = uploadFolder + domTree;

    WebDriverWait explicitWait;

    String osName = System.getProperty("os.name");

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
    public void TC_01_Upload_File() {
        //Notes: Khi click vào icon upload file trên web >> open file dialog (popup để upload file)
        // >> open file dialog được gọi là desktop app chứ không phải webapp vì nó là của máy tính
        //element tạo thành tính năng upload: thẻ input có type = "file" - bắt buộc phải có
        // và element này không bắt buộc phải hiển thị
        // và có thể hiển thị ở vị trí khác vị trí upload file trên DOM (vị trí f12)
        // Nếu không có input với type ="file" thì không upload file được
        // Tuy nhiên Selenium không support upload file với open file dialog vì nó là desktop app chứ không phải web app
        // Và để thao tác được với open file dialog thì phải dùng Auto IT/Js... nhưng những cách này không áp dụng từ lâu rồi
        // Hiện tại cách tối ưu nhất là dùng Selenium Sendkey

        //Các cách upload file
        //Cách 1. Selenium Sendkey method:
        // Ưu điểm:
        // Dêc dùng/ dễ cài đặt
        // word với mọi browser (window, MAC, Linux)
        // Cho phép upload nhiều file cùng lúc
        // Không quan tâm đến việc sử lý Open file dialog

        // Các bước: (không hề thao tác với open file dialog - chính là ko click vào btn để hiển thị ra open file dialog)
        // Chỉ cần lấy được element có type = file
            //Bước 1: Khai báo element upload file:
            //Ví dụ cách khai báo: WebElement fileInput = driver.findElement(By.name("uploadFileInput"));

            //Bước 2: Truyền đường dẫn file >> tạo folder chứa file cần upload
            //Ví dụ cách truyền đường dẫn: String filePath = "G:\PROJECT TRAINING...\image_01.png";

            //Bước 3. Sendkey đường dẫn để tự upload file lên
            //Ví dụ: fileInput.sendKeys(filePath);

        // Cách 2. AutoIT - Chỉ dùng được trên Window còn trên MAC/Linux >> không chạy được
        // AutoIT nó là 1 phần mềm
        //Nhược điểm:
        // Chỉ chạy được trên Window
        // Khó dùng và khó cài đặt
        // Mỗi browser phải record 1 script khác nhau
        // TH upload nhiều file sẽ bị giới hạn lượng ký tự trên textbox
        // Không ổn định nếu chạy parrallel testing - chạy nhiều browser cùng lúc
        // Chạy trn các CI system: jenkins/Gitlab/Github/...(headless)

        //Ưu điểm:
        // Work với tất cả các loại browser
        // Upload form nào nó cũng work được

        // Cách 3. Java Robot class
        // - Chỉ word với Window cho paste đường dẫn vào textbox của open file dialog
        // còn trên MAC với Linux không có textbox để paste đường dẫn vào
        //Cách 4. Sikuli >> lỗi thời rồi






    }

    @Test
    public void TC_02_Upload_Single_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        // Vì sau khi upload file đầu tiên lên >> hệ thống reload lại locator của input với type = 'file'
        // NÊN: những lần sau muốn upload thì phải select lại locator nếu không sẽ bị lỗi
        // >> Đặt biến để dùng:
        By inputBy = By.xpath("//input[@type='file']");

        //Load file lên
        driver.findElement(inputBy).sendKeys(test1Path);
        sleepInSeconds(2);

        driver.findElement(inputBy).sendKeys(domPath);
        sleepInSeconds(2);

        driver.findElement(inputBy).sendKeys(domTreePath);
        sleepInSeconds(2);

        // Verify file được upload
        Assertion assertion = new Assertion();
        assertion.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = 'Test1.jpg']")).isDisplayed());
        assertion.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = 'DOM.png']")).isDisplayed());
        assertion.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = 'dom-tree.png']")).isDisplayed());

        //Click upload cho từng file.
        // Vì có nhiều btn upload nên find tất cả sau đó dùng vòng for để duyệt qua lần lượt từng btn
        List<WebElement> uploadButtons = driver.findElements(By.cssSelector("td button.start"));
        for (WebElement uploadButton : uploadButtons){
            uploadButton.click();
            sleepInSeconds(2);
        }

        // Verify các file được upload thành công
        assertion.assertTrue(driver.findElement(By.xpath("//a[text()='" + test1 + "']")).isDisplayed());
        assertion.assertTrue(driver.findElement(By.xpath("//a[text()='" + dom + "']")).isDisplayed());
        assertion.assertTrue(driver.findElement(By.xpath("//a[text()='" + domTree + "']")).isDisplayed());


    }

    @Test
    public void TC_03_Upload_Multiple_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        // Vì sau khi upload file đầu tiên lên >> hệ thống reload lại locator của input với type = 'file'
        // NÊN: những lần sau muốn upload thì phải select lại locator nếu không sẽ bị lỗi
        // Để upload multiple file thì thẻ input có type = file phải có thuộc tính multiple
        // >> Đặt biến để dùng:
        By inputBy = By.xpath("//input[@type='file']");

        //Load file lên  - nhiều file cùng lúc >> cộng các file path lại với nhau
        driver.findElement(inputBy).sendKeys(test1Path + "\n"+ domPath + "\n" + domTreePath);
        sleepInSeconds(2);

        // Verify file được upload
        Assertion assertion = new Assertion();
        assertion.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = 'Test1.jpg']")).isDisplayed());
        assertion.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = 'DOM.png']")).isDisplayed());
        assertion.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = 'dom-tree.png']")).isDisplayed());

        //Click upload cho từng file.
        // Vì có nhiều btn upload nên find tất cả sau đó dùng vòng for để duyệt qua lần lượt từng btn
        List<WebElement> uploadButtons = driver.findElements(By.cssSelector("td button.start"));
        for (WebElement uploadButton : uploadButtons){
            uploadButton.click();
            sleepInSeconds(2);
        }

        // Verify các file được upload thành công
        assertion.assertTrue(driver.findElement(By.xpath("//a[text()='" + test1 + "']")).isDisplayed());
        assertion.assertTrue(driver.findElement(By.xpath("//a[text()='" + dom + "']")).isDisplayed());
        assertion.assertTrue(driver.findElement(By.xpath("//a[text()='" + domTree + "']")).isDisplayed());


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
