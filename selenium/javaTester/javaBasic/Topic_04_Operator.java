package javaTester.javaBasic;

public class Topic_04_Operator {

    public static void main(String[] args) {
        //I. Assign
        // Ví dụ 11 vài toán tử:
        // +=
        int number = 5;
        number += 5;// Dòng này là cách viết diễn giải number = number + 5;
        System.out.println("number = "+ number);

        // Phép chia lấy số nguyên
        System.out.println(number/2);

        // Chia lấy số dư
        System.out.println(number%4);

        //II. Math (Toán học)
        int math = 5;
        // Bao gồm các biểu thức toán học: +,-,*,/, ++, --
        System.out.println( "math = "+math ++);
        // nếu dùng math++ cách thức hoạt động như sau:
        // In math ra trước: 5
        // Sau đó ++ => tăng math lên 1 >> number = 6
        // sau bước math++ nếu mà in lại biến math thì lúc này math = 6
        System.out.println("math sau ++ = " + math);

        System.out.println("++ math = " + ++math);
        //nếu dùng ++math thì cách thức hoạt động như sau:
        // ++ trước: tăng math lên 1 đơn vị >> math = 7. Tại sao lại = 7, vì bên trên vừa thực hiện tăng 1 đơn vị cho math nè
        // Sau đó in math ra >> math = 7

        //III. Relation - Biểu thức quan hệ
        // Bao gồm các biểu thức: ==, !=, <, >, <=, >=

        //IV. Logic
        // Bao gồm các biểu thức: ||(hoặc), && (và), ! (phủ định lại 1 điều kiện)
        String address = "Ho Chi Minh";
        if (!(address == "Ha Noi")){
            System.out.println("Address is not the same");
        }

        //V. Condition - Biểu thức điều kiện - biểu thức tam nguyên
        // Biểu thức này có 3 dấu: =, ?, :
        boolean status = (address == "Ha Noi")?true:false;
        System.out.println(status);


        //VI. Casting - Ép kiểu
        //





    }

    


}
