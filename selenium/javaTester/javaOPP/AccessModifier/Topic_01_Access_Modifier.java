package javaTester.javaOPP.AccessModifier;

public class Topic_01_Access_Modifier {
    //Lập trình hướng đối tượng có các nguyên tắc sau:
    //- Kế thừa (Inheritance):
    //- Đóng gói (Encapsulation)
    //- Đa hình (Polymorphism) - Đa hình thái
    //- Trừu tượng (Abstraction)

    //Modifier - non Modifier (phạm vi truy cập và không phải phạm vi truy cập)
    //Modifier bao gồm các từ khoá:
    //1. public
    //2. private
    //3. default
    //4. protected

    //Non modifier: gômf 7 từ khoá:
    //1. Những từ khoá hay dùng: final/static/abstract
    //2. Những từ không hay dùng: transient/volatile/native/synchronized

    //Class Modifier
    //1. Default Access: Không use public khi khởi tạo class
    //- Chỉ cho phép truy cập giữa các class cùng package
    //- Class khác package không truy cập được

    //2. Public Access: Sử dụng public khi tạo mới class
    //- Cho phép tất cả các class kể cả khác package truy cập và tạo mơi đối tượng

    //3. Final Class: Sử dung final khi tạo mới class
    //- Không cho class khác kế thừa (extends)
    //- Vẫn có thể truy câp thông qua tạo mới đối tượng

    //4. Abstract Class: Sử dụng abstract khi tạo mới class
    //- Không cho pheps tạo mới đối tượng nếu đó là 1 abstract class
    //- Cho phép kế thừa nếu đó là 1 abstract class


}
