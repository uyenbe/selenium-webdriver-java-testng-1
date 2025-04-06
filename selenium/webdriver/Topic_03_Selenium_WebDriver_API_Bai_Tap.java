package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.concurrent.TimeUnit;

public class Topic_03_Selenium_WebDriver_API_Bai_Tap {
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
    public void TC_01_Browser_URL() {
        //Mở URL cần tìm
    driver.get("http://live.techpanda.org/");
    // Click My Account ở footer
    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
    //sleep action
    sleepInSeconds(3);
    //Lấy ra URL hiện tại của web sau khi click My Account
    String url = driver.getCurrentUrl();
    //Khai báo hàm assert để thực hiện verify
    Assertion asserts = new Assertion();
    //Verify URL
    asserts.assertEquals(url,"http://live.techpanda.org/index.php/customer/account/login/");
    //Click btn Resigter
    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
    //Lấy ra URL hiện tại
    String urlResigter = driver.getCurrentUrl();
    //Verify URL
    asserts.assertEquals(urlResigter,"http://live.techpanda.org/index.php/customer/account/create/");

    }
    @Test
    public void TC_02_Browser_Title() {
        //Mở web cần thao tác
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        String title = driver.getTitle();
        Assertion asserts = new Assertion();
        asserts.assertEquals(title,"Customer Login");
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        String titlePage = driver.getTitle();
        asserts.assertEquals(titlePage,"Create New Customer Account");


    }
    @Test
    public void TC_03_Browser_Navigate() {
        //Mở URL cần tìm
        driver.get("http://live.techpanda.org/");
        // Click My Account ở footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        //Click btn Resigter
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        //Lấy ra URL hiện tại
       // String urlResigter = driver.getCurrentUrl();
        driver.getCurrentUrl();
        //Khai báo hàm assert để thực hiện verify
        Assertion asserts = new Assertion();
        //Verify URL
      //  asserts.assertEquals(urlResigter,"http://live.techpanda.org/index.php/customer/account/create/");
        asserts.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
       // WebDriver.Navigation nav = driver.navigate();
        driver.navigate().back();
        sleepInSeconds(2);
        //nav.back();
        driver.getCurrentUrl();
       // String backUrl = driver.getCurrentUrl();
       // asserts.assertEquals(backUrl,"http://live.techpanda.org/index.php/customer/account/login/");
        asserts.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        //nav.forward();
        driver.navigate().forward();
        sleepInSeconds(2);
       // String titleForward = driver.getTitle();
        driver.getTitle();
       // asserts.assertEquals(titleForward,"Create New Customer Account");
        asserts.assertEquals(driver.getTitle(),"Create New Customer Account");


    }

    @Test
    public void TC_04_Browser_Page_Source() {
        //Mở URL cần tìm
        driver.get("http://live.techpanda.org/");
        // Click My Account ở footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
       WebElement element = driver.findElement(By.xpath("//h1[contains(text(),'Login or Create an Account')]"));
       String text = element.getText();
        Assertion ass = new Assertion();
        ass.assertEquals(text,"LOGIN OR CREATE AN ACCOUNT");
       driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
       WebElement element1 = driver.findElement(By.xpath("//h1[text()='Create an Account']"));
       String text1 = element1.getText();
       ass.assertEquals(text1,"CREATE AN ACCOUNT");

    }

