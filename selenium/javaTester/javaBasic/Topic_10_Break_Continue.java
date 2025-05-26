package javaTester.javaBasic;

public class Topic_10_Break_Continue {

    // Continue chỉ ảnh hưởng đến vòng for mà nó đang đứng thôi
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Lần chạy của i (for trên)" + i);

            //Mỗi lần chạy của vòng for trên sẽ apply cho tất cả các lần chạy của for dưới này
            for (int j = 1; j <= 5; j++) {
                if (j == 5){
                    continue;
                }
                System.out.println("j = "+ j);
            }

            // Loại trừ i = 5

        }
    }
}
