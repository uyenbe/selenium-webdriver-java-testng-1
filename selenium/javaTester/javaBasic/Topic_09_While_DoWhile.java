package javaTester.javaBasic;

// Đây là class
public class Topic_09_While_DoWhile {
    //while: Kiểm tra điều kiện trước rồi mới chạy vào phần thân
    // do-while: chạy phần thân trước ít nhất 1 lần rồi mới kiểm tra điều kiện
    // Dùng while/ do-while cho case vòng lặp không xác định được số lần lặp

   // Đây là function
    public static void main(String[] args) {

        int i = 0;

        // Block code
        for (i =0; i <=5; i++){
            // i ở đây chỉ được dùng trong vòng for
            System.out.println("For: "+ i);
//            if (i == 3){
//                break;
//            }
        }
        System.out.println("Biến I sau khi done vòng for: " + i);

        //int i = 0; // i ở đây khác i trong vòng for, chỉ cùng tên thôi
        // Sau khi done vòng for thì lúc này biến i = 5 >> không thoả mãn điều kiện của vòng while
        while (i < 5){
            System.out.println("while: "+ i);
            i++;
//            if (i == 3){
//                break;
//            }
        }

        // do-while cho chạy ít nhất 1 lần phần thân ngay cả khi điều kiện sai rồi mới kiểm tra điều kiện
        // hạn chế dùng vòng lặp do-while vì dễ gây hiểu nhầm

        do {
            System.out.println("do while: "+ i);
            i++;
        }while (i < 5);
    }
}
