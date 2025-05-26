package javaTester.javaBasic;

import java.util.Scanner;

public class Topic_07_Switch_case_Exercise {
    public static void main(String[] args) {
        System.out.println("TC_01: Nhập số - In chữ Tiếng Anh");
        System.out.println("TC_02: Nhập số - Tính toán");
        System.out.println("TC_03: Nhập tháng - Hiển thị số ngày của tháng");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số TC cần test: ");
        String numberTC = scanner.nextLine();
        switch (numberTC) {
            case "TC_01":
                System.out.println(" Nhập số vào từ 1 đến 5: ");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        System.out.println("One");
                        break;
                    case 2:
                        System.out.println("Two");
                        break;
                    case 3:
                        System.out.println("Three");
                        break;
                    case 4:
                        System.out.println("Four");
                        break;
                    case 5:
                        System.out.println("Five");
                        break;
                    default:
                        System.out.println("Số không nằm trong phạm vi");
                        break;

                }
                break;
            case "TC_02":
                System.out.println("Nhập số a: ");
                int numberA = scanner.nextInt();

                System.out.println("Nhập số b:");
                int numberB = scanner.nextInt();

                System.out.println(" Nhập phép tính vào: ");
                String operation = scanner.next();

                switch (operation) {
                    case "+":
                        System.out.println("a+b = " + (numberA + numberB));
                        break;
                    case "-":
                        System.out.println("a-b= " + (numberA - numberB));
                        break;
                    case "*":
                        System.out.println("a*b = " + (numberA * numberB));
                        break;
                    case "/":
                        System.out.println("a/b= " + (numberA / numberB));
                        break;
                    default:
                        System.out.println(" Phép tính không nằm trong phạm vi");
                        break;
                }
                break;
            case "TC_03":
                int month = scanner.nextInt();
                switch (month) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        System.out.println("Tháng có 31 ngày");
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        System.out.println("Tháng có 30 ngày");
                        break;
                    case 2:
                        System.out.println(" Tháng có 28 hoặc 29 ngày");
                        break;
                    default:
                        System.out.println("Tháng không đúng");
                        break;
                }
        }
    }
}
