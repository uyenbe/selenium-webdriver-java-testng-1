package javaTester.javaBasic;

import org.testng.annotations.Test;

public class Topic_11_Array_Exercise {

    int number[] = {2,7,8,9,6,1,10,15,-1,0,-20} ;
    @Test
    public void TC_01_Find_Max_Number() {
        // Logic so sánh:
        // - Khởi tạo biến so sánh  x = 0
        // - so sánh giá trị ở vị trí [0] với biến so sánh. Nếu [0] > x thì gán giá trị biến x = [0]
        // - Tiếp tục so sánh lần lượt các giá trị ở vị trí còn lại trong mảng, cái nào lớn hơn thì gán x = cái lớn
        // - Chạy hết mảng thì in ra số lớn nhất
        int soSanh = 0;
        for (int i = 0; i < number.length; i++){
            if (soSanh < number[i]){
                soSanh = number[i];
            }
        }
        System.out.println("Số lớn nhất là: " + soSanh);

    }

    @Test
    public void TC_02_Sum_Fist_And_Last_Number() {
        int sum = 0;
        sum = number[0] + number[number.length - 1];
        System.out.println("Tổng đầu cuối là: "+ sum);
    }

    @Test
    public void TC_03_Find_So_Chan() {
       // int soSanh = 0;
        for (int i = 0; i < number.length; i++){
            if (number[i] % 2 == 0){
                System.out.println(" Số chẵn là: " + number[i]);
            }
        }
    }

    @Test
    public void TC_04_Sum_so_le() {
        int sum = 0;
        for (int j : number) {
            if (j % 2 != 0 && j > 0 && j <= 10) {
                System.out.println(" Số lẻ hợp lệ: " + j);
                sum = sum + j;
            }
        }
        System.out.println("Tổng các số lẻ hợp lệ: " + sum);
    }

    @Test
    public void TC_05_Sum_And_Average() {
        float sum = 0;
        float average = 0;

        for (int i: number) {
            sum = sum + i;
        }
        System.out.println("Tổng các số là: " + sum);

        average = sum / number.length;
        System.out.println("Trung bình cộng các số: " + average);
    }

    @Test
//    public void TC_06_Student_Infor() {
        public class Student{
            String stdName;
            String stdID;
            int age;

            public Student(String name, String ID, int age){
                this.stdName = name;
                this.stdID = ID;
                this.age = age;
            }
            public void display(){
                System.out.println("Name: " + stdName);
                System.out.println("ID: " + stdID);
                System.out.println("Age: " + age);
            }

            public void main(String[] args){
                Student[] students = new Student[3];
                students[0] = new Student("Uyen","2303", 28);
                students[1] = new Student("Huyen","2003", 28);
                students[2] = new Student("Bu","2313", 28);

                for (int i = 0; i < students.length; i++) {
                    students[i].display();
                }
            }

        }
    }

//}
