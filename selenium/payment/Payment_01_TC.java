package payment;

import org.testng.annotations.Test;

public class Payment_01_TC {
    @Test(groups = {"payment", "regression"})
    public void TC_01_Annotation() {
        System.out.println("Payment_01_Annotation");

    }

    @Test (groups = {"payment", "regresstion"})
    public void TC_02_Annotation() {
        System.out.println("Payment_02_Annotation");

    }
}
