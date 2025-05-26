package javaTester.javaOPP.Knowledge;


//Abstract Class >> Class khác ko new được mà chỉ được kế thừa
public abstract class Topic_04_2_Abstract {
    //Varibale >> ko có Abstract Variable
    String annimalName = "Dog";

    //Method
    //Khoong có phần thân
    //Kết hợp được với Access Modifier: public, protected
    //Băt buộc các class con phải override lại
    public abstract void setAnnimalName();


}
