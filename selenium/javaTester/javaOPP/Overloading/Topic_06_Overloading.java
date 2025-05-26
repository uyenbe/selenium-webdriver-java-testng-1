package javaTester.javaOPP.Overloading;

public class Topic_06_Overloading {
    private int firstNumber;
    private int secondNumber;

    public void sumNumber(){
        System.out.println(this.firstNumber + this.secondNumber);
    }

    public void sumNumber(int firstNumber, int secondNumber){
        System.out.println(firstNumber + secondNumber);
    }

    public void sumNumber(float firstNumber, float secondNumber){
        System.out.println(firstNumber + secondNumber);
    }

    public void sumNumber(int firstNumber, float secondNumber){
        System.out.println(firstNumber + secondNumber);
    }

    // Cùng tên hàm nhưng khác số lượng tham số
    public void sumNumber(int number){
        System.out.println(number + 1000);
    }


    public static void main(String[] args) {
        //1. Overloading: Nạp chồng hàm, ghi đè
        // - thể hiện tính đa hình của dữ liệu:
        // một hàm nhưng có cùng tên hoặc cùng số lượng tham số.
        // TH cùng số lượng tham số thì tham số phải khác kiểu dữ liệu
        // TH khác số lượng tham số thì ko quan tâm ến kiểu dữ liệu
        // >> Ưu tiên check số lượng tham số trước > check kiểu dữ liệu sau
        // Phạm vi: Nằm trong Class
        //Overloading phát huy tác dụng rất lớn khi gọi đến từ các Class khác - làm tăng tính sử dụng của Class
        //Overloading: có chứa tính đa hình trong quá trình viết code - compile code
    }
}
