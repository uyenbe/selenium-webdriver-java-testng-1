package javaTester.javaOPP.Inheritance;

public class Topic_07_Inheritance_KeThua {
    //Class con kế thừa Class cha thì có thể truy cập và sử dụng phương thức và thuộc tính của Class cha
    // Sử dụng từ khoá extends
    // Class con ko được thừa hưởng các thuộc tính và phương thức private từ Class cha
    // Muốn truy xuất đến các thuộc tính private của Class cha thì phải thông qua các hàm getter/setter của lớp cha
    // Constructor của Class con luôn gọi đến Constructor của Class cha trước rồi mới gọi đến constructor của Class con
    //Nếu ko chỉ rõ gọi đến constructor nào thì luôn gọi tới constructor mặc định của Class cha
    // Sử dụng từ khoá super để chi cho Class con biết cần gọi tới  constructor nào của Class cha (TH Class cha có >1 constructor)

    // Ưu điểm:
    //1. Sử dụng lại được các thuộc tính/phương thức có sẵn từ các Class khác mà ko phải xây dựng lại
    // >> giảm chi ph phát triển và bảo trì
    //2. Tránh việc trùng lặp, dư thừa code ở nhiều Class
    //3. Các Class con sẽ tuân thủ theo 1 giao diện - interface nhất định
    //4. Cơ sở để tạo ra các Class libraries

    //Nhược điểm:
    //1. Các chức năng đợc kế thừa sẽ hoạt động chậm hơn chức năng bình thường - vì nó lấy gián tiếp từ Class cha chứ ko phải lấy trực tiếp từ Class con
    //2. Các data trong Class cha ko được sử dụng sẽ dẫn đến lãng phí bộ nhớ
    //3. Làm tăng sự kết nối giữa Class cha và Class con - TH Class cha thay đổi sẽ ảnh hưởng đến Class con
    //4. Nếu ko sử dụng đúng cách sẽ dẫn đến các cách design sai

    //Các kiểu kế thừa
    //- Trong Java ko hỗ trợ đa kế thừa (1 Class kế thừa nhiều Class cùng lúc) và kế thừa lai (Hybrid inheritance)
    //Hỗ trợ 3 kiểu kế thừa:
    //1. Single level - Đơn kế thừa: 1 Class chỉ kế thừa 1 Class khác
    //2. Multiple level - Kế thừa nhiều cấp: lớp kế thừa được tạo ra từ 1 lớp kế thừa khác
    //3. Hierarchical - Kế thừa thứ bậc - nhiều lớp con kế thừa 1 Class cha


}

