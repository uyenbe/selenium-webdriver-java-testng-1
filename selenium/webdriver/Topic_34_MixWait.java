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
import java.util.Date;

public class Topic_34_MixWait {
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
        // explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // set thời gian timeout = 10s và sau 0,3s thì tìm lại 1 lần (pooling time = 0,3s)
        //Dùng cách nào cũng được
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));
        driver.manage().window().maximize();


    }

    @Test
    public void TC_01_Element_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        // TH Wait với Explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

        //TH Wait với Implicit
        driver.findElement(By.cssSelector("input#email"));

        //Notes:
        //Trong TH tìm thấy element thì việc dùng cả implicit và Explicit đều không bị ảnh hưởng gì cả
        // Vì vào phát tìm thấy element luôn thì đã pass rồi nên không cần chờ nữa


    }

    @Test
    public void TC_02_Element_Not_Found_Only_Implicit_() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://live.techpanda.org/index.php/customer/account/login/");
        //TH Wait với Implicit
        driver.findElement(By.cssSelector("input#amail"));

        //Notes: Trong TH không tìm thấy element và dùng Implicit wait thì:
        // Mới đầu vào không thấy:
        // Tìm lại mà thấy element thì ko cần chờ hết tổng time còn lại
        // Tìm lại mà không thấy tiếp tục tìm đến hết tổng time thì đánh fail TCs
        // Show lỗi: NoSuchElementExpection


    }
    @Test
    public void TC_03_Element_Not_Found_Only_Explicit_By() {
        // Tham số By
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        By emailTextbox =By.cssSelector("input#amail");
        // Lúc này implicit = 0
        //Explicit = 3

        // TH Wait với Explicit ở đây tham số
        System.out.println("Start time = " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailTextbox));
        } catch (Exception e) {
            System.out.println("End time = " + getDateTimeNow());
            throw new RuntimeException(e);
        }

        //Notes: Trong TH không tìm thấy element và dùng Explicit wait thì:
        // Mới đầu vào không thấy:
        // Vì trong hàm visibility... có hàm findElement >> bị ảnh hưởng 100% bởi hàm ImplicitWait
        // Nhưng ở đây ko set timeout cho ImplicitWait tức là Implicit Wait = 0
        // Vì không set timeout cho findElement nên khi chạy mà không tìm thấy element thì nó sẽ lấy timeout của Explicit
        // và trả ra lỗi Timeout Exception khi không tìm thấy element - đây là lỗi timeout của Explicit Wait

    }

    @Test
    public void TC_03_Element_Not_Found_Only_Explicit_02_WebElement() {
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        // Lúc này implicit = 0
        //Explicit = 3
        // TH Wait với Explicit mà có tham số truyền vào là WebElement như ở đây được:
        // element ở bên ngoài được truyền như 1 tham số trong hàm wait của Explicit và bị ảnh hưởng bởi timeout bên ngoài
        // Và các hàm trong try chạy từ trong ra ngoài, tức là chạy từ hàm findElement trước rồi mới đến visibility
        // nên khi set Implicit = 0 nên hàm findElement nó chạy và ko thấy element phát thì nó báo fail luôn
        // >> Khi dùng các hàm wait của Explicit nên tránh dùng các hàm truyền element vào (vì nó sẽ bị ảnh hưởng bởi implicit,
        // vì mình phải tìm thấy element thì mới đưa vào hàm wait để kiếm tra nên trong TH ko tìm thấy sẽ báo fail ngay lập tức
        // Còn dùng các hàm có tham số là By >> nhận timeout của Explicit
        System.out.println("Start time = " + getDateTimeNow());
        WebElement emailTextbox = driver.findElement(By.cssSelector("input#omail"));

        try {
            explicitWait.until(ExpectedConditions.visibilityOf(emailTextbox));
        } catch (Exception e) {
            System.out.println("End time = " + getDateTimeNow());
            throw new RuntimeException(e);
        }


    }

    @Test
    public void TC_04_Element_Not_Found_Only_Explicit_Mix_Implicit() {

        //TH: Implicit < Explicit
        //Trường hợp mix 2 cái implicit và explicit thì 2 hàm wait này cùng chạy nhưng chúng chạy song song với nhau
        // Và lúc nào Implicit cũng chạy trước, nếu ko tìm thấy element thì mới nhảy qua check điều kiện visibility, lúc này explicit mới được chạy
        // Mặc dù chạy song song nhưng vẫn có sự chênh lệch time từ 0,5 đến 1s
        // Không cộng dồn timeout của 2 thằng wait này lại để tính timeout của TCs. Vì chúng ko ảnh hưởng trực tiếp đến nhau
        // Cơ chế chạy song song khi mix 2 wait này như sau: nó sẽ chạy thằng Implicit wait trước, sau đó mới chạy đến Explicit Wait
        // Nếu timeout của Implicit > Explicit thì lúc này Explicit đã hoàn thành/chạy xong rồi, nhưng thằng Implicit vẫn còn đang chờ để tìm Element
        // Nên >> thời gian hoàn thành Steps/TCs = timeout của Implicit nhưng throw ra lỗi của Explicit
        //Vì Explicit đã chạy xong rồi, nhưng vẫn đợi Implicit chạy xong thì mới kết thúc
        //TH implicit < Explicit thì lúc này Implicit đã chạy xong rồi - không tìm element nữa, nhưng Explicit vẫn chưa chạy xong
        // Nên >> thời gian hoàn thành Steps/TCs = timeout của Explicit và throw là lỗi Timeout Exception...
        // Có thể hiểu là thời gian kết thúc = time tối đa của 1 trong 2


            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
            explicitWait = new WebDriverWait(driver, Duration.ofSeconds(4));

            driver.get("http://live.techpanda.org/index.php/customer/account/login/");

            // TH Wait với Explicit
        // Để tính xem steps này chạy hết bao nhiêu giây >> tạo 1 hàm tính thời gian chạy code sau đó gọi hàm vào để tính
        // Gọi hàm này trước và sau khi chạy steps
        System.out.println("Start time = " + getDateTimeNow());

        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#amail")));
        } catch (Exception e) {
            System.out.println("End time = " + getDateTimeNow());
            throw new RuntimeException(e);
        }



    }

    @Test
    public void TC_05_Demo() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://gofile.io/?t=uploadFiles");
        Assertion assertion = new Assertion();

        //Wait cho hiển thị màn hình button upload file hiển thị
        assertion.assertTrue(explicitWait.until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("div.text-center.cursor-pointer"))).isDisplayed());


        By inputBy = By.xpath("//input[@type='file']");

        //Thực hiện upload fiels
        driver.findElement(inputBy).sendKeys(test1Path+ "\n" + domPath + "\n" + domTreePath);

        //Wait cho icon Loading tại màn hình upload file không còn hiển thị nữa
        assertion.assertTrue(explicitWait.
                until(ExpectedConditions.invisibilityOfElementLocated(By
                                .xpath("//div[@class='processing-indicator']//div[contains(@class,'animate-spin')]"))));

        // Wait cho các thanh progress bar biến mất
        // Vì chỗ này có hàm findElement >> cần set up imlicit wait
        explicitWait.until(ExpectedConditions
                .invisibilityOfAllElements(driver
                        .findElements(By.cssSelector("div.file-progressbar"))));

        //Kiểm tra cho text hiển thị upload file thành công >> chỗ này ko cần wait cũng được
        assertion.assertTrue(driver.findElement(By
                        .xpath("//div[@class='text-center']//h2[text()='Upload Complete']"))
                .isDisplayed());


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








    }

    public String getDateTimeNow()
    {
        Date date = new Date();
        return date.toString();
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
