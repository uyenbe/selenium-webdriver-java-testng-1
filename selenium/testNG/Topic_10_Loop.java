package testNG;

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

public class Topic_10_Loop {

        WebDriver driver;
        String projectPath = System.getProperty("user.dir");
        String osName = System.getProperty("os.name");
        Random random;

        // Đối với textbox thường sẽ check các TH như sau:
        //1. Clear data
        //2. Sendkey
        //Verify data: thường dùng hàm getAttribute()
        // Các thông tin như firstName, lastName hay Email
        // >> cần khai báo ở bên ngoài các TCase để dùng như biến toàn cục để dùng cho nhiều TCs
        String firstName , lastName ,emailAddress ;
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
            firstName = "Trung";
            lastName = "Bu";

            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();

        }
        //invocationCount: chỉ chạy cho @Test và chạy 3 lần,
        // còn @BeforeClass và @AfterClass thì không được chạy
        //
        @Test(invocationCount = 1)
        public void TC_01_Register (){
            driver.get("https://demo.nopcommerce.com");
            emailAddress = "TrungBu" + random.nextInt(99999) + "@gmail.com";

            driver.findElement(By.xpath("//a[@class='ico-register']")).click();

            driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);
            driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);

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
            System.out.println("Email: " + emailAddress);
            System.out.println("Password: " + password);

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

    }

