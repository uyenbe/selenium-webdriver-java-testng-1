package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

public class Topic_04_Always_Run {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test");
    }

    //AllwaysRun: hay áp dụng cho before và after
    // Nếu before<> (class/suite/method) mà bị fail thì các TCs mặc định sẽ bị skip và After<> mặc định sẽ không chạy
    // Always Run ko nên đưa vào TC vì khi phầnArrange fail thì ko có dữ liệu để thực thi TC và TCs sẽ tự động fail
    // >> ko cần cho vào

    // Arrange
    @BeforeClass
    public void beforeClass(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.fahasa.com/");
        //Mở đến trang login
        // Login không thành công
        // Ví dụ tượng chưng
        Assertion assertion = new Assertion();
        assertion.assertEquals(driver.getTitle(),"Fhasa");

        //TH bị fail ở before thì trình duyệt sẽ ko close vì theo cơ chế nếu before fail thì after sẽ ko chạy
        // Nên để close được browser thì dùng AlwaysRun tại After
    }



    @Test
    public void TC_01_Annotation() {
        System.out.println("TC_01_Annotation");

    }

    @Test
    public void TC_02_Annotation() {
        System.out.println("TC_02_Annotation");

    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("After Method");
    }


    @AfterClass (alwaysRun = true)

    public void afterClass(){
        System.out.println("After Class");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("After Test");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("After Suite");
    }
}

