package javaTester.javaBasic;

import org.testng.asserts.Assertion;

public class Topic_09_Assert {
    public static void main(String[] args) {
        Assertion assets = new Assertion();
        //Mong đợi điều kiện trả về là True
        // assets.assertTrue(3,5);

        String fullName = "Automation testing";
        assets.assertTrue(fullName.contains("Manual"));

        //Mong đợi điều kiện trả về là Sai (false)
        assets.assertFalse(fullName.contains("Manual"));
        // Với hàm assertTrue/ assertFalse >> dữ liệu truyền vào là boolean
        // 2 hàm này thường kết hợp với các hàm get.isDisplayed/... là các hàm trả về giá trị là boolean

        //Mong đợi 2 điều kiện bằng nhau
        assets.assertEquals(3,5);
    }


}