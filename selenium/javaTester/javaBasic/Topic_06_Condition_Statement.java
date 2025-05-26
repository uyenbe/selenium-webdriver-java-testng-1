package javaTester.javaBasic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_06_Condition_Statement {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
@Test
    public void TC_01_IF() {
    //I. IF statement
    //1. Hàm IF
    boolean status = 5 > 3; // đây là phép so sánh
    System.out.println(status);

    // Hàm if sẽ nhận vào 1 điều kiện đúng
    if (status){
        //Đúng thì vào phần thân của hàm if
        // Sai thì bỏ qua
        System.out.println("Go to If");
    }

    //Assign - Gán
    int numberStudent = 10;

    // So sánh
    status = (numberStudent == 10);
    System.out.println(status);

    WebDriver driver = new ChromeDriver();

    //Element luôn có trong DOM dù popup hiển thị hay ko
    WebElement salePopup = driver.findElement(By.id(""));
    if (salePopup.isDisplayed()){
        //điều kiện
    }

    //Element ko có trong DOM khi popup không hiển thị
    List<WebElement> salePopups = driver.findElements(By.id(""));

    // Check element không hiển thị
    if (salePopups.size() > 0 && salePopups.get(0).isDisplayed()){
        // điều kiện check
    }

    // Uncheck to checkbox
    WebElement checkbox = driver.findElement(By.id(""));
    if (checkbox.isDisplayed()){
        checkbox.click();
    }

    // Hàm check to checkbox
    if (!checkbox.isDisplayed()){
        checkbox.click();
    }
}

@Test
public void TC_02_IF_Else() {
    //2. IF-else
    // Có tới 2 điều kiện: nếu như đúng thì vào if, sai thì vào else

    // Nếu drvier start với browser như Chrome/Firefox/Edge/Safari thì dùng hàm click thông thường (builtin) của Selenium WebElement
    // Nếu driver là IE thì dùng hàm click của JavaScriptExecutor
    System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
    driver = new ChromeDriver();

    System.out.println(driver.toString());

    if (driver.toString().contains("ChromeDriver")){
        System.out.println("Click by Selenium WebElement");
    }else {
        System.out.println("Click by JavaScript Executor");
    }

}

@Parameters("browser")
@Test
    public void TC_03_If_Elese_If_Else(String browserName) {
    // Có điều kiện
    // Không nên if-else quá nhiều
    if (browserName.equalsIgnoreCase("chrome")) {
        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
    }else if (browserName.equalsIgnoreCase("firefox")) {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
    }else if (browserName.equalsIgnoreCase("edge")) {
        System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/edgedriver");
    }else {
        throw new RuntimeException("Please input correct browser name");
    }
    System.out.println(driver.toString());
    System.out.println(browserName);
    driver.quit();

}

    @Test
    public void TC_04_If_Elese_If_Else(String browserName) {
        //Page Object
        // Dynamic Page
        // Dùng If-else if-else có thể bị trùng các điều kiện trong hàm if nhưng khi chạy nó ko báo lỗi
        String pageName = "Login";

        if (pageName.equals("Login")) {
            // LoginPage loginPage = new LoginPage()
            // return loginPage
        } else if (pageName.equals("Register")) {
            // RegisterPage registerPage = new RegisterPage()
            // return registerPage;
        } else if (pageName.equals("New Customer")) {
            // CustomerPage newCustomerPage = new CustomerPage()
            // return newCustomerPage;
        }else {
            // HomePage homePage = new homePage()
            // return homePage;

        }

        // Biểu thức tam nguyên - đại diện cho if-else
        int age =20;
        String access = (age < 18) ? "you can not accsess" : "welcome to our system";

        // Diễn giải theo kiểu if-else
        if (age <18){
            access = "you can not accsess";
        }else {
            access = "welcome to our system";
        }
        }








//    public static void main (String[] args) // dùng hàm main thì mới chạy được trong java
//    {
//
//    }

}
