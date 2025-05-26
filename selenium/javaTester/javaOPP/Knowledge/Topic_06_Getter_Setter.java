package javaTester.javaOPP.Knowledge;

public class Topic_06_Getter_Setter {
    private String personName;
    private int personAge;
    private int phoneNumber;
    public void showPersonInfor(){
        System.out.println("Person Name: " + this.personName);
        System.out.println("Person Age: " + this.personAge);
        System.out.println("Phone Number: " + this.phoneNumber);
    }

    //Mục đích sử dụng getter và setter: để đảm bảo tình đóng gói dữ liệu
    //Getter và Setter thường đi với biến private - giúp biến private gán là lấy dữ liệu
    //Giúp thuộc tính private của một Class sẽ ko thể truy xuất hoặc chỉ được phép đọc thông qua Getter và chỉ cho ghi/gán thông qua Setter
    //Setter: dùng để validate dliệu
    //Getter: ko cho phép class bên ngoài truy cập vào biến private nếu ko dùng hàm Getter
    //>> Getter và Setter: kiểm soát cách Class bên ngoài truy cập tới một dữ liệu quan trọng và cách cập nhật giá trị cho dữ lệu đó
    //Không nên để biến là public mà vẫn dùng Getter/Setter
    //Nên để biến là private để các Class bên ngoài ko thể truy cập trực tiếp nó mà phải thông qua Getter/Setter

    // Setter
    public void setPersonName(String personName) {
        //Trong điều kiện hàm IF không được truyền this.personName mà phải truyền personName
        //Vì this.personName là chỉ biến global không phải biến truyền vào của hàm setPersonName
        // Mà biến personName mới là biến truyền vào của hàm setPersonName >> đây mới là biến cần check điều kiện
       if (personName == null || personName.isEmpty() || personName.isBlank()) {
           throw new IllegalArgumentException("Person name không hợp lệ");
       }else {
           this.personName = personName;
       }
    }

    public String getPersonName() {
        return this.personName;
    }
    public void setPersonAge(int personAge) {
        if (personAge > 15 && personAge < 60) {
            throw new IllegalArgumentException("Person age không hợp lệ");
        }else {
            this.personAge = personAge;
        }
    }
    public int getPersonAge() {
        return this.personAge;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        if (!String.valueOf(phoneNumber).startsWith("0")) {
            throw new IllegalArgumentException("SĐT phải bắt đầu bằng: 09 - 03...");
        } else if (phoneNumber < 10 || phoneNumber > 11) {
            throw new IllegalArgumentException("SĐT phải từ 10 - 11 số");
        }else {
            this.phoneNumber = phoneNumber;
        }
    }

}
