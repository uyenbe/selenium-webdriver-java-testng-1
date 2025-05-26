package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class Topic_12_DependOnTest {
    WebDriver driver;
    @Test ()
    public void TC_01_Create_New_Product() {
        System.out.println("TC_01_Create_New_Product");

//        Assertion   assertion = new Assertion();
//        assertion.assertTrue(false);

    }


    @Test (dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_02_View_Product() {
        System.out.println("TC_02_View_Product");

    }

    @Test (dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_03_Edit_Product() {
        System.out.println("TC_03_Edit_Product");

    }


    @Test(dependsOnMethods ="TC_01_Create_New_Product")
    public void TC_04_Move_Product() {
        System.out.println("TC_04_Move_Product");

    }


    @Test(dependsOnMethods ="TC_01_Create_New_Product")
    public void TC_05_Delete_Product() {
        System.out.println("TC_05_Delete_Product");

    }
}
