package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_25_JsExecutor_I {
    private static final Logger log = LoggerFactory.getLogger(Topic_25_JsExecutor_I.class);
    WebDriver driver;
    Actions actions;
    JavascriptExecutor jsExecutor;
    Random random;
    String email;

    WebDriverWait explicitWait;
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
        actions = new Actions(driver);
        // nên khởi tạo sau khởi tạo driver, vì là interface nên không có new khi khởi tạo
        // Đây là ép kiểu tường minh
        jsExecutor = (JavascriptExecutor) driver;

        // Khởi tạo email
        email = "automation" + new Random().nextInt(10000) + "@gmail.com";


        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_JSExecutor_I() {
        //jsExecutor nằm trong package của selenium, Nên sử dụng trong TH các hàm trong thư viện selenium không có sẵn thì dùng jsExecutor
        // Không nên lạm dụng jsExecutor
        // jsExecutor không query được Xpath, chỉ quy được Css
       // driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        //Lấy ra 1 web Element/domain/title bằng JSExecutor:
        //B1. Khai báo thư viện JSExecutor
        //B2. Khởi tạo biện jsexecutor
        //B3. Dùng biến vừa khai báo để lấy ra web element

        //1. Lấy ra domain
//        System.out.println(jsExecutor.executeScript("return document.domain"));
//
//        //2. Lấy ra 1 web element và sendkey
//       WebElement emailTextbox = (WebElement) jsExecutor.executeScript("return document.querySelector('input#email')");
//       emailTextbox.sendKeys("automation");
//
//       //3. Lấy ra tất cả web element
//        List<WebElement> emailType = (List<WebElement>) jsExecutor.executeScript("return document.querySelectorAll(\"input[type='email'\");");

        //4. Click vào 1 element mà đang bị ẩn/che bằng jsExecutor >> TH này nếu dùng WebElement click() thì sẽ bị lỗi
        driver.get("https://demo.nopcommerce.com/");
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("ul.top-menu.notmobile a[href='/desktops']")));


    }

    @Test
    public void TC_02_TechPanda() {
        //1. Open URL
        jsExecutor.executeScript("window.location = 'http://live.techpanda.org/'");
        sleepInSeconds(1);

        //2. Get domain of page
        //jsExecutor.executeScript(" return document.domain");
        String techPandaDomain = (String) jsExecutor.executeScript("return document.domain");
        Assertion assertion = new Assertion();
        assertion.assertEquals(techPandaDomain, "live.techpanda.org");

        //3. Get URL
        //jsExecutor.executeScript("return document.URL");
        String techPandaUrl = (String) jsExecutor.executeScript("return document.URL");
        assertion.assertEquals(techPandaUrl, "https://live.techpanda.org/");

        //4. Open mobile page
        WebElement mobileTab = driver.findElement(By.xpath("//a[text()='Mobile']"));
        jsExecutor.executeScript("arguments[0].click();", mobileTab);
        sleepInSeconds(1);

        //5. Add sản phẩm Samsung Galaxy vào giỏ hàng
        WebElement samSungGalaxy = driver.findElement(
                By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button"));
        jsExecutor.executeScript("arguments[0].click();", samSungGalaxy);
        sleepInSeconds(1);

        //6. Verify message hiển thị
        String samSungText = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        assertion.assertTrue(samSungText.contains("Samsung Galaxy was added to your shopping cart."));

        //7. Open Customer Service page
        WebElement customerPage = driver.findElement(By.xpath("//a[text()='Customer Service']"));
        jsExecutor.executeScript("arguments[0].click();", customerPage);
        sleepInSeconds(2);

        String customerTitle = (String) jsExecutor.executeScript("return document.title;");
        assertion.assertEquals(customerTitle, "Customer Service");

        //8. Scroll tới element NewLetter textbox
        WebElement newletter = driver.findElement(By.cssSelector("input#newsletter"));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true)", newletter);
        sleepInSeconds(2);

        //9. Nhập email vào và click đăng ký
        // Email để đăng ký được phải là random >> khai báo biến email và random sau đó khởi tạo
        // Dùng js để sendkey
        WebElement emailRegistor = driver.findElement(By.cssSelector("input#newsletter"));
        jsExecutor.executeScript("arguments[0].setAttribute('value', '"+ email + "')",emailRegistor);
        sleepInSeconds(2);

        //Click button Subcribe
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(
                By.xpath("//button[@title='Subscribe']")));
        sleepInSeconds(2);

        //verify message
        String successMessage = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        assertion.assertTrue(successMessage.contains("Thank you for your subscription."));

        //10. navigate với domain FB
        jsExecutor.executeScript("window.location ='https://www.facebook.com/'");
        sleepInSeconds(2);

    }

    @Test
    public void TC_03_TechPanda_() {
        navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSeconds(1);

        String pageURL = getPageURL();
        Assertion assertion = new Assertion();
        assertion.assertEquals(pageURL, "https://live.techpanda.org/");

        String domainPage = getDomain();
        assertion.assertEquals(domainPage, "live.techpanda.org");

        clickToElementByJS("//a[text()='Mobile']");
        sleepInSecond(1);

        clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");
        sleepInSecond(1);

        assertion.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

        clickToElementByJS("//a[text()='Customer Service']");
        scrollToElementOnTop("//input[@id='newsletter']");

        setAttributeInDOM("//input[@id='newsletter']","value", email);
        clickToElementByJS("//button[@title='Subscribe']");
        assertion.assertTrue(getInnerText().contains("Thank you for your subscription."));

    }

    @Test
    public void TC_03_HTML5_Message() {
        driver.get("https://warranty.rode.com/");
        sleepInSeconds(1);

        //driver.findElement(By.cssSelector("input#email")).sendKeys("input#email");
        WebElement loginButton = driver.findElement(By.cssSelector("button[type = 'submit']"));

        //Case 1. Empty
        loginButton.click();
        String emptyEmail = getElementValidationMessage("//input[@id='email']");
        Assertion assertion = new Assertion();
        assertion.assertEquals(emptyEmail, "Please fill out this field.");

        //Case 2. Invalid Email
        String invalidEmailData = "aaa";
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginButton.click();
        sleepInSeconds(2);

        String invalidEmailMessage = getElementValidationMessage("//input[@id='email']");

        if (driver.toString().contains("ChromeDriver")){
            assertion.assertEquals(invalidEmailMessage,
                    "Please include an '@' in the email address. '"+ invalidEmailData +"' is missing an '@'.");
        }else {
            assertion.assertEquals(invalidEmailMessage, "Please enter an email address.");
        }

        invalidEmailData = "bbb@";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginButton.click();
        sleepInSeconds(2);

        invalidEmailMessage = getElementValidationMessage("//input[@id='email']");

        if (driver.toString().contains("ChromeDriver")){
            assertion.assertEquals(invalidEmailMessage,"Please enter a part following '@'. '"+invalidEmailData+"' is incomplete.");
        }else {
            assertion.assertEquals(invalidEmailMessage,"Please enter an email address.");
        }

        //Case 3. Valid email vì ở 2 trình duyệt trả cùng message >> không cần dùng hàm if
//        if (driver.toString().contains("ChromeDriver")){
//            assertion.assertEquals(email,"Please fill out this field.");
//        } else {
//            assertion.assertEquals(email,"Please enter an email address.");
//        }
        driver.findElement(By.xpath("//input[@id='email']")).clear();

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);

        loginButton.click();
        sleepInSeconds(2);

        invalidEmailMessage = getElementValidationMessage("//input[@id='password']");

        assertion.assertEquals(invalidEmailMessage, "Please fill out this field.");


    }


    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public String getPageURL (){
        return  (String) jsExecutor.executeScript("return document.URL;");
    }

    public String getDomain (){
        return  (String) jsExecutor.executeScript("return document.domain;");
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }


    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
