package product;

import org.testng.annotations.Test;

public class Product_01_CRUD {
    @Test(groups = "product")
    public void TC_01_Annotation() {
        System.out.println("Product_01_Annotation");

    }

    @Test (groups = "product")
    public void TC_02_Annotation() {
        System.out.println("Product_02_Annotation");

    }
}
