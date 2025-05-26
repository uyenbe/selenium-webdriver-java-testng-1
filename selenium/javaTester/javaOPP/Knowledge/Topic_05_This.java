package javaTester.javaOPP.Knowledge;

public class Topic_05_This {
    private int firstNumber;
    private int secondNumber;

    public Topic_05_This(int firstNumber, int secondNumber) {
        //TH biến local và global trong class không trùng tên
//        firstNumber = fNumber;
//        secondNumber = sNumber;

        //TH biến global và local trùng tên
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;

    }

    //this: từ constructor này gọi qua constructor kia
    public Topic_05_This(){
       //this ko được đứng sau bất kỳ dòng code này
        //luôn đứng ở đầu
        this(10, 15);
    }

    //
    public void sumNumber(){
        System.out.println(this.firstNumber + this.secondNumber);
    }
    //this gọi method của lớp hiện tại >> thực tế hay gọi trực tiếp hơn là dùng this
    public void showNumber(){
        this.sumNumber();
    }

    //this: trả về instance của lớp hiện tại
    //this: được truyền như 1 tham số trong phương thức
    public static void main(String[] args) {
        //this: Truy cập đến/tham chiếu biến global bên trong class hiện tại
        Topic_05_This topic = new Topic_05_This(1, 2);
        topic.sumNumber();




        //supper: Demo BaseOPP
        // Truy cập đến Class cha: biến/method/constructor
    }

}
