package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_XPath_4_Bai_Tap {
    WebDriver driver;
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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }
    @Test
    public void TC_01_Empty_Data() {
        //https://alada.vn/tai-khoan/dang-ky.html
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
        //Verify: dùng 3 hàm để verify:
        //1. Assert.assertTrue =>> Kiểm tra 1 điều kiện trả về là ĐÚNG
        //2. Assert.assertFalse =>> Kiểm tra 1 điều kiện trả về là SAI
        //3. Assert.assertEquals =>> Kiểm tra thực tế với mong đợi như nhau
        //3.1. Expected Result vs Actual Result sẽ dùng Assert.assertEquals
        //So sánh expect ở đây là chuỗi >> cần phải getText() thì mới so sánh được, Bởi vì driver.find... là tìm 1 webElement chứ kphai Text
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");

        //driver.findElement(By.xpath("//label[text()='Vui lòng nhập họ tên']")); KHÔNG cần dùng Xpath vì có ID
    }
    @Test
    public void TC_02_Invalid_Email() {
        //Nhập value
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Thị Uyến");
        driver.findElement(By.id("txtEmail")).sendKeys("@gmail");
        driver.findElement(By.id("txtCEmail")).sendKeys("@gmail");
        driver.findElement(By.id("txtPassword")).sendKeys("12345678");
        driver.findElement(By.id("txtCPassword")).sendKeys("12345678");
        driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
        //Click btn
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");


    }
    @Test
    public void TC_03_Incorrect_Confirm_Email() {
        //Nhập value
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Thị Uyến");
        driver.findElement(By.id("txtEmail")).sendKeys("nguyenuyen.ba@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("nguyen@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("12345678");
        driver.findElement(By.id("txtCPassword")).sendKeys("12345678");
        driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
        //Click btn
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }
    @Test
    public void TC_04_Invalid_Password() {
        //Nhập value
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Thị Uyến");
        driver.findElement(By.id("txtEmail")).sendKeys("nguyenuyen.ba@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("nguyenuyen.ba@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("1234");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234");
        driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
        //Click btn
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }
    @Test
    public void TC_05_Incorrect_Confirm_Password() {
        //Nhập value
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Thị Uyến");
        driver.findElement(By.id("txtEmail")).sendKeys("nguyenuyen.ba@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("nguyenuyen.ba@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("12345678");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234");
        driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
        //Click btn
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }
    @Test
    public void TC_06_Invalid_Phone() {
        //Nhập value
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Thị Uyến");
        driver.findElement(By.id("txtEmail")).sendKeys("nguyenuyen.ba@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("nguyenuyen.ba@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("12345678");
        driver.findElement(By.id("txtCPassword")).sendKeys("12345678");
        driver.findElement(By.id("txtPhone")).sendKeys("0287654321");
        //Click btn
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
