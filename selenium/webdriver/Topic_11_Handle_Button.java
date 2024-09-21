package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Topic_11_Handle_Button {
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
    public void TC_01_Example_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
        WebElement loginButton = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
        // Verify button bị disable
        Assertion assertion = new Assertion();
        assertion.assertFalse(loginButton.isEnabled());

        // Verify button được enable: Nhập đủ các trường thông tin
        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("0987654321");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("12345678");
        assertion.assertTrue(loginButton.isEnabled());

        // Lấy ra mã background của btn
        // Vì selenium lấy ra màu theo kiểu RGB
        // Mà trên FE đang để kiểu màu là Hexa >> cần convert lại mã màu
        //1. Dùng hàm String để lấy mã màu btn theo kiểu RGB
//        String loginButtonBackgroundRGBA = loginButton.getCssValue("background-color");
//        System.out.println("Background RGBA là: "+ loginButtonBackgroundRGBA);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        var bg = js.executeScript("""
                    var element = document.querySelector('.fhs-btn-login');  // Replace with your CSS selector
                    if (!element) return null;
                    var computedStyle = window.getComputedStyle(element);
                    var background = computedStyle.getPropertyValue('background-color');
                    
                    return background;
                """);
        if (bg != null) {
            if (bg instanceof String) {

                String loginButtonBackgroundRGBA = bg.toString();
//                2. Convert mã màu kiểu String (mã RGB) >> kiểu Color (mã RGB) mà selenium hỗ trợ (library of selenium)
//                 để sau đó dùng kiểu Color convert RGB > Hexa ( vì hàm String no support convert RGB to Hexa)
                Color loginButtonBackgroundColor = Color.fromString(loginButtonBackgroundRGBA);


//                 3. Convert mã màu từ kiểu Color (mã RGB) sang String (mã Hexa)
//                 loginButtonBackgroundHexa = loginButtonBackgroundColor.asHex();
                String loginButtonBackgroundHexa = Color.fromString(loginButtonBackgroundRGBA).asHex();

                System.out.println("Background Hexa là: " + loginButtonBackgroundHexa);

//                 4. Verify mã màu
                assertion.assertEquals(loginButtonBackgroundHexa.toUpperCase(), "#C92127");
            }
            System.out.println("");
//            for property_name, property_value in element_data['css'].items():
        } else {
            System.out.println("Element not found");
        }


    }

    @Test
    public void TC_02_Fahasa_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
        WebElement loginButton = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
        // Verify button bị disable
        Assertion assertion = new Assertion();
        assertion.assertFalse(loginButton.isEnabled());

        // Verify button được enable: Nhập đủ các trường thông tin
        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("0987654321");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("12345678");
        assertion.assertTrue(loginButton.isEnabled());

        // Lấy ra mã background của btn
        // Vì selenium lấy ra màu theo kiểu RGB
        // Mà trên FE đang để kiểu màu là Hexa >> cần convert lại mã màu
        //1. Dùng hàm String để lấy mã màu btn theo kiểu RGB
        String loginButtonBackgroundRGBA = loginButton.getCssValue("background-color");
        System.out.println("Background RGBA là: "+ loginButtonBackgroundRGBA);
        //2. Convert mã màu kiểu String (mã RGB) >> kiểu Color (mã RGB) mà selenium hỗ trợ (library of selenium)
        // để sau đó dùng kiểu Color convert RGB > Hexa ( vì hàm String no support convert RGB to Hexa)
       // Color loginButtonBackgroundColor = Color.fromString(loginButtonBackgroundRGBA);

        //3. Convert mã màu từ kiểu Color (mã RGB) sang String (mã Hexa)
        // loginButtonBackgroundHexa = loginButtonBackgroundColor.asHex();
        String loginButtonBackgroundHexa = Color.fromString(loginButtonBackgroundRGBA).asHex();
        System.out.println("Background Hexa là: " + loginButtonBackgroundHexa.toUpperCase());

        //4. Verify mã màu
        assertion.assertEquals(loginButtonBackgroundHexa.toUpperCase(), "#C92127");

        // Cách viết gộp
        //assertion.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toUpperCase(),"#C92127");



    }
    @Test
    public void TC_02_1_Ego_Button(){
        driver.get("https://egov.danang.gov.vn/reg");

        //verify button disable (khi chưa click)
        Assertion assertion = new Assertion();
       assertion.assertFalse(driver.findElement(By.xpath("//input[@class = 'egov-button']")).isEnabled());

       // verify button enbale (sau khi click vào checkbox)
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        assertion.assertTrue(driver.findElement(By.xpath("//input[@class = 'egov-button']")).isEnabled());

        //verify background của button
        String registerButtonRGBBA = driver.findElement(By.xpath("//input[@class = 'egov-button']")).getCssValue("background-color");
        System.out.println("Background RGBA là: " + registerButtonRGBBA);

        String registerButtonHexa = Color.fromString(registerButtonRGBBA).asHex();
        System.out.println("Background Hexa là: " + registerButtonHexa);

        assertion.assertEquals(registerButtonHexa,"#ef5a00");



    }

    @Test
    public void TC_03_Default_Checkbox() {
        // Nhận biết checkbox/radio button là defaul: thẻ input hiển thị (khi inspect checkbox/radio button >> select vào thẻ input luôn)
        // thẻ input bị ẩn đi >> là dạng custom

        // Cách để handle checkbox và verify:
        //1. Click vào checkbox/ radio button
        // 2. Verify xem checkbox/ radio button enable hay disable

        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        //1. verify checkbox chưa được chọn
        Assertion assertion = new Assertion();
        assertion.assertFalse(driver.findElement(By.xpath("//input[@id='eq3']")).isSelected());

        //2. verify checkbox sau khi được click

        driver.findElement(By.xpath("//input[@id='eq4']")).click();
        assertion.assertTrue(driver.findElement(By.xpath("//input[@id='eq4']")).isSelected());



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
