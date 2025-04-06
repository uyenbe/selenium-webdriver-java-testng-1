package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.Color;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_12_Handle_Checkbox_Radiobutton {
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
    public void TC_01_Default_Checkbox() {
        // Nhận biết checkbox/radio button là defaul: thẻ input hiển thị (khi inspect checkbox/radio button >> select vào thẻ input luôn)
        // thẻ input bị ẩn đi >> là dạng custom

        // Cách để handle checkbox và verify:
        //1. Click vào checkbox/ radio button
        // 2. Verify xem checkbox/ radio button enable hay disable

        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        //1. verify checkbox có 2 case:
        // Case 1: Checkbox default đã được chọn
        // Case 2: Checkbox default chưa được chọn
        // >> dùng hàm if để control 2 case này
        // notes: khi element có đánh index >> không nên dùng mà nên dựa vào text/title/label
        Assertion assertion = new Assertion();

        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span//input");
        By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span//input");

        //2. verify checkbox sau khi được click

        if (!driver.findElement(rearSideCheckbox ).isSelected()){
            driver.findElement(rearSideCheckbox ).click();
            sleepInSeconds(2);
        }


        if (!driver.findElement(dualZoneCheckbox).isSelected()){
            driver.findElement(dualZoneCheckbox).click();
            sleepInSeconds(2);
        }
        //verify sau khi chọn
        assertion.assertTrue(driver.findElement(rearSideCheckbox).isSelected());
        assertion.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        //3. verify chekbox sau khi bỏ chọn
        if (driver.findElement(dualZoneCheckbox).isSelected()){
            driver.findElement(dualZoneCheckbox).click();
            sleepInSeconds(2);
        }
        assertion.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());



    }
    @Test
    public void TC_02_RadioButton_Default(){
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By petrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span//input");
        By dieselRadio = By.xpath("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::span//input");

        // radio button: không bỏ chọn được mà chỉ có thể chọn sang radio khác để bỏ chọn
        // Check 2 case tương  tự như checkbox nhưng là chọn 1 trong 2
        if (!driver.findElement(petrolRadio).isSelected()){
            driver.findElement(petrolRadio).click();
            sleepInSeconds(3);
        }
        Assertion assertion = new Assertion();
        assertion.assertTrue(driver.findElement(petrolRadio).isSelected());
        assertion.assertFalse(driver.findElement(dieselRadio).isSelected()); // 1 được chọn thì 2 sẽ ko được chọn

        if (!driver.findElement(dieselRadio).isSelected()){
            // driver.findElement(dieselRadio).isSelected() >> được chọn nên dùng ! sẽ là phủ định

            driver.findElement(dieselRadio).click();
            sleepInSeconds(3);
        }
        assertion.assertFalse(driver.findElement(petrolRadio).isSelected());
        assertion.assertTrue(driver.findElement(dieselRadio).isSelected());

    }

    @Test
    public void TC_03_SelectAllCheckbox_Or_1InAll(){
        driver.get("https://automationfc.github.io/multiple-fields/");

        // Select all checkbox: Dùng hàm List element để gán với all checkbox trong màn hình
        // Sau đó dùng vòng lặp for để duyệt : khai báo biến tạm: checkbox để duyệt
        // Dùng thêm câu điều kiện if
        List<WebElement> allCheckboxes = driver.findElements(By.xpath("//div[@class='form-single-column']//input[@type='checkbox']"));
        Assertion assertion = new Assertion();
        for (WebElement checkbox: allCheckboxes){
           if (!checkbox.isSelected()){
               checkbox.click();
              // sleepInSeconds(2);
           }

       }
       //Verify all: Dùng vòng lặp for
        for (WebElement checkbox : allCheckboxes){
            assertion.assertTrue(checkbox.isSelected());
        }

        // Xoá tất cả cookies của trang
        driver.manage().deleteAllCookies();

        // thực hiện refresh lại trang
        driver.navigate().refresh();
        sleepInSeconds(1);

        // Select 1 in allcheckbox >> Tại thẻ input: dùng hàm get<Attribute> để lấy ra giá trị của checkbox cần check
        // Sau đó dùng vòng lặp và điều kiện để check tiếp
        // Cần phải gán lại biến allcheckboxes bởi vì sau khi refresh lại page
        // >> các checkbox đã không còn nằm trong list allcheckboxes
        // >> phải gọi lại list checkbox
        allCheckboxes = driver.findElements(By.xpath("//div[@class='form-single-column']//input[@type='checkbox']"));
        for (WebElement checkbox: allCheckboxes){
            if (checkbox.getAttribute("value").equals("Asthma") && !checkbox.isSelected()){
                checkbox.click();
                sleepInSeconds(1);
            }
        }

        // Verify
        for (WebElement checkbox: allCheckboxes){
            if (checkbox.getAttribute("value").equals("Asthma")){
                assertion.assertTrue(checkbox.isSelected());
            }else {
                assertion.assertFalse(checkbox.isSelected());
            }
        }

    }

    @Test
    public void TC_04_Custom_Checkbox_RadioButton(){
        driver.get("https://login.ubuntu.com/");
        // Case 1: dùng thẻ input để click >> thẻ input bị che nên ko click được >> FALSE

        //Case 2: dùng thẻ div/ 1 thẻ bên ngoài để click >> PASS
        // Dùng thẻ được click để verify >> FALSE bởi vì isSelected chỉ dùng cho thẻ input thôi

        //Case 3: dùng thẻ div/ thẻ bên ngoài để click >> PASS
        // DÙng thẻ input để verify với isSelected >> PASS
        // Nhưng chưa tối ưu, vì phải xách định 2 locator khác nhau cho 1 element >> sau này khi mantain sẽ mất nhiều time hơn
        //        By radioButton = By.xpath("//label[@class='new-user']");
        //        Assertion assertion = new Assertion();
        //        if (!driver.findElement(radioButton).isSelected())
        //        {
        //            driver.findElement(radioButton).click();
        //            sleepInSeconds(1);
        //        }
        //        assertion.assertTrue(driver.findElement(By.xpath("//label[@class='new-user']/preceding-sibling::input")).isSelected());
        //
        //        By checkBox = By.xpath("//div[contains(@class, 'accept-tos-input')]");
        //        if (!driver.findElement(checkBox).isSelected()){
        //            driver.findElement(checkBox).click();
        //        }
        //        assertion.assertFalse(driver.findElement(By.xpath("//div[contains(@class, 'accept-tos-input')]//input[@type='checkbox']")).isSelected());
        //Case 4: Dùng thẻ input để click, nhưng vì thẻ input đang bị ẩn >> dùng click của webElement nó sẽ không dùng được
        // Dùng JavascripExecutor (JS) để click >> chỉ cần xác định 1 locator cho element thôi
        // Dùng thẻ input để verify >> assertion với isSelected như bình thường
        // driver: kiểu interface webdriver
        // JavascriptExecutor: interface javascrip executor
        // >> Ép kiểu interface này sang kiểu interfae khác

        // Cách 1: Không khai báo biến mà ép kiểu driver xong gọi hàm luôn
       // ((JavascriptExecutor)driver).executeAsyncScript("");

        // Cách 2: Khai báo biến

        By radioButton = By.xpath("//label[@class='new-user']/preceding-sibling::input");
        JavascriptExecutor js = (JavascriptExecutor)driver;

        //Trong đó: arguments [0]: là tham số truyền vào, ở đây chính là cái element dùng để click, và truyền từ vị trí số 0
        // element dùng để click >> được define ở sau dấu ,

        Assertion assertion = new Assertion();
        if (!driver.findElement(radioButton).isSelected())
        {
            js.executeScript("arguments[0].click();",driver.findElement(radioButton));
            sleepInSeconds(1);
        }
        assertion.assertTrue(driver.findElement(radioButton).isSelected());

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//span[text()='Create account']")));
        actions.perform();

        By checkBox = By.xpath("//label[@for='id_accept_tos']/preceding-sibling::input");

        if (!driver.findElement(checkBox).isSelected()){
            js.executeScript("arguments[0].click();",driver.findElement(checkBox));
            sleepInSeconds(1);
        }
        assertion.assertTrue(driver.findElement(checkBox).isSelected());

    }

    @Test
    public void TC_05_Custom_RadioButton() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        By radioCanTho = By.xpath("//div[@aria-label='Cần Thơ']");
        By checkboxQuangNam = By.xpath("//div[@aria-label='Quảng Nam']");

        //verify radio chưa selected
        Assertion assertion = new Assertion();
        // Verify radio chưa click
        // Cách 1
        assertion.assertEquals(driver.findElement(radioCanTho).getAttribute("aria-checked"), "false");
        assertion.assertEquals(driver.findElement(checkboxQuangNam).getAttribute("aria-checked"), "false");

        // Cách 2: thêm luôn attribute vào xpath, dùng isDisplayed để verify
        assertion.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked = 'false']")).isDisplayed());

        driver.findElement(radioCanTho).click();
        driver.findElement(checkboxQuangNam).click();

        //Verify radio sau khi click
        assertion.assertEquals(driver.findElement(radioCanTho).getAttribute("aria-checked"), "true");
        assertion.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked = 'true']")).isDisplayed());

        assertion.assertEquals(driver.findElement(checkboxQuangNam).getAttribute("aria-checked"), "true");
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
