package javaTester.javaBasic;

public class Topic_03_Compare {
        int number = 10;

    public static void main(String[] args) {

        // Biến nguyên thuỷ
        //Khởi tạo 1 vùng nhớ cho biến x
        int x = 5;

        // Khởi tạo 1 vùng nhớ cho biến y
        int y = x;

        System.out.println("x = " + x);
        System.out.println("y = " + y);

        // Set giá trị mới cho biến y
        y = 10;
        System.out.println("x sau = " + x);
        System.out.println("y sau = " + y);


        // Biến tham chiếu
        Topic_03_Compare firstVariable = new Topic_03_Compare();
        Topic_03_Compare secondVariable = firstVariable;

        System.out.println("first variable = " + firstVariable.number);
        System.out.println("second variable = " + secondVariable.number);

        //Set giá trị mới
        secondVariable.number = 15;

        System.out.println("first variable = " + firstVariable.number);
        System.out.println("second variable = " + secondVariable.number);
        // 2 biến first và second đều tham chiếu đến cùng 1 ô nhớ chứa giá trị của number nên
        // khi giá trị của number thay đổi thì giá trị của 2 biến tham chiếu vào cũng sẽ thay đổi

    }

    


}
