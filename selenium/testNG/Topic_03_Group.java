package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class Topic_03_Group {
    WebDriver driver;
    @Test
    public void assertion() {
        Assertion assets = new Assertion();
        // AssertTrue: Khi kiểm tra 1 điều kiện mong đợi nó sẽ trả về là ĐÚNG
        // Áp dụng cho các hàm trả về true/false:
        // Selenium: isDisplayed/ isEnabled/ isSelected/ isMultiple
        // Hoặc các hàm User tự  define
        String fullName = "Automation testing";
        assets.assertTrue(fullName.contains("Manual"));

        // Assert False: Khi kiểm tra 1 điều kiện mong đợi nó sẽ trả về là SAI
        assets.assertFalse(fullName.contains("Manual"));

        // Assert Equal: Khi kiểm tra 1 điều kiện mong đợi bằng vs điều kiện thực tế
        // Selenium: getText(), getAttribute(), getUrl, getTitle, getCss,...
        assets.assertEquals(driver.findElements(By.cssSelector("")).size(),5);
    }
}
