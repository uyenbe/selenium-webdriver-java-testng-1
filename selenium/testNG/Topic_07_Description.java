package testNG;

import org.testng.annotations.Test;

public class Topic_07_Description {
   // Cách đặt Tên của TCs = hàm/function/method của Java
    // theo convention của từng ngôn ngữ

    // Khi muốn Chú thích hoặc/ diễn giải/note >> dùng description >> description sẽ show ra trong report
    // Có description thì member mới/non-tech đọc dễ hiểu
    // Ko comment code


    @Test (description = "Demo - Use can register")
    public void shouldBeRegister() {
        System.out.println("shouldBeRegister");

    }

    // @Test (priority=2)
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

