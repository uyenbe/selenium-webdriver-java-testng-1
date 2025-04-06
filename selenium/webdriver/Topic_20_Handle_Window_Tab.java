package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_20_Handle_Window_Tab {
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
    public void TC_01_Tab_Windows_have_2_ID() {

        //Mỗi Tab/Windows có 1 ID đại diện, khác với iD của driver
        // Selenium sẽ cũng cấp 2 hàm để:
        // 1 Hàm lấy ra được ID mà driver đang đứng ở page nào (ID của Tab/Windows)
        // 1 Hàm get ra tất cả các ID, không quan tâm driver đang đứng ở đâu
        // Hàm để nhảy qua các tab/windows cần >> switchTo.windown("truyền ID của Tab/Windows")
        // Việc quan trọng nhất là phải xác định được ID của Tab/Window mình cần switch vào

      //  ------------------------------------------

        // Các bước để Switch qua Tab/Window khác:
        //B1. Get ra ID của tab/window hiện tại >> dùng driver.getWindowHandle()
        //B2. Thực hiện thao tác/click để xuất hiện tab/window thứ 2
        //B3. Get ra tất cả ID của tab/window đang có
        //B4. So sánh trong số ID được lấy ra, ID nào khác ID ở B1 thì mình switch qua (dùng vong for kết hợp với phủ định của hàm IF)


        driver.get("https://automationfc.github.io/basic-form/index.html");
        System.out.println("Driver ID: " + driver.toString());

        //Lấy ra ID của Tab/Window mà driver đang active hoặc đang đứng tại page đó
        String githubWindowID = driver.getWindowHandle();
        System.out.println("Page ID_0: " + githubWindowID);
        System.out.println("Page Title: " + driver.getTitle());
        System.out.println("Page URL: " + driver.getCurrentUrl());


        // CLick vào GG link >> nó sẽ bật lên 1 tab mới và tự nhảy qua
        // Nhưng về mặt code thì driver không tự nhảy qua, nó vẫn ở Tab/Window cũ
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        //Chỗ này không dùng lại biến githubWindowID được mà phải dùng driver.getWindowHandle() là:
        // vì biến githubWindowID khai báo để lấy ID của page trước đó, hiện tại đang ở page mới được mở ra
        // nhưng do driver vẫn đứng ở page trước đó nên ID của page vẫn là ID của page trước đó chứ ko phải ID của page sau
        System.out.println("Page ID 2: " + driver.getWindowHandle());
        sleepInSeconds(3);

        //Lấy ra tất cả ID của Tab/Window đang có
        // Khi lấy ra tất cả các ID của Tab/Window >> tức là có nhiều ID.
        // Ở đây không dùng hàm List<String> để lưu các ID mà dùng hàm Set<String> bởi vì:
        // List cho phép lưu trùng ID còn Set không cho lưu trùng ID
        Set<String> allWindowIDs = driver.getWindowHandles();
          System.out.println("all IDs_1: " + allWindowIDs );

        // Dùng vòng lặp để duyệt qua từng ID
        for (String id : allWindowIDs){

            //So sánh IDs được lấy ra cái nào khác với ID hiện tại thì switch qua ID khác
            if (!id.equals(githubWindowID)){
                driver.switchTo().window(id);
            }
        }
        sleepInSeconds(3);

        //Get lại các thông tin của page mới
        System.out.println("Page ID new: " + driver.getWindowHandle());
//        System.out.println("Page Title: " + driver.getTitle());
//        System.out.println("Page URL: " + driver.getCurrentUrl());

        // Send key vào Page GG được mở ra
        driver.findElement(By.xpath("//textarea[@name = 'q']")).sendKeys("Automation");

        sleepInSeconds(5);

        // Vì lúc này đã switch vào page của GG rồi nên driver lúc này đang đứng ở page của GG
        String id_GG = driver.getWindowHandle();
        System.out.println("Id GG: " + id_GG);

        // Muốn Switch về tab trước đó
        Set<String> allWindowIDs_2 = driver.getWindowHandles();
        System.out.println("all IDs_2:" + allWindowIDs_2);

        // Dùng vòng lặp để duyệt qua từng ID
        for (String id_2 : allWindowIDs_2){

            //So sánh IDs được lấy ra cái nào khác với ID hiện tại thì switch qua ID khác
            if (!id_2.equals(id_GG)){
                driver.switchTo().window(id_2);
            }
        }
        sleepInSeconds(3);
        System.out.println("Page ID pre: " + driver.getWindowHandle());
//        System.out.println("Page Title: " + driver.getTitle());
//        System.out.println("Page URL: " + driver.getCurrentUrl());

        // Bài tập phần này sẽ viết rút gọn đoạn lặp lại của vòng for thành 1 hàm dùng chung
        // Vòng for trên chỉ đúng với case có 2 tab/window
        //TH có từ 3 Tab/Window sẽ không áp dụng được nữa,
        // vì mình đang chưa xác định được chính xác Tab cần switch qua, tại có >2 tab có ID khác với ID của page trước
        // Nên để xác định được chính xác Tab/Window cần switch qua thì ta làm như sau:
        // Vẫn lấy ra tất cả các Tab/Window ID
        // sau đó dùng vòng for để duyệt qua lần lượt từng ID được lấy ra
        // Mỗi lần duyệt sẽ cho driver switch vào trước để lấy ra Title của page được swich vào (lấy URL của page cũng được vì sẽ có những web ko đổi title khi mở tab khác)
        // Nếu page của title hiện tại = Title của page mình cần thì thoát vòng lặp bởi vì mình đã switch vào page từ bước trước rồi
        // Nếu page title được switch vào != page cần thì lại tiếp tục chạy vòng for đến phần tử tiếp theo và lại so sánh tiếp

        //Check ID của page hiện tại xem driver đang đứng ở đâu
//        String pageID_current = driver.getWindowHandle();
//        System.out.println("Page ID current: " + pageID_current);

        // Click vào FB link >> mở ra tab FB. Lúc này driver đang ở page chính
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(3);

        //---------Đoạn code check với >2 tab/Window sau làm bài tập sẽ viết thành hàm để gọi lại check cho gọn-------//
        Set<String> allWindowIDs_4 = driver.getWindowHandles();
        System.out.println("all IDs_4:" + allWindowIDs_4);
        for (String id_4 : allWindowIDs_4){
            // switch vào page có ID được duyệt lần lượt
            driver.switchTo().window(id_4);
            System.out.println("Id_4: "+ id_4);

            //Lấy ra title của page đang được switch vào
            String pageTitle = driver.getTitle();
            System.out.println("Page Title current: " + pageTitle);

            if (pageTitle.equals("Google")){
                break;
            }
        }
        System.out.println("Page Title: " + driver.getTitle());

//-----------Bài tập phần này viết thành hàm dùng cho clean code--------------//
        // Sau khi mở nhiều tab/Window thì check xem tab/Window nào khác với expected thì close cái tab/window khác đi
        //B1. Lấy hết toàn bộ các ID của tab/window ra
        //B2. Dùng vòng for để duyệt lần lượt qua các ID
        //B3. Check xem nếu ID được duyệt khác với ID expected thì:
        // B3.1 Switch vào tab/window có ID đang được duyệt
        // B3.2 Close tab/window khác với expected
        //B4. Switch lại vào tab/window expected sau khi đóng hết các tab/window khác với expected bởi vì:
        // lúc này driver đang đứng ở tab/window bị close cuối cùng
        // và driver không tự nhảy sang tab/window còn lại mà cần phải switch vào để driver nhảy vào

        Set<String> allWindowIDs_5 = driver.getWindowHandles();
        System.out.println("all IDs_5:" + allWindowIDs_5);

        for(String id_5 : allWindowIDs_5){

            if (!id_5.equals(id_GG)){
                driver.switchTo().window(id_5);
                sleepInSeconds(2);
                driver.close();
            }

        }
        driver.switchTo().window(id_GG);
        System.out.println("Page ID new: " + driver.getWindowHandle());

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
