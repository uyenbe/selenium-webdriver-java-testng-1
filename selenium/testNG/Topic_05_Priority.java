package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

public class Topic_05_Priority {
   //TH không set thứ tự cho các TCs thì nó sẽ chạy ngẫu nhiên và ko theo thứ tự nào cả
    // Nếu như có TCs liên quan đến flow thì sẽ bị ảnh hưởng
    // >> Phải set thứ tự cho các TCs hoặc đặt priority
    // >> Ưu tiên set thứ tự cho các TCs: TC_01, TC_02,... để dễ dàng quản lý và count số lượng TCs trong 1 class/package/module


    @Test (priority=1)
    public void shouldBeRegister() {
        System.out.println("shouldBeRegister");

    }

    @Test (priority=2)
    public void shouldBeLogin() {
        System.out.println("shouldBeLogin");

    }

    @Test (priority=3)
    public void shouldBeLoginFail() {
        System.out.println("shouldBeLoginFail");

    }

    @Test (priority=4)
    public void shouldBeCreateNewProduct() {
        System.out.println("shouldBeCreateNewProduct");

    }

    @Test (priority=5)
    public void shouldBeViewProduct() {
        System.out.println("shouldBeViewProduct");

    }

    @Test (priority=6)
    public void shouldBeDeleteProduct() {
        System.out.println("shouldBeDeleteProduct");

    }


}

