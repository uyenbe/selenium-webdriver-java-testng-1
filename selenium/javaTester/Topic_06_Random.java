package javaTester;

import java.util.Random;

public class Topic_06_Random {
    // java builtin : Cung cấp sẵn, có sẵn trong java - lấy ra sử dụng
    //java libraries: Do 1 tổ chức/cá nhân viết
    public static void main (String[] args) // dùng hàm main thì mới chạy được trong java
    {
        Random random = new Random() ;
        System.out.println(random.nextInt()) ; // in ra chuỗi chứa các số nguyên
        System.out.println(random.nextInt(999)) ;// in ra chuỗi chứa các số nguyên từ 0 >> 999
        System.out.println(random.nextDouble()) ;// in ra chuỗi số thập phân
        System.out.println(random.nextFloat()) ;// in ra chuỗi số thập phân
        System.out.println(random.nextBoolean()) ;// in ra chuỗi có kết quả true/false
        System.out.println("nguyenuyen" + random.nextInt(99999) + "@gmail.com");



    }
}
