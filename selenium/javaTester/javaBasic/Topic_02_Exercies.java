package javaTester.javaBasic;

import org.testng.annotations.Test;

public class Topic_02_Exercies {
    @Test
    public void TC_01() {
        int a = 6;
        int b = 2;
        System.out.println("P1 = " + (a+b));
        System.out.println("P2 = " + (a-b));
        System.out.println("P3 = " + (a*b));
        System.out.println("P4 = " + (a/b));
    }

    @Test
    public void TC_02() {
        float chieu_dai = 7.5f;
        float chieu_rong = 3.8f;
        System.out.println("Dien tich P = " + (chieu_dai*chieu_rong));
    }

    @Test
    public void TC_03() {
        String name = "Automation Tesing";
        System.out.println("Hello " + name);
    }
}
