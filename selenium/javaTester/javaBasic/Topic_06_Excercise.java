package javaTester.javaBasic;


import java.util.Scanner;

public class Topic_06_Excercise {

    //@Test -  trong @Test không dùng được Scaner
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("TC_01");
        System.out.println("TC_02");
        System.out.println("TC_03");
        System.out.println("TC_04");
        System.out.println("TC_05");
        System.out.println("TC_06");
        System.out.println("TC_07");
        int stt = scanner.nextInt();
        switch (stt){
            case 1:
                System.out.println(" Nhap so vao: ");
                int number = scanner.nextInt();
                if (number % 2 == 0){
                    System.out.println(number+ " " + "la so chan");
                }else {
                    System.out.println(number + " " + "la so le");
                }
                break;
            case 2:
                System.out.println(" Nhap so a vao: ");
                int a = scanner.nextInt();

                System.out.println(" Nhap so b vao: ");
                int b = scanner.nextInt();
                if (a > b){
                    System.out.println(a+" " + "lon hon "+ " "+b);
                }else if (a == b){
                    System.out.println(a+" " + "bang "+ " "+b);
                }else {
                    System.out.println(a+" " + "nho hon "+ " "+b);
                }
                break;
            case 3:
                System.out.println(" Nhap ten 1: ");
                String ten1 = scanner.nextLine();

                System.out.println(" Nhap ten 2: ");
                String ten2 = scanner.nextLine();

                // Kiểu equals: dùng cho kiểu reference - String
                // Kiểm tra value của biến
                // Kiểm tra vị trí của biến trong vùng nhớ
                if (ten1.equals(ten2)){
                    System.out.println("2 nguoi cung ten");
                }else {
                    System.out.println("2 nguoi khac ten");
                }

                // So sánh cho kiểu primitive - nên trong TH này sẽ chạy ko đúng
//                if (ten1 == ten2){
//                    System.out.println("2 nguoi cung ten");
//                }else {
//                    System.out.println("2 nguoi khac ten");
//                }
                break;
            case 4 :
                System.out.println(" Nhap so 1: ");
                int number1 = scanner.nextInt();

                System.out.println(" Nhap so 2: ");
                int number2 = scanner.nextInt();

                System.out.println(" Nhap so 3: ");
                int number3 = scanner.nextInt();

                if (number1 > number2 && number1 > number3){
                    System.out.println("So lon nhat la: " + number1);
                }else if (number2 > number3){
                    System.out.println("So lon nhat la: " + number2);
                }else {
                    System.out.println("So lon nhat la: " + number3);
                }
                break;
            case 5:
                System.out.println(" Nhap so vao: ");
                int number4 = scanner.nextInt();
                if (10 <= number4 && number4 <= 100 ){
                    System.out.println(number4 + " là so thuộc [10,100]");
                }else {
                    System.out.println(number4 + " không thuộc khoảng [10,100]");
                }
                break;
            case 6:
                System.out.println(" Nhập điểm số vào: ");
                float number5 = scanner.nextFloat();
                if (0 < number5 && number5 < 5){
                    System.out.println("Điểm D");
                    break;
                }else if (5 <= number5 && number5 < 7.5){
                    System.out.println("Điểm C");
                } else if (7.5 <= number5 && number5 < 8.5) {
                    System.out.println("Điểm B");
                } else if (8.5 <= number5 && number5 < 10) {
                    System.out.println("Điểm A");
                }
                break;
            case 7:
                System.out.println(" Nhập tháng vào: ");
                int number6 = scanner.nextInt();
                if (number6 == 1 || number6 == 3 || number6 == 5
                        || number6 == 7 || number6 == 8 || number6 == 10
                        || number6 == 12){
                    System.out.println(" Tháng có 31 ngày");

                } else if (number6 == 2) {
                    System.out.println(" có 28 hoặc 29 ngày");

                }else if (number6 == 4||number6 == 6 || number6 == 9 || number6 == 11) {
                    System.out.println("có 30 ngày");
                } else{
                    System.out.println("Tháng không hợp lệ");
                }
                break;

        }


    }










}