    @Test
    public void TC_01_Elemeent_isDisplayed(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement element1 = driver.findElement(By.xpath("//input[@id='mail']"));
        if (element1.isDisplayed()){
            element1.sendKeys("Automation Testing");
            System.out.println("Email is displayed");
        }else {
            System.out.println("Email is not displayed");
        };

        WebElement element2 = driver.findElement(By.xpath("//input[@id='under_18']"));
        if (element2.isDisplayed()){
            element2.click();
            System.out.println("Under 18 is displayed");
        } else {
            System.out.println("Under 18 is not displayed");
        };

        WebElement element3 = driver.findElement(By.xpath("//textarea[@id='edu']"));
        if (element3.isDisplayed()){
            element3.sendKeys("Automation Testing");
            System.out.println("Education is displayed");
        }else {
            System.out.println("Education is displayed");
        };

        WebElement element4 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        if (element4.isDisplayed()){
            System.out.println("User 5 is displayed");
        }else {
            System.out.println("User 5 is not displayed");
        };

        //Cách 2
       // WebElement element1 = driver.findElement(By.xpath("//label[@for='mail']"));
       // WebElement element2 = driver.findElement(By.xpath("//label[@for='under_18']"));
       // WebElement element3 = driver.findElement(By.xpath("//label[@for='edu']"));
//        WebElement element4 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
//        Assertion assertion = new Assertion() ;
//        assertion.assertTrue(element1.isDisplayed());
//        driver.findElement(By.xpath("//input[@name='user_email']")).sendKeys("Automation Testing");
//        System.out.println("Element is displayed");
//
//        assertion.assertTrue(element2.isDisplayed());
//        element2.click();
//      // element2.sendKeys("Element is displayed");
//        assertion.assertTrue(element3.isDisplayed());
//        driver.findElement(By.xpath("//textarea[@name='user_edu']")).sendKeys("Automation Testing");
//       // System.out.println("Element is displayed");
//        assertion.assertFalse(element4.isDisplayed());


    }

    @Test
    public void TC_02_Element_isEnable(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement element1 = driver.findElement(By.xpath("//input[@id='mail']"));
        if (element1.isDisplayed()){
            System.out.println("Email is Enable");
        }else {
            System.out.println("Email is Disable");
        };

        WebElement element2 = driver.findElement(By.xpath("//input[@id='under_18']"));
        if (element2.isDisplayed()){
            element2.click();
            System.out.println("Under 18 is displayed");
        } else {
            System.out.println("Under 18 is not displayed");
        };

        WebElement element3 = driver.findElement(By.xpath("//textarea[@id='edu']"));
        if (element3.isDisplayed()){
            element3.sendKeys("Automation Testing");
            System.out.println("Education is displayed");
        }else {
            System.out.println("Education is displayed");
        };

        WebElement element4 = driver.findElement(By.xpath("//label[@for='password']"));
        if (element4.isEnabled()){
            System.out.println("Password is Enable");
        }else {
            System.out.println("Password is Disable");
        };
    }
@Test
public void TC_03_Element_isSelected(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//input[@id='under_18']")).click();
        driver.findElement(By.xpath("//input[@id='java']")).click();

        Assertion asserts = new Assertion() ;
        asserts.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isSelected());
        asserts.assertTrue(driver.findElement(By.xpath("//input[@id='java']")).isSelected());

        driver.findElement(By.xpath("//input[@id='java']")).click();
        sleepInSeconds(2);

        asserts.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isSelected());
        asserts.assertFalse(driver.findElement(By.xpath("//input[@id='java']")).isSelected());

};

