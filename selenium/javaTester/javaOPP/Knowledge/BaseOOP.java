package javaTester.javaOPP.Knowledge;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseOOP {
    public long shortTimeout = 15;
    protected long longTimeout = 45;
    private WebDriver driver;
    public void setImplicitWait(){
        long shortTimeout = 10;
        driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
    }
    public BaseOOP(){
        System.out.println("Constructor tại class cha");
    }

    public BaseOOP(String browser){
        System.out.println("Constructor tại class cha: " + browser);
    }
    public BaseOOP(int name){
        System.out.println("Constructor tại class cha: " + name);
    }
}
