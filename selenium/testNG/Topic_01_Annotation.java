package testNG;

import org.testng.annotations.*;

public class Topic_01_Annotation {

    //Cách chạy của các Annotation được ánh xạ theo thứ tự trong file .xlm (Run.xml)
    // Thực tế hay dùng beforTest và beforClass. Tuỳ vào phạm vi của dự án nhỏ hay lớn
    //Nếu muốn chạy 1 lần cho tất cả các class >> beforeTest
    // Muốn chạy 1 lần cho 1 class >> thêm beforeClass vào trong class đó (public class)
    //Trong 1 TCs luôn phải xác minh lại xem TCs chạy đúng hay chưa >> dùng assert
    // AAA pattern: Arrange/Act/Assert
    //Arrange: PreCondition - Khởi tạo class/dữ liệu
    //Act (action): Thực hiện test
    //Assert: Verify
    //TestNG có thể quản lý/chạy TCs theo package/Class/Method hoặc Group





    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test");
    }


    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Before Method");
    }


    @Test
    public void TC_01_Annotation() {
        System.out.println("TC_01_Annotation");

    }

    @Test
    public void TC_02_Annotation() {
        System.out.println("TC_02_Annotation");

    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("After Method");
    }


    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("After Test");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("After Suite");
    }
}
