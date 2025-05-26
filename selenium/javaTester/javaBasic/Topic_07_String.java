package javaTester.javaBasic;

public class Topic_07_String {
    public static void main(String[] args) {
        String firstname = "Nguyễn";
        String lastname = "Uyến";
        //Các cách ghép chuỗi fullname:
        //1. Cộng các biến firstname và lastname
        String fullname = firstname +" "+lastname ;
        System.out.println(fullname);

        //2. Dùng hàm concat() để nối chuỗi
        fullname = firstname.concat(" ").concat(lastname) ;
        System.out.println(fullname);

        // Cách nối biến vào chuỗi: cộng biến vào như bình thường giữa các chuỗi nhỏ
        String text = "Hello " + fullname + " login";
        System.out.println(text);


    }
}
