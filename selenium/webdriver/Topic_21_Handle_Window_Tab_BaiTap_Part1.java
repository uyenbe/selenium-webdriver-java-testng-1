package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_21_Handle_Window_Tab_BaiTap_Part1 {
    WebDriver driver;
    Actions actions;

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

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_13_Tab_Windows_have_2_ID() {

        //Các bước:
        //1. Lấy ra ID của tab/window hiện tại drvier đang trỏ vào
        //2. Click vào link để open tab/window khác
        //3. Lấy ra tất cả IDs của tab/window đang được open
        //4. Dùng vòng for để duyệt qua lần lượt từng ID được lấy ra
        //5. Check ID nào khác ID expectd thì thoát vòng lặp hoặc switch vào rồi close tab/window có ID khác
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String idWindownParent = driver.getWindowHandle();
        System.out.println("ID window parent: " + idWindownParent);
        System.out.println("Title parent page: " + driver.getTitle());

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        System.out.println("Check xem da nhay driver chua: " + driver.getWindowHandle());
        switchToWindowByID(idWindownParent);
        String idGoogle = driver.getWindowHandle();
        System.out.println("Title new page: " + driver.getTitle());
        System.out.println("ID Google: " + idGoogle);
        switchToWindowByID(idGoogle);
        System.out.println("Check xem da nhay driver chua: " + driver.getWindowHandle());

        //Open nhiều tab/window
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        System.out.println("FB click! Check xem da nhay driver chua: " + driver.getWindowHandle());
        switchToWindowByTitlePage("Facebook - Đăng nhập hoặc đăng ký");
        String idFacebook = driver.getWindowHandle();
        System.out.println("Title new page: " + driver.getTitle());
        System.out.println("ID Facebook: " + driver.getWindowHandle());
        switchToWindowByTitlePage("Selenium WebDriver");
        System.out.println("Check xem da nhay driver chua: " + driver.getWindowHandle());

        //Đóng tab/window giống với expected
        closeWindow("Google");

        //Đóng tab/window khác với expected
        driver.switchTo().window(idWindownParent);
        driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
        sleepInSeconds(1);
        closeWindowDifferenceTitle("Selenium WebDriver");

        // Kiểm tra đã quay về parent window thành công
        driver.switchTo().window(idWindownParent);
        System.out.println("ID driver hien tai: " + driver.getWindowHandle());
        String checkTitle = driver.getTitle();
        System.out.println("Check title: " + checkTitle);

        Assertion assertion = new Assertion();
        assertion.assertEquals(checkTitle, "Selenium WebDriver");

    }

    @Test
    public void TC_14_() {
        driver.get("http://live.techpanda.org/");

        //Click mobile tab
        driver.findElement(By.cssSelector("li.nav-1")).click();
        sleepInSeconds(2);

        //Search product >> trang chặn mất rùi
//        driver.findElement(By.cssSelector("input#search")).sendKeys("Sony Xperia");
//        sleepInSeconds(2);
//        driver.findElement(By.cssSelector("button.search-button")).click();
//        sleepInSeconds(2);

        //Add product to compare
        driver.findElement(By.xpath("//a[@title='Xperia']//following-sibling::div//a[@class='link-compare']")).click();
        sleepInSeconds(2);
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
                "The product Sony Xperia has been added to comparison list.");

        //Add product to compare
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']//following-sibling::div//a[@class='link-compare']")).click();
        sleepInSeconds(2);
        assertion.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
                "The product Samsung Galaxy has been added to comparison list.");

        //Click Compare button
        driver.findElement(By.xpath("//button[@title = 'Compare']")).click();
        sleepInSeconds(2);

        //Switch to new window has to product added
        //Cách 1. Tự viết hàm
        // Lấy ra ID của tab parent
        String idDriver1 = driver.getWindowHandle();
        System.out.println("Driver parent: " + idDriver1);

