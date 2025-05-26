package testNG;

import org.testng.annotations.Test;

public class Topic_06_Skip {
   // Để skip TC thì có thể set trạng thái cho TCs đó: enable = false hoặc là comment annotation @Test lại
    // TH không set thì mặc định enable = true


    @Test (enabled = false)
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

