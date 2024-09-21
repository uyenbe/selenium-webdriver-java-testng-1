package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.List;

public class Topic_09_Handle_Droplist_Custom_Part2 {
    WebDriver driver;

    // wait tường minh: hiển thị trạng thái cụ thể cho element:
    // Visible/ Invisible/ Presence/ Number/ Clickable/ ...
    WebDriverWait expliciWait;

    // Ham wait cua selenium đều chung 1 cơ chế:
    //- Nếu thoả mãn điều kiện rồi thì >> không cần chờ hết timeout
    //- Nếu chưa thoả mãn điều kiện thì tiếp tục chờ (cơ chế lặp lại/ check lại điều kiện sau mỗi tần số bao lâu
    // Bây giờ muốn dùng hàm wait >> phải khởi tạo >> khởi tạo sau webdriver
    // (vì driver là cái đầu tiên, cái open browser để thực hiện thao tác)
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");


    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
            //  WebDriverManager.chromedriver().setup();
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        //driver = new ChromeDriver();
        // cách khởi tạo của expliciWait - Wait: đợi cho 1 element thoả mãn điều kiện
        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // wait ngầm định: không rõ ràng cho 1 trạng thái cụ thể nào hết của element
        // Ngầm định cho việc tìm element
        // wait này: đợi cho element được tìm thấy
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        // driver.get("https://demo.nopcommerce.com");
        //https://demo.nopcommerce.com/register

    }

    // Cách 2: Dùng hàm để sử dụng nhiều lần, nhiều droplist
    @Test
    public void TC_01_JQuery() {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInDroplist("//span[@id='speed-button']", "//ul[@id='speed-menu']//div", "Slower");
        sleepInSeconds(1);
        //driver.navigate().refresh(); // refresh lại giá trị đã chọn trong droplist
        selectItemInDroplist("//span[@id='files-button']", "//ul[@id='files-menu']//div", "ui.jQuery.js");
        sleepInSeconds(1);
        selectItemInDroplist("//span[@id='number-button']", "//ul[@id='number-menu']//div", "3");
        sleepInSeconds(1);

        // Verify
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath
                ("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(),
                "Slower");
        assertion.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']//span[@class='ui-selectmenu-text']")).getText(),
                "ui.jQuery.js");
        assertion.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
                "3");

//        driver.findElement(By.xpath("//span[@id='number-button']")).click(); //
//        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='number-menu']//div"))); //
//        List<WebElement> allItems = driver.findElements(By.xpath("//ul[@id='number-menu']//div"));
//        for (WebElement item : allItems) {
//            String textItem = item.getText();
//            System.out.println("Text item = " + textItem);
//            if (textItem.equals("8")) {
//                item.click();
//                break;
//            }
//
//        };
//        driver.findElement(By.xpath("//span[@id='number-button']")).click(); //
//        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='number-menu']//div"))); //
//        allItems = driver.findElements(By.xpath("//ul[@id='number-menu']//div"));
//        for (WebElement item : allItems) {
//            String textItem = item.getText();
//            System.out.println("Text item = " + textItem);
//            if (textItem.equals("15")) {
//                item.click();
//                break;
//            }
//
//        }

    }

    ;

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDroplist("//i[@class='dropdown icon']",
                "//div[@class='item']//span","Stevie Feliciano");
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath("//div[@class = 'divider text']")).getText(),"Stevie Feliciano");

        selectItemInDroplist("//i[@class='dropdown icon']",
                "//div[@class='item']//span","Christian");
        assertion.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Christian");


    }
    @Test
    public void TC_03_VueJs(){
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDroplist("//li[@class='dropdown-toggle']",
                "//ul[@class='dropdown-menu']//a","Second Option");
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"Second Option");

    }
    ;
    @Test
    public void TC_04_EditAble(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemEditableInDroplist("//input[@class='search']","//div[@class='selected item']//span","Andorra");
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Andorra");
        sleepInSeconds(2);

        selectItemEditableInDroplist("//input[@class='search']","//div[@class='selected item']//span","Belgium");
        assertion.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Belgium");

    }

    @Test
    public void TC_05_NoCommers(){
        driver.get("https://demo.nopcommerce.com/register");
        selectItemInDroplist("//select[@name='DateOfBirthDay']","//select[@name='DateOfBirthDay']//option[@value='10']",
                "10");
        // droplist là default nhưng ko sử dụng thư viện Select để verify
        Assertion assertion = new Assertion();
        assertion.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']//option[@value='10']")).isSelected());
        sleepInSeconds(1);
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

    // Tao ham de su dung nhieu lan
    // Trong dấu (xxx) được gọi là tham số -đata dùng để truyền vào
    public void selectItemInDroplist(String parentXpath, String childItemXpath, String itemTexExpected) {
        driver.findElement(By.xpath(parentXpath)).click();////span[@id='number-button']
        sleepInSeconds(3);

        // Vừa wait + vừa tìm element
        //expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemXpath))); ////ul[@id='number-menu']//div

        // Tìm element >> không cần đoạn driver.findElements(By.xpath(childItemXpath)) nữa. mà thay luôn expliciWait... vào
        List<WebElement> allItems = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemXpath))); //ul[@id='number-menu']//div
        for (WebElement item : allItems) {
            String textItem = item.getText();
            //System.out.println("Text item = " + textItem);
            if (textItem.equals(itemTexExpected)) {
                item.click();
                sleepInSeconds(3);
                break;
            }

        }
    }

    public void selectItemEditableInDroplist(String parentItemXpath, String childItemXpath, String itemExpected){
        driver.findElement(By.xpath(parentItemXpath)).clear();
        driver.findElement(By.xpath(parentItemXpath)).sendKeys(itemExpected);
        sleepInSeconds(1);

        List<WebElement> allItem = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemXpath)));
        for(WebElement item : allItem){
            String textItem = item.getText();
            if (textItem.equals(itemExpected)){
                item.click();
                sleepInSeconds(3);
                break;
            }
        }

    }
}
