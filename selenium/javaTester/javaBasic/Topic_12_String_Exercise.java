package javaTester.javaBasic;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Scanner;

public class Topic_12_String_Exercise {
    String checkTest = "Automation Testing ADvance 30T4 1975";

    @Test
    public void TC_01_Count_UpperCase() {
        char[] checkTestArr = checkTest.toCharArray();
        int countUpper = 0;
        int countLower = 0;
        int countNumber = 0;

        for(char character: checkTestArr) {
            //Count upper case
            if (character <= 'Z' && character >= 'A') {
                countUpper++;
            }

            //Count lower case
            if(character <= 'z' && character >= 'a') {
                countLower++;
            }

            //Count number
            if (character <= '9' && character >= '0'){
                countNumber++;
            }
        }
        System.out.println("Count Upper: " + countUpper);
        System.out.println("Count Lower: " + countLower);
        System.out.println("Count Number: " + countNumber);

    }

    @Test
    public void TC_02_Count_LowerCase() {
        char[] checkTestArr = checkTest.toCharArray();

        int count = 0;
        for ( char c: checkTestArr) {
            if (c == 'a'){
                count++;
            }
        }
        System.out.println("Coun a: " + count);

        if (checkTest.startsWith("Automation")){
            System.out.println(true);
        }else {
            System.out.println(false);
        }

        //cách khác
        System.out.println(checkTest.startsWith("Automation"));

        if (checkTest.contains("Testing")){
            System.out.println(true);
        }else {
            System.out.println(false);
        }

        if (checkTest.endsWith("Online")){
            System.out.println(true);
        }else {
            System.out.println(false);
        }


        int index = checkTest.indexOf("ADvance");
        System.out.println("Vị trí: " + index);
    }

    @Test
    public void TC_03_Reverse() {
        char[] checkTestArr = checkTest.toCharArray();

        for (int i = checkTestArr.length - 1; i >= 0; i--) {
            System.out.println(checkTestArr[i]);
        }

    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập SĐT vào: ");
        String phoneNumber = sc.nextLine();

        //Convert int sang String để check chữ số đầu tiên
        //Check chữ số đầu tiên có thể dùng charAt(0) hoặc startsWith("7")

       // String phoneNumberS = String.valueOf(phoneNumber);
        if ((phoneNumber.startsWith("7") || phoneNumber.startsWith("8")
                || phoneNumber.startsWith("9"))
                && phoneNumber.length() <= 10){
            System.out.println("SĐT đúng định dạng");
        }else {
            System.out.println("SĐT sai định dạng");
        }
    }
}