//        Set<String> allIdDrivers = driver.getWindowHandles();
//        System.out.println("All driver: " + allIdDrivers);
//        for(String id : allIdDrivers ){
//            if (!id.equals(idDriver1)) {
//                driver.switchTo().window(id);
//            }
//        }
        // Cách 2. Tách hàm sau đó gọi lại
        //Cách nhanh: Vì driver đang đứng ở tab/window hiện tại rồi
        // driver.close();

        // Cách đang học:
        switchToWindowByID(idDriver1);
        System.out.println("Driver ID new: " + driver.getWindowHandle());
        String titleComparePage = driver.getTitle();
        System.out.println("Title compare page: " + titleComparePage);
        assertion.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

        //Close tab and back to parent tab
        Set<String> allIDTab = driver.getWindowHandles();
        System.out.println("All ID tab:" + allIDTab);

        for (String id : allIDTab) {
            driver.switchTo().window(id);
            if (!id.equals(idDriver1)) {
                driver.close();
            }
        }
        // System.out.println("ID sau khi close: "+ driver.getWindowHandle()); -- không lấy được vì tab này bị close đi rồi

        driver.switchTo().window(idDriver1);
        System.out.println("ID sau khi switch: " + driver.getWindowHandle());

        //CLick clear all
        driver.findElement(By.xpath("//a[text()= 'Clear All']")).click();
        sleepInSeconds(2);

        //Accept alert
        // Cách 1: dùng theo cách handle alert trước