@Test
public void TC_04_Element_MailChimp (){
    driver.get("https://login.mailchimp.com/signup/");
    //Nhập text là email
    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("uyennt7@gmail.com");
    Assertion assertion = new Assertion();

    // Case 0: Bỏ trống tất cả/ Empty data
    //driver.findElement(By.xpath("//input[@id='new_password']")).clear(); // clear data của lần nhập trước đó
    try{
        driver.findElement(By.xpath("//button[@id='create-account-enabled']")).click();
    }catch(Exception e){
        driver.findElement(By.xpath("//button[@class='onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon']")).click();
        sleepInSeconds(1);
        driver.findElement(By.xpath("//button[@id='create-account-enabled']")).click();
    }
    //gọi hàm Sleep để check data sau khi nhập
    sleepInSeconds(2);
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='username-check not-completed']")).isDisplayed());

    // Case 1: Nhập number
    driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("123");

    //gọi hàm Sleep để check data sau khi nhập
    sleepInSeconds(2);

    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

    // Case 2: Nhập chữ thường Lower Character
    driver.findElement(By.xpath("//input[@id='new_password']")).clear(); // clear data của lần nhập trước đó
    driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("nguyen");//gọi hàm Sleep để check data sau khi nhập
    //gọi hàm Sleep để check data sau khi nhập
    sleepInSeconds(2);

    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

    // Case 3: Nhập chữ hoa Upper character
    driver.findElement(By.xpath("//input[@id='new_password']")).clear(); // clear data của lần nhập trước đó
    driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("NGUYEN");
    //gọi hàm Sleep để check data sau khi nhập
    sleepInSeconds(2);

    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

    // Case 4: Nhập ký tự đặc biệt/ Special Character
    driver.findElement(By.xpath("//input[@id='new_password']")).clear(); // clear data của lần nhập trước đó
    driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("@#$%");
    //gọi hàm Sleep để check data sau khi nhập
    sleepInSeconds(2);
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

    // Case 5: Nhập >8 ký tự
    driver.findElement(By.xpath("//input[@id='new_password']")).clear(); // clear data của lần nhập trước đó
    driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("nguyenthiuyen");
    //gọi hàm Sleep để check data sau khi nhập
    sleepInSeconds(2);
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

 //Case 7: Bỏ trống tất cả/ Empty data
    driver.findElement(By.xpath("//input[@id='new_password']")).clear(); // clear data của lần nhập trước đó
    driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("c");
    driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys(Keys.BACK_SPACE);
    driver.findElement(By.xpath("//button[@id='create-account-enabled']")).click();
    //gọi hàm Sleep để check data sau khi nhập
    sleepInSeconds(2);
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
    assertion.assertTrue(driver.findElement(By.xpath("//li[@class='username-check not-completed']")).isDisplayed());

    // Case 6: Nhập hợp lệ/ Valid data
    driver.findElement(By.xpath("//input[@id='new_password']")).clear(); // clear data của lần nhập trước đó
    driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("Hh@123456");
    //gọi hàm Sleep để check data sau khi nhập
    sleepInSeconds(2);
    assertion.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
    assertion.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
    assertion.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
    assertion.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
    assertion.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
    assertion.assertFalse(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());
};

@Test
    public void TC_01_Login_EmptyEmailPass (){
    driver.get("http://live.techpanda.org/");
    Assertion asserts = new Assertion();
    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
    sleepInSeconds(1);
    driver.findElement(By.xpath("//button[@title='Login']")).click();
    sleepInSeconds(1);
    asserts.assertTrue(driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email validation-failed']")).isDisplayed());
    asserts.assertTrue(driver.findElement(By.xpath("//input[@class='input-text required-entry validate-password validation-failed']")).isDisplayed());
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
    asserts.assertTrue(driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email validation-failed']")).isDisplayed());
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
    asserts.assertTrue(driver.findElement(By.xpath("//input[@class='input-text required-entry validate-password validation-failed']")).isDisplayed());
};

@Test
public void TC_04_Incorrect_Email(){
    driver.get("http://live.techpanda.org/");
    Assertion asserts = new Assertion();
    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
    sleepInSeconds(1);
    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("uyen@gmail");
    driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
    driver.findElement(By.xpath("//button[@title='Login']")).click();
    asserts.assertTrue(driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email validation-failed']")).isDisplayed());
}

@Test
public void TC_04_1_Incorrect_Pass(){
    driver.get("http://live.techpanda.org/");
    Assertion asserts = new Assertion();
    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
    sleepInSeconds(1);
    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("uyen@gmail.com");
    driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("1234");
    driver.findElement(By.xpath("//button[@title='Login']")).click();
    asserts.assertTrue(driver.findElement(By.xpath("//input[@class='input-text required-entry validate-password validation-failed']")).isDisplayed());
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
}
