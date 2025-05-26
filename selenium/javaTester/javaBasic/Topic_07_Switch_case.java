 package javaTester.javaBasic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Scanner;

 public class Topic_07_Switch_case {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
  //  String osName = System.getProperty("os.name");

@Parameters("browser")
@Test
    public void TC_03_If_Elese_If_Else(String browserName) {
    getBrowserName(browserName);

    System.out.println(driver.toString());
    System.out.println(browserName);
    driver.quit();

}

// case return về driver
public WebDriver getBrowserName(String browserName){
    switch (browserName) {
        case "chrome":
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
            driver = new ChromeDriver();
            break;
        case "firefox":
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/geckodriver");
            driver = new FirefoxDriver();
            break;
        default:
            throw new RuntimeException("Please input correct browser name");

    }
    return driver;
}

@Test
public static void main (String[] args) {
    //TH không dùng break >> sẽ chạy hết tất cả các case từ trên xuống dưới >> perfomence sẽ bị giảm
    Scanner sc = new Scanner(System.in);
    System.out.println("Nhập tháng vào: ");
    int month = sc.nextInt();
    switch (month){
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            System.out.println(" tháng có 31 ngày");
            break;
        case 2:
            System.out.println(" tháng có 28 ngày");
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            System.out.println(" tháng có 30 ngày");
            break;
}
}









//    public static void main (String[] args) // dùng hàm main thì mới chạy được trong java
//    {
//
//    }

}
