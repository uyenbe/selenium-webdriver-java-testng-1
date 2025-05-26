package javaTester.javaBasic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Topic_11_Array {
    // Array: lưu trữ các phần tử có cùng kiểu dữ liệu
    // Các phần tử của mảng được đánh chỉ số - index
    public static void main(String[] args) {

        // Khai báo mảng 1 chiều
        //int number[] = {12,7,5,6};
//        int b[] = new int[4];
//        b[0] = 3;
//        int []student = {1,3,4,5,6};
//
//        //Lấy ra phần tử đầu tiên >> dùng index
//        System.out.println(student[1]);
//        System.out.println(student[4]);

        // Lấy ra phần tử vượt ngoài mảng
//        System.out.println(student[5]);

        // Được define cố định bao nhiêu phần tử khi mình viết code
        String stName[] = {"Nam", "Long", "An"};
        //Gán lại, gán đè
        stName[0] = "Uyen";
        //Lấy ra chiều dài của mảng >> .length

        for (int i = 0; i <stName.length; i++){
        //    System.out.println(stName[i]);
            if (stName[i].equals("Uyen")){
                System.out.println(" Click vào Uyen");
            }
        }

        // hàm for-each
        for (String std : stName) {
            if (std.equals("Uyen")){
                System.out.println("Click vào Uyen ne!");
            }
        }

        //Thông thường các mảng sẽ phải define số lượng phần tử ngay từ đầu
        // Và không thể thêm sửa xoá được các phần tử
        // Nhưng mảng động: ArrayList - dạng Collection (List/Set/Queue)/ dạng Map(HashMap/HashTable)
        // thì có thể thêm sửa xoá được - không cố định số lượng phần tử
        ArrayList<String> sdtName = new ArrayList<String>();
        // Khi nào chạy code mới add vào
        for (String std: stName) {
            sdtName.add(std);
        }

        // Chuyển từ  Array sang List >> dùngArray.asList
        List<String> arrayList = Arrays.asList("Uyen 2", "Uyen 3", "Uyen 4", "Uyen 5");
        for (String name: arrayList) {
            System.out.println("Name: " + name);
        }

        // So sánh 2 mảng:
        // Dùng .equals();
        for (String std : stName) {
            if (std.equals("Uyen")){
                System.out.println("Click vào Uyen ne!");
            }
        }
        // Gán cùng 1 giá trị cho tất cả các phần tử của mảng








    }
}
