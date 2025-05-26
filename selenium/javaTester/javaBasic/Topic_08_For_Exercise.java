package javaTester.javaBasic;

import org.testng.annotations.Test;

import java.util.Scanner;

public class Topic_08_For_Exercise {

    @Test
    public void TC_01(){
        int number = 10;
        System.out.println("Nhập số vào:");
        for (int i = 1; i <= number; i++) {
            System.out.println(i);
        }
    }

    @Test
    public void TC_03(){
        int Tong = 0;
        for (int i = 1; i <= 5; i++) {
            if (i%2 == 0){
                System.out.println("Các số chẵn là: "+ i);
                Tong = Tong + i;
            }
        }
        System.out.println("Tổng của các số chẵn: " + Tong);

    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số bài tập muốn test: ");
        int numberTC = scanner.nextInt();

        switch (numberTC) {
            case 2:
                System.out.println("Nhập số a: ");
                int a = scanner.nextInt();
                System.out.println("Nhập số b: ");
                int b = scanner.nextInt();

                for (int i = a; i <= b; i++) {
                    System.out.println(i);
                }
                break;
            case 4:
                System.out.println("Nhập số A: ");
                int numberA = scanner.nextInt();
                System.out.println("Nhập số B: ");
                int numberB = scanner.nextInt();

                int Tong = 0;

                for (int i = numberA; i <= numberB; i++) {
                    System.out.println("Các số từ A đến B là: "+ i);
                    Tong = Tong + i;
                }
                System.out.println("Tổng các số từ A đến B là: " + Tong);
                break;
            case 5:
                System.out.println("Nhập số: ");
                int numberN = scanner.nextInt();
                int tong = 0;

                for (int i = 0; i <= numberN; i++) {
                    if (i%2 != 0){
                        System.out.println("Các số lẻ là:" + i);
                        tong = tong + i;
                    }
                }
                System.out.println("Tổng các số lẻ là: " + tong);
                break;
            case 6:
                System.out.println("Nhập số A: ");
                int number1 = scanner.nextInt();

                System.out.println("Nhập số B: ");
                int number2 = scanner.nextInt();

                for (int i = number1; i <= number2; i++) {
                    if (i%3 == 0 && i % 2 != 0){
                        System.out.println("Các số lẻ là:" + i);
                    }
                }
                break;
            case 7:
                System.out.println("Nhập số vào:");
                int number3 = scanner.nextInt();
                int giaiThua = 1;

                for (int i = 1; i <= number3; i++) {
                    System.out.println("Các số để tính giai thừa là: "+ i);
                    giaiThua = giaiThua * i;
                }
                System.out.println("Giai thừa bằng: " + giaiThua);

        }

    }
}