//        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
//        alert.accept();

        // Cách 2: dùng switchTo để switch vào alert
        driver.switchTo().alert().accept();
        sleepInSeconds(2);

        //Verify message
        assertion.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
                "The comparison list was cleared.");


    }

    @Test
    public void TC_15_() {
        driver.get("https://dictionary.cambridge.org/vi/");
        sleepInSeconds(2);

        driver.findElement(By.xpath("//div[@amp-access='NOT loggedIn']//span//span[text()='Đăng nhập']")).click();
        sleepInSeconds(2);

        //Switch to new tab - không gọi hàm
        String idCampParent = driver.getWindowHandle();
        Set<String> allIdCamp = driver.getWindowHandles();
        for (String id : allIdCamp) {
            if (!id.equals(idCampParent)) {
                driver.switchTo().window(id);
            }
        }
        sleepInSeconds(2);
        System.out.println("New id tab Camp: " + driver.getWindowHandle());

        driver.findElement(By.xpath("//input[@value = 'Log in']")).click();
        sleepInSeconds(2);

        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.findElement(By.xpath("//input[@aria-label='Email']//following-sibling::span[@data-bound-to='loginID']")).getText(),
                "This field is required");

        assertion.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Password *']//following-sibling::span[@data-bound-to='password']")).getText(),
                "This field is required");

        //Close window: mình có thể switch vào window trước hoặc switch vào sau khi check idWindow
        for (String id : allIdCamp) {
            driver.switchTo().window(id);
            if (!id.equals(idCampParent)) {
                driver.close();
            }
        }
        driver.switchTo().window(idCampParent);

        //sendkey to searchbox
        driver.findElement(By.cssSelector("input#searchword")).sendKeys("automation");
        driver.findElement(By.xpath("//button[@type = 'submit']//i[@class='i i-search']")).click();
        sleepInSeconds(2);

        assertion.assertEquals(driver.findElement(By.xpath("//div[@data-id='cald4']//span//span[@class='hw dhw']")).getText(),
                "automation");

    }

    @Test
    public void TC_16_() {
        driver.get("https://courses.dce.harvard.edu/");
        sleepInSeconds(2);

        driver.findElement(By.xpath("//a[@data-action='login']")).click();
        sleepInSeconds(2);

        //Switch qua new tab
        String idDCE = driver.getWindowHandle();
        Set<String> allIdDCE = driver.getWindowHandles();
        System.out.println("All ID dce: " + allIdDCE);
        for (String id : allIdDCE) {
            if (!id.equals(idDCE)) {
                driver.switchTo().window(id);
                break;
            }
        }
        System.out.println("New id tab IDE: " + driver.getWindowHandle());
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.getTitle(), "Harvard Division of Continuing Education Login Portal");

        //Close new tab
        for (String id : allIdDCE) {
                //--- Solution by Trung Thien ------
//                try {
//                    waitUntilTimes("button#harvard-key-button", 1);
//                } catch (RuntimeException e) {
//                    System.err.println("Error timeout: " + e.getMessage());
//                    continue;
//                }
//                WebElement element = (new WebDriverWait(driver,  Duration.ofSeconds(1)))
//                        .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#harvard-key-button")));

            if (!id.equals(idDCE)) {
                driver.switchTo().window(id);
                //sleepInSeconds(5);
                driver.close();
            }
        }
        driver.switchTo().window(idDCE);
        System.out.println("id tab IDE sau khi switch: " + driver.getWindowHandle());
        sleepInSeconds(2);

        assertion.assertTrue(driver.findElement(By.cssSelector("div#sam-wait")).isDisplayed());
        driver.findElement(By.cssSelector("button.sam-wait__close")).click();
        sleepInSeconds(2);
    }

    @Test
    public void TC_17_Auto_Switch_NewTab() {
        driver.get("http://live.techpanda.org/");
        sleepInSeconds(2);

        //Click mobile tab
        driver.findElement(By.cssSelector("li.nav-1")).click();
        sleepInSeconds(2);

        System.out.println("ID_Driver: " + driver.toString());
        System.out.println("ID_Window: " + driver.getWindowHandle());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.switchTo().newWindow(WindowType.WINDOW)
                .get("http://live.techpanda.org/index.php/customer/account/login/");
        sleepInSeconds(2);

        System.out.println("ID_Driver: " + driver.toString());
        System.out.println("ID_Window: " + driver.getWindowHandle());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

    }





    private void waitUntilTimes(String element, int time) {
        if (time == 0) throw new RuntimeException("Hết lượt");

        WebElement e = driver.findElement(By.cssSelector(element));
        if (e == null) {
            sleepInSeconds(1);
            time--;
            waitUntilTimes(element, time);
        }
    }

    private void switchToWindowByID(String windowID) {
        Set<String> allIdWindows_1 = driver.getWindowHandles();
        //System.out.println("ID all window 1: "+ allIdWindows_1);

        for (String id : allIdWindows_1) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                sleepInSeconds(2);
                break;
            }
        }
    }

    private void switchToWindowByTitlePage(String titlePage) {
        Set<String> allIdWindows_2 = driver.getWindowHandles();
        System.out.println("ID all window 2: " + allIdWindows_2);

        // Dùng vòng for để duyệt lần lượt từng ID được lấy ra
        for (String id2 : allIdWindows_2) {

            // Switch vào tab/winhdow để lấy ra title của tab/page hiện tại
            driver.switchTo().window(id2);
            String titleCurentPage = driver.getTitle();

            // DÙng hàm if để check xem title hiện tại = title mong muốn >> thoát vòng for
            if (titleCurentPage.equals(titlePage)) {
                sleepInSeconds(2);
                break;
            }
        }
    }

    private void closeWindow(String titlePage) {
        Set<String> allIdWindows_2 = driver.getWindowHandles();
        for (String id2 : allIdWindows_2) {
            driver.switchTo().window(id2);
            String titleCurentPage = driver.getTitle();
            if (titleCurentPage.equals(titlePage)) {
                sleepInSeconds(2);
                driver.close();
            }
        }
    }

    private void closeWindowDifferenceTitle(String titlePage) {
        Set<String> allIdWindows_2 = driver.getWindowHandles();
        for (String id2 : allIdWindows_2) {
            driver.switchTo().window(id2);
            String titleCurentPage = driver.getTitle();
            if (!titleCurentPage.equals(titlePage)) {
                sleepInSeconds(2);
                driver.close();
            }
        }
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
