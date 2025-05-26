package javaTester.javaBasic;

public class Topic_05_Casting {

    public static void main(String[] args) {
        //I. Casting - Ép kiểu
        //1. Khái niệm
        // Đây là việc chuyển từ data type này sang data type khác.
        // Việc này không làm thay đổi data type của biến cũ mà chỉ giúp tạo ra một biến mới với data type mới

        //2. Các cách ép kiểu
        // Có 2 cách ép kiểu:
        // - Ép kiểu với dữ liệu nguyên thuỷ (Primitive Data Type). Ở đây chia làm 2 loại:
            // + Chuyển đổi/Ép kiểu ngầm định (Implicit) - Chính là nới rộng (mở rộng) khả năng lưu trữ
            // + Chuyển đổi/Ép kiểu tường minh (Explicit) - Chính là thu hẹp khả năng lưu trữ
        // - Ép kiểu với dữ liệu tham chiếu (Reference Type)

        //3.  Chuyển đổi/Ép kiểu ngầm định (Implicit)
        // Cách ép kiểu này giúp chuyển đổi từ data type có kích thước/ khả năng lưu trữ nhỏ >> data type có khả năng lưu trữ lớn
        // Nhưng ko làm mất đi giá trị của dữ liệu
        // kiểu byte có thể ép sang kiểu: short, int, long, float, double
        // tương tự với kiểu short >>> int, long, float, double
        // int có thể ép sang: long, float, double
        // long có thể ép sang: float, double
        // float có thể ép sang: double

        //4. Chuyển đổi/Ép kiểu tường minh (Explicit)
        // Cách ép kiểu này sẽ chuyển đổi từ data type có khả năng lưu trữ lớn hơn >> data type có khả năng lưu trữ nhỏ hơn
        // Cách này có thể làm mất đi giá trị của dữ liệu
        // Cần chỉ định khi muốn ép kiểu tường minh

// ----------------------- Ví dụ----------------------------//
        // Ép kiểu ngầm định (Implicit)
        //        byte bNumber = 126;
        //        System.out.println(bNumber);
        //
        //        short sNumber = bNumber;
        //        System.out.println(sNumber);
        //
        //        int iNumber = sNumber;
        //        System.out.println(bNumber);
        //
        //        long lNumber = iNumber;
        //        System.out.println(iNumber);
        //
        //        float fNumber = lNumber;
        //        System.out.println(fNumber);
        //
        //        double dNumber = fNumber;
        //        System.out.println(dNumber);

        // Ép kiểu tường minh (Explicit) - mất dữ liệu
        double dNumber = 6543217890d;
        System.out.println(dNumber);

        float fNumber = (float) dNumber;
        System.out.println(fNumber);

        long lNumber = (long) fNumber;
        System.out.println(lNumber);

        int iNumber = (int) lNumber;
        System.out.println(iNumber);




    }

}
