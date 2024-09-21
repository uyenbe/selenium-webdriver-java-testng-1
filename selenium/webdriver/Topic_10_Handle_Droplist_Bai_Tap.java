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

import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Topic_10_Handle_Droplist_Bai_Tap {
    WebDriver driver;

    WebDriverWait expliciWait; // Khai báo hàm wait tường minh
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

        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(30)); // wait tường minh

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // wait ngầm định


        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_JQuery() {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        //1. Click vào droplist
        driver.findElement(By.xpath("//span[@id='speed-button']")).click();
        sleepInSeconds(3);

        //2. Wait tất cả các phần tử trong droplist được hiển thị
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='speed-menu']//div")));

        //3. Lấy tất cả các item trong droplist vào  1 list element
        List<WebElement> allItem = driver.findElements(By.xpath("//ul[@id='speed-menu']//div"));

        //4. Dùng vòng for để duyệt qua từng phần tử sau đó getText()
        for (WebElement item : allItem) {
            String textItem = item.getText();
            System.out.println("Name of item: " + textItem);

            //5. So sánh element được getText với expected item
            if (textItem.equals("Slower")) {
                item.click();
                break;
            }

        }

        //6. Compare value item expected with item is selected in droplist
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(),
                "Slower");

        // Doing with difference dropdown: Select a file
        driver.findElement(By.xpath("//span[@id='files-button']")).click();
        sleepInSeconds(3);
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='files-menu']//div")));
        List<WebElement> allItem1 = driver.findElements(By.xpath("//ul[@id='files-menu']//div"));
        for (WebElement item1 : allItem1) {
            String textItem1 = item1.getText();
            System.out.println("Name of item1: " + item1);

            if (textItem1.equals("Some unknown file")) {
                item1.click();
                break;
            }
        }

        assertion.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']//span[@class='ui-selectmenu-text']")).getText(), "Some unknown file");

        //Droplist: Select a number
        driver.findElement(By.xpath("//span[@id='number-button']")).click();
        sleepInSeconds(3);
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='number-menu']//div")));
        List<WebElement> allItem2 = driver.findElements(By.xpath("//ul[@id='number-menu']//div"));
        for (WebElement item2 : allItem2) {
            String textItem2 = item2.getText();
            System.out.println("Name of item2: " + item2);

            if (textItem2.equals("9")) {
                item2.click();
                break;
            }
        }

        assertion.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(), "9");

        // Droplist: Select a title
        driver.findElement(By.xpath("//span[@id='salutation-button']")).click();
        sleepInSeconds(3);
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='salutation-menu']//div")));
        List<WebElement> allItem3 = driver.findElements(By.xpath("//ul[@id='salutation-menu']//div"));
        for (WebElement item3 : allItem3) {
            String textItem3 = item3.getText();
            System.out.println("Name of item3: " + textItem3);
            if (textItem3.equals("Other")) {
                item3.click();
                break;
            }
        }
        assertion.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']//span[@class='ui-selectmenu-text']")).getText(), "Other");


    }

    @Test
    public void TC_02_ReactJs() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        driver.findElement(By.xpath("//i[@class='dropdown icon']")).click();
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='item']//span")));
        List<WebElement> allItem = driver.findElements(By.xpath("//div[@class='item']//span"));
        for (WebElement item : allItem) {
            String textItem = item.getText();
            System.out.println("Name of item: " + textItem);
            if (textItem.equals("Stevie Feliciano")) {
                item.click();
                break;
            }
        }
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Stevie Feliciano");

        // làm với giá trị khác
        driver.findElement(By.xpath("//i[@class='dropdown icon']")).click();
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='item']//span")));
        // List<WebElement> allItem = driver.findElements(By.xpath("//div[@class='item']//span"));
        for (WebElement item : allItem) {
            String textItem = item.getText();
            System.out.println("Name of item: " + textItem);
            if (textItem.equals("Christian")) {
                item.click();
                break;
            }
        }
        assertion.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Christian");


    }

    @Test
    public void TC_03_VueJs() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).click();
        sleepInSeconds(2);
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='dropdown-menu']//a")));
        List<WebElement> allItem = driver.findElements(By.xpath("//ul[@class='dropdown-menu']//a"));
        for (WebElement item : allItem) {
            String itemText = item.getText();
            if (itemText.equals("Third Option")) {
                item.click();
                sleepInSeconds(2);
                break;
            }
        }

        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");

        // different value
        driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).click();
        sleepInSeconds(2);
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='dropdown-menu']//a")));
        for (WebElement item1 : allItem) {
            String item1Text = item1.getText();
            if (item1Text.equals("Second Option")) {
                item1.click();
                break;
            }
        }
        assertion.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");


    }

@Test
public void TC_04_1_Editable(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        driver.findElement(By.xpath("//input[@class='search']")).clear();
        driver.findElement(By.xpath("//input[@class='search']")).sendKeys("American Samoa");
        List<WebElement> allItem = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='selected item']//span")));
        for (WebElement item: allItem){
            String textItem = item.getText();
            System.out.println("Name of item: " + textItem);

            if (textItem.equals("American Samoa")){
                item.click();
                break;
            }

        }

        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"American Samoa");
}

@Test
public void TC_05_NoCommers() {
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")).click();
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//select[@name='DateOfBirthDay']//option")));
        List<WebElement> allItem = driver.findElements(By.xpath("//select[@name='DateOfBirthDay']//option"));
        for (WebElement item: allItem){
            String textItem = item.getText();
            System.out.println("Name of item: "+textItem);

            if (textItem.equals("18")){
                item.click();
                break;
            }
        }
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']//option[@value='18']")).getText(),"18");
//Month
    driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")).click();
    expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//select[@name='DateOfBirthMonth']//option")));
    List<WebElement> allItem1 = driver.findElements(By.xpath("//select[@name='DateOfBirthMonth']//option"));
    for (WebElement item: allItem1){
        String textItem = item.getText();
        System.out.println("Name of item: "+textItem);

        if (textItem.equals("January")){
            item.click();
            break;
        }
    }
    //Assertion assertion1 = new Assertion();
    assertion.assertEquals(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']//option[@value='1']")).getText(),"January");

// Year
    driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")).click();
    expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//select[@name='DateOfBirthYear']//option")));
    List<WebElement> allItem2 = driver.findElements(By.xpath("//select[@name='DateOfBirthYear']//option"));
    for (WebElement item: allItem2){
        String textItem = item.getText();
        System.out.println("Name of item: "+textItem);

        if (textItem.equals("1997")){
            item.click();
            break;
        }
    }
    //Assertion assertion = new Assertion();
    assertion.assertEquals(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']//option[@value='1997']")).getText(),"1997");

}

@Test
public void TC_05_1(){
        driver.get("https://demo.nopcommerce.com/register");
    driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")).click();
    List<WebElement> allItem = expliciWait.until(ExpectedConditions.
            presenceOfAllElementsLocatedBy(By.xpath("//select[@name='DateOfBirthDay']//option")));
    for (WebElement item: allItem){
        String textItem = item.getText();
       // System.out.println("Name of item: "+textItem);
        if (textItem.equals("18")){
            item.click();
            sleepInSeconds(1);
            break;
        }
    }
    Assertion assertion = new Assertion();
    assertion.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']//option[@value='18']")).isSelected());
//Month
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
    ;

}
}
