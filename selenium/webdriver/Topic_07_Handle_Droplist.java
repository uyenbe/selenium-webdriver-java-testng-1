package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_07_Handle_Droplist {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
// Đối với textbox thường sẽ check các TH như sau:
    //1. Clear data
    //2. Sendkey
    //Verify data: thường dùng hàm getAttribute()
    // Các thông tin như firstName, lastName hay Email
    // >> cần khai báo ở bên ngoài các TCase để dùng như biến toàn cục để dùng cho nhiều TCs
    String firstName = "Trung", lastName = "Bu", emailAddress = getEmailAddress();
    //với email, phải dùng hàm random để tránh trùng lặp mail
    String companyName = "LuBu Entertainment", password = "12345678";
    String day = "15", month ="February", year = "1997";

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
       // driver.get("https://demo.nopcommerce.com");
        //https://demo.nopcommerce.com/register

    }
    @Test
    public void TC_00_Droplist() {
        //Để xác định được cách handle Droplist, Cần xác định xem Droplist ở dạng customize hay default. Dựa vào cấu trúc HTML của thẻ chứa Droplist
        // Dạng default: Sau khi f12 lên thẻ chứa Droplist có cấu trúc:
            //Select - thẻ cha
            //Option - thẻ con
        // Dạng custom: Cấu trúc HTML khách select - option
            //Có thể là: div/ul/li...

    }



    @Test
    public void TC_01_Register (){
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);

        //field Date of birth: là dropdown list >> check xem droplist đang ở dạng default hay dạng custom.
        // TH này ở dạng custom
        //bước 1. Khai báo biến cho từng droplist bằng hàm select và hàm này nhận giá trị là một element

        // TH biến dùng lại nhiều lần  >> khai báo biến
        //Select DayDroplist = new Select(driver.findElement(By.name("DateOfBirthDay")));

        //Các hàm thường dùng vs Select:
        //1.
        //DayDroplist.isMultiple(); // Check xem droplist là single hay mutiple
       // DayDroplist.selectByIndex(1); // Hàm này cho mình chọn bằng cái index
            // - tức là đánh số của option theo thứ tự từ trên xống và bắt đầu từ 1.
            // Không nên dùng hàm này vì index có thể thay đổi >> kết quả chạy sau mỗi lần thay đổi index khoong được chính xác nữa
       // DayDroplist.selectByValue(""); // hàm này dùng cho các element có attribute là value
        // Cũng không nên dùng vì: value là attribute không bắt buộc >> có TH element ko có attribute là value
        // TH giá trị của value khác với text hiển thị >> gây khó khăn trong việc nhận biết element.
        //  VD  <option value = '12'> Tỉnh Bình Dương </option>

       // DayDroplist.selectByVisibleText(""); // Hàm này cho phép select với những text hiển thị bên trong droplist
        // Nên dùng hàm này. Vì khi thêm/sửa/xoá text >> không ảnh hưởng bởi index hay attribute là value
        // Hợp với hành vi của end-user
        //Coder/reviewer/maintainer đọc script đều dễ hiểu

        //2. Các hàm bỏ chọn trong droplist
//        DayDroplist.deselectAll(); // Bỏ chọn tất cả các gtri trong droplist
//        DayDroplist.deselectByValue("");// ngược vs hàm select value
//        DayDroplist.deselectByIndex(1); // ngược vs hàm select index >> bỏ chọn vs index bằng bao nhiêu
//        DayDroplist.deselectByVisibleText("");// bỏ chọn với text = ""

        //3.
//        DayDroplist.getAllSelectedOptions(); // lấy ra tất cả item được chọn trong droplist >> dùng để verify. Nhưng thực tế không ha duùng
//        DayDroplist.getFirstSelectedOption(); // dùng cho TH là singgle droplist (chỉ chọn 1 gtri)
//        // Item được chọn s được hiển thị lên đầu trong droplist > dùng hàm này để verify
//        DayDroplist.getOptions(); // Lấy ra xem trong droplist có bnhieu item >> số lượng item có trong dropdown
        // Day dropdown
        Select DayDroplist = new Select(driver.findElement(By.name("DateOfBirthDay")));

        // Chọn ngày
        DayDroplist.selectByVisibleText(day);

        // Verify dropdown là Single (không phải Multiple)
        // Nếu là Multiple >> true
        // Nếu là Single >> false >> dùng assertion
        Assertion assertion = new Assertion();
        assertion.assertFalse(DayDroplist.isMultiple()); // không phải là Multiple >> trả về là false

        //Verify tổng số lượng item trong dropdown là 32 item >> getOptions()
        assertion.assertEquals(DayDroplist.getOptions().size(), 32);

        Select MonthDroplist = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        MonthDroplist.selectByVisibleText(month);
        assertion.assertEquals(MonthDroplist.getOptions().size(),13);



        new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);


        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='Company']")).sendKeys(companyName);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@name='register-button']")).click();
        sleepInSeconds(3);
        // verify register thành công
        assertion.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");

        // log out
        driver.findElement(By.xpath("//a[@class='ico-logout']")).click();

    };

    @Test
    public void TC_02_Login(){
        driver.get("https://demo.nopcommerce.com");
        Assertion asserts = new Assertion();
        //Login
        driver.findElement(By.xpath("//a[@class='ico-login']")).click();
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        sleepInSeconds(1);
        driver.findElement(By.xpath("//a[@class='ico-account']")).click();
        sleepInSeconds(2);
        asserts.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"),firstName);
        asserts.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"),lastName);

        Select day1 = new Select(driver.findElement(By.name("DateOfBirthDay")));
        asserts.assertEquals(day1.getFirstSelectedOption().getText(),day);
        Select month1 = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        asserts.assertEquals(month1.getFirstSelectedOption().getText(),month);
        Select year1 = new Select(driver.findElement(By.name("DateOfBirthYear")));
        asserts.assertEquals(year1.getFirstSelectedOption().getText(),year);

        asserts.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"),emailAddress);
        asserts.assertEquals(driver.findElement(By.xpath("//input[@id='Company']")).getAttribute("value"),companyName);



    };


    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
    public void sleepInSeconds(long timeInSecond){
        try{
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        };

    }
    public String getEmailAddress(){
        Random random = new Random();
        return "TrungBu" + random.nextInt(99999) + "@gmail.com";
    }
}
