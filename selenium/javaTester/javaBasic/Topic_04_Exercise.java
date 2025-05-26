package javaTester.javaBasic;

import org.testng.annotations.Test;

import java.util.Scanner;

public class Topic_04_Exercise {


   @Test
    public void swapNumber(){
       int a = 5;
       int b = 6;
       a = a+b; //a=11
       b = a-b;//b=11-6
       a = a-b;//a=11-5
        System.out.println(a);
        System.out.println(b);
    }



    @Test
    public void TC_02_swapNumber(){
       int a = 3;
       int b = 4;

       //Thực hiện hoán đổi
        a = a+b;
        b = a-b;
        a = a-b;
        System.out.println(a);
        System.out.println(b);

    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tinh tuoi");
        System.out.println("True/False");

       int number = scanner.nextInt();
       switch (number){
           case 1:
               System.out.println("Nhap so tuoi hien tai vao: ");
               int age = scanner.nextInt();

               // In ra màn hình số tuổi sau 15 năm
               System.out.println("So tuoi sau 15 nam la: "+ (age + 15));
               break;

           case 2:
               System.out.println("Nhap so a: ");
               int a = scanner.nextInt();

               System.out.println("Nhap so b: ");
               int b = scanner.nextInt();

               if (a > b){
                   System.out.println("true");
               }else {
                   System.out.println("false");
               }
               break;
       }
        scanner.close();
    }








    


}
