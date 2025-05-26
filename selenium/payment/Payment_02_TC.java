package payment;

import org.testng.annotations.Test;

public class Payment_02_TC
{
    @Test (groups = {"payment", "regresstion"})
    public void TC_01_Annotation() {
        System.out.println("Payment2_01_Annotation");

    }

    @Test
    public void TC_02_Annotation() {
        System.out.println("Payment2_02_Annotation");

    }
}
