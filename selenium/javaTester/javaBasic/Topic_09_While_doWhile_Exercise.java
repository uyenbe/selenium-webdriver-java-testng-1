package javaTester.javaBasic;

import org.testng.annotations.Test;

import java.util.Scanner;

public class Topic_09_While_doWhile_Exercise {

   static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TC_08();

    }
    @Test
    public static void TC_01_For() {
        System.out.println(" Nhập số:");
        int number = scanner.nextInt();
        for (int i = number; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println("Các số chẵn là: "+ i);
            }
        }

    }

    @Test
    public static void TC_02_While() {
        System.out.println(" Nhập số:");
        int number = scanner.nextInt();

        while (number < 100) {
            if (number % 2 == 0) {
                System.out.println("Các số chẵn là: "+ number);
            }
            number++; // Đưa ra ngoài if để đảm bảo luôn tăng
        }

    }

    @Test
    public static void TC_03_Do_While() {
        System.out.println(" Nhập số:");
        int number = scanner.nextInt();
        do {
            if (number % 2 == 0) {
                System.out.println("Các số chẵn là: "+ number);
            }
            number++; // Đưa ra ngoài if để đảm bảo luôn tăng
        }while (number < 100);

    }

    @Test
    public static void TC_04() {
        System.out.println(" Nhập số numberA: ");
        int numberA = scanner.nextInt();

        System.out.println("Nhập số numberB: ");
        int numberB = scanner.nextInt();


        while (numberA < numberB){
            //int i = 0;
            if (numberA%3==0 && numberA%5==0 ){
                System.out.println("Số thoả mãn: "+ numberA);

            }
            numberA++;

        }

    }

    @Test
    public static void TC_05_Sum() {
        System.out.println("Nhập số: ");
        int number = scanner.nextInt();
        int sum = 0;
        while (number > 0) {
            if (number % 2 != 0) {
                System.out.println(" Số lẻ: " + number);
                sum = sum + number;
            }
            number--; // phải là number-- vì mình đang lấy ra các số lẻ từ 0 > number nên phải giảm dần
        }
        System.out.println("Tổng các số lẻ là: " + sum);
    }

    @Test
    public static void TC_06() {
        System.out.println("Nhập số number1 vào: ");
        int number1 = scanner.nextInt();

        System.out.println("Nhập số number2 vào");
        int number2 = scanner.nextInt();

        while(number1 < number2){
            if (number1%3==0){
                System.out.println("Các số chia hết cho 3 là: "+ number1);
            }
            number1++;
        }
    }

    @Test
    public static void TC_07() {
        System.out.println("Nhập số vào: ");
        int number = scanner.nextInt();
        int giaiThua = 1;
        while (number > 0) {
            System.out.println("Số number lần lượt là:" + number);
            giaiThua = giaiThua * number;
            number--;
        }
        System.out.println("Giai thừa cần tính là: "+giaiThua);
    }

    @Test
    public static void TC_08() {
        System.out.println("Nhập số number1 vào: ");
        int number1 = scanner.nextInt();
        System.out.println("Nhập số number2 vào: ");
        int number2 = scanner.nextInt();

        while(number1 < number2){
            if (number1 % 2 == 0){
                System.out.println("Số chẵn là: " + number1);
            }
            number1++;
        }
    }








}
