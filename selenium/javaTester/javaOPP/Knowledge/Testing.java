package javaTester.javaOPP.Knowledge;

public class Testing extends Topic_04_2_Abstract {
    public static void main(String[] args) {
        //Gọi hàm static từ ngoài class vào
        //Truy cập trực tiếp từ tên Class
        //Không cần phải tạo instance/object
        //Không nên lạm dụng khai báo biến static
        Topic_03_Variable_Property_Method.showCarColor();

        System.out.println(Topic_04_Non_Access_Modifier.browerName);

        // Nên dùng trong TH khởi tạoc các Class
        Topic_04_Non_Access_Modifier.sendkeyToElement("Link");

        //final variable
        Topic_04_Non_Access_Modifier topic = new Topic_04_Non_Access_Modifier();
        System.out.println(topic.colorCar);

        //Final method - không ghi đè được
        // public void setCarName(){}

        Topic_06_Getter_Setter topicGetter = new Topic_06_Getter_Setter();
        //topicGetter.personName = "UyenNT";
       // System.out.println(topicGetter.personName);

        //topicGetter.personAge = -10;
       // System.out.println(topicGetter.personAge);

       // topicGetter.phoneNumber = 98;
       // System.out.println(topicGetter.phoneNumber);

        //topicGetter.showPersonInfor();
        topicGetter.setPersonName(null);
        System.out.println(topicGetter.getPersonName());

        topicGetter.setPersonName("");
        System.out.println(topicGetter.getPersonName());

    }
    @Override
    public void setAnnimalName(){
        //
    }
}
