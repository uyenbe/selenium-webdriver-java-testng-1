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

public class DropdownList_Exercise {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
// Đối với textbox thường sẽ check các TH như sau:
    //1. Clear data
    //2. Sendkey
    //Verify data: thường dùng hàm getAttribute()
    // Các thông tin như firstName, lastName hay Email
    // >> cần khai báo ở bên ngoài các TCase để dùng như biến toàn cục để dùng cho nhiều TCs
    String firstName ="Trung", lastName = "Bu";
    String emailAddress = getEmailAddress();
    String CompanyName = "LuBu Entertainment", passWord = "12345678";

    // Khai báo data cho date/month/year
    String day = "23", month = "July", year = "1997";


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
    public void TC_01_Register () {
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);
        // Droplist Ngày/tháng/Năm
        // Vì droplist dạng default: cấu trúc select - option >> dùng thư viện Select để khai báo
        //một số hàm vs droplist
        // dayDroplist.selectByVisibleText(""); - select option hiển thị trên droplist
        // dayDroplist.selectByValue(""); - chỉ dùng cho option có attribute là value
        //dayDroplist.selectByIndex(1); - select option có index 1 trong droplist

        Select dayDroplist = new Select(driver.findElement(By.name("DateOfBirthDay")));
        dayDroplist.selectByVisibleText(day); // lấy ra text hiển thị trên droplist

        //Verify so luong cac option trong droplist Day
        Assertion assertion = new Assertion();
        assertion.assertFalse(dayDroplist.isMultiple());
        assertion.assertEquals(dayDroplist.getOptions().size(),32);

    //Month
        Select monthDroplist = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        monthDroplist.selectByVisibleText(month);
        // Verify
        assertion.assertFalse(monthDroplist.isMultiple());
        assertion.assertEquals(monthDroplist.getOptions().size(), 13);

    //Year
        Select yearDroplist = new Select(driver.findElement(By.name("DateOfBirthYear")));
        yearDroplist.selectByVisibleText(year);
        // verify
        assertion.assertFalse(yearDroplist.isMultiple());
        assertion.assertEquals(yearDroplist.getOptions().size(),112);

        // difference field
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='Company']")).sendKeys(CompanyName);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(passWord);
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(passWord);
        driver.findElement(By.xpath("//button[@id='register-button']")).click();
        sleepInSeconds(2);
        // Verify register successful
        assertion.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");

    }
    @Test
    public void TC_02_Login(){
        driver.get("https://demo.nopcommerce.com");
        //driver.get("https://demo.nopcommerce.com/registerresult/1?returnUrl=/"); link sau khi register done
        Assertion assertion = new Assertion();
        driver.findElement(By.xpath("//a[@class='ico-login']")).click();// để click được vào my account >> cần phải Login vào trước
       sleepInSeconds(2);

        driver.findElement(By.xpath("//input[@class='email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@class='password']")).sendKeys(passWord);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
       // driver.findElement(By.xpath("//div[@class='buttons']//button[@type='submit']")).click();
        sleepInSeconds(3);

        //Click MyAccount
        driver.findElement(By.xpath("//a[@class='ico-account']")).click();

        //Verify
        assertion.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"), firstName );
        assertion.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"), lastName );

        Select dayDrop1 = new Select(driver.findElement(By.name("DateOfBirthDay")));
        assertion.assertEquals(dayDrop1.getFirstSelectedOption().getText(), day);

        Select monthDrop1 = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        assertion.assertEquals(monthDrop1.getFirstSelectedOption().getText(), month);

        Select yearDrop1 = new Select(driver.findElement(By.name("DateOfBirthYear")));
        assertion.assertEquals(yearDrop1.getFirstSelectedOption().getText(), year);

        assertion.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"), emailAddress );
        assertion.assertEquals(driver.findElement(By.xpath("//input[@id='Company']")).getAttribute("value"), CompanyName );







    }


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
