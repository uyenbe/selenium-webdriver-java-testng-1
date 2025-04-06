package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_06_Handle_TextBox_TextArea {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
// Đối với textbox thường sẽ check các TH như sau:
    //1. Clear data
    //2. Sendkey
    //3. Verify data. Dữ liệu cần verify ở đâu thì dùng hàm get ở đấy. Ví dụ
    // 3.1 Dữ liệu cần verify nằm ở attribute >> dùng hàm getAttribute()
    //3.2 Dữ liệu cần verify nằm ở ngoài attribute >> thường dùng getText()
    //3.3 Dữ liệu không nằm ở trong và ngoài attribute >> thầy chưa dạy đến
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

    }
    @Test
    public void TC_00_TextBox_and_TextArea() {
        // Textbox: không xuống dòng được
        //textarea: cho phép xuống dòng, bằng dấu /n
        // dấu: /t: chuyển tab
        //Verify dữ liệu trong textbox hoặc text area: Dữ liệu nằm ở đâu thì dùng hàm verify tương ứng
        //Đối với Textbox: dữ liệu thường nằm trong attribute là value >> dùng hàm getAttribute() để verify
        //Đối với Textarea: dữ liệu nằm ở bên ngoài của các thẻ >> dừng hàm getText() để verify

    }
    @Test
    public void TC_01_Login_EmptyEmailPass (){
        driver.get("http://live.techpanda.org/");
        Assertion asserts = new Assertion();
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        sleepInSeconds(1);
        asserts.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),"This is a required field.");
        asserts.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),"This is a required field.");

    };

    @Test
    public void TC_02_Login_Invalid_Email(){
        driver.get("http://live.techpanda.org/");
        Assertion asserts = new Assertion();
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("1234@12345");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        sleepInSeconds(1);
        asserts.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    };

    @Test
    public void TC_03_Pass_Less_6_Character(){
        driver.get("http://live.techpanda.org/");
        Assertion asserts = new Assertion();
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("uyen@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        sleepInSeconds(1);
        asserts.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    };

    @Test
    public void TC_04_Incorrect_Email_Or_Pass(){
        driver.get("http://live.techpanda.org/");
        Assertion asserts = new Assertion();
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("uyen@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        asserts.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");

        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='pass']")).clear();

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automationtest@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Hh000000");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        asserts.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");
    }

    @Test
    public void TC_05_Correct_Email_Or_Pass(){
        driver.get("http://live.techpanda.org/");
        Assertion assertion = new Assertion();
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);

        //Có 1 số cách làm case này như sau:
        //1. Đăng ký trước 1 tài khoản bằng tay (manual), sau đó dùng TK đó login cho case này
            // Dùng cách này là cách bị động bởi vì:
            // Khi hệ thống reset lại sẽ bị mất dữ liệu hoặc xoá DB >> phải đăng ký lại
            // Khi qua môi trường mới (Dev/UAT/Pilot...) thì phải đăng ký lại
        //2. Dùng tính năng Register trước - email không thay đổi
            //Để làm cách này >> chức năng register cũng phải làm auto và
            // mail đăng ký phải fix cứng (hard code) và chỉ dùng được 1 lần, lần sau chạy lại hệ thống sẽ báo mail đã tồn tại
        //3. Dùng tính năng Register trước - email thay đổi (random)
            //Dùng cho mọi trường hợp >> Chạy luôn đúng cho tất cả các case

        //Để làm như case số 3:
        //1. Đăng ký Acc trước
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);

        //Vì phải dùng firstname, lastname, email, pass nhiều lần (register, login và verify) >> sử dụng biến
        //Để email thay đổi (random) >> cần sử dụng thư viện của java >> khai báo trong package javaTester
        String firstname = "Nguyen", lastname = "Uyen", emailaddress = getEmailAddress(), password = "12345678"; // password thì chỉ cần dùng 1 biến
        String fullname = firstname + " " + lastname ;

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstname); // truyền biến vào chứ không phải truyền text
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastname);
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailaddress);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        sleepInSeconds(2);


        assertion.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
        assertion.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + fullname + "!");
        // Do việc lấy thông tin firstname, lastname và email đang bị lặp lại >> tạo biến để sử dụng
        //Biến infor lấy text của màn Register thành công
        String infor = driver.findElement(By.xpath("//h3[text()='Contact Information']//parent::div/following-sibling::div//p")).getText();
        //Chỗ này lấy verify tương đối >> dùng assertTrue() với hàm contains()
        assertion.assertTrue(infor.contains(fullname));
        assertion.assertTrue(infor.contains(emailaddress));

        //1.2 LogOut
        // Để check được acc vừa tạo có login thành công không >> cần phải logout ra sau đó login
        driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
        sleepInSeconds(1);
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        sleepInSeconds(5);
        // Verify xem sau khi logOut hệ thống
        String HomeUrl = driver.getCurrentUrl();
        assertion.assertEquals(HomeUrl,"http://live.techpanda.org/index.php/");

        //2. Login
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailaddress);
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        sleepInSeconds(2);

        assertion.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + fullname + "!");
        // Do việc lấy thông tin firstname, lastname và email đang bị lặp lại >> tạo biến để sử dụng
        //biến infor ở đây được gọi lại vì bên trên đã khai báo biến này rồi.
        // Nhưng ở đây là getText của màn login thành công chứ không phải của màn Register thành công
        infor = driver.findElement(By.xpath("//h3[text()='Contact Information']//parent::div/following-sibling::div//p")).getText();
        //Chỗ này lấy verify tương đối >> dùng assertTrue() với hàm contains()
        assertion.assertTrue(infor.contains(fullname));
        assertion.assertTrue(infor.contains(emailaddress));

        //3. Verify
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSeconds(2);

        // Để verify giá trị firstname, lastname và email >> dùng hàm getAttribute
        assertion.assertEquals(driver.findElement(By.xpath("//input[@id='firstname']")).getAttribute("value"),firstname);
        assertion.assertEquals(driver.findElement(By.xpath("//input[@id='lastname']")).getAttribute("value"),lastname);
        assertion.assertEquals(driver.findElement(By.xpath("//input[@id='email']")).getAttribute("value"),emailaddress);

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
        return "nguyenuyen" + random.nextInt(99999) + "@gmail.com";
    }
}
