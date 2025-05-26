 package javaTester.javaBasic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

 public class Topic_08_For_Foreach {
     WebDriver driver;
  @Test
  public void TC_01_For(){
      for (int i = 0; i <= 5; i++){
          System.out.println(i);
      }
      //TH nên sử dụng vòng For:
      // for: dùng để chạy qua hết tất cả các giá trị và có thể dừng ở bất kỳ giá trị nào (dựa vào biến i < bao nhiêu)
      // for kết hợp với If: thoả mãn điều kiện mới action - chạy ở phần thân của for
      // xác định được số lần lặp, nếu ko xác định được thì dùng vòng while hoặc do while
      // Dùng cho mảng Array
      // Cấu trúc của For: gồm 3 vế
      // Vế 1: khai báo biến tạm, dùng để tăng giá trị lên sau mỗi lần duyệt
      // Dùng để so sánh với tổng giá trị
      // Vế 2: Biểu thức So sánh với tổng
      // Vế 3:Tăng i lên 1 đơn vị sau khi chạy vào thân vòng for

      // Giải thích
      // Lần 1:
      // i = 0
      // 0 < 5: đúng
      // in ra i
      // i++: tăng i lên 1 đơn vị (i = 1)

      System.out.println("===================================");

      // Array
      String[] cityName = {"HN", "HCM", "TB", "HP"};

      // vì mảng/list/set/queue luôn bắt đầu từ vị trí/index 0 >> phải bắt đầu từ vị trí 0
      // cityName.length >> lấy ra độ dài của mảng
      // for thông thường hoặc kết hợp với if: thoả mãn điều kiện mới action
      // có biến đếm i >> dùng để giới hạn số lần lặp, dùng như 1 điều điều kiện để filter
      for (int i = 0; i < cityName.length; i++){
          //System.out.println(cityName[i]);
          if (cityName[1].equals("HN")){
              //action
              System.out.println("Do action");
              break;
              // Dùng break để thấy giá trị cần tìm sẽ thoát vòng for luôn, không quan tâm đến các giá trị sau nữa
              // Nếu ko dùng break thì cho dù tìm thấy giá trị cần rồi nhưng vòng for vẫn chạy hết tất cả các giá trị
          }
      }

      // for each: Dùng để chạy qua tất cả các giá trị
//      for (String city : cityName){
//          System.out.println(city);
//      }

  }


  @Test
  public void TC_02_For_each(){

      // For-each:
      // Duyệt lần lượt tất cả các phần tử trong mảng từ đầu tiên đến cuối cùng
      // Nhược điểm:
      // - Không tìm được vị rí phần tử trong mảng vì nó chạy tuần tự
      // - Không chạy ngược mảng
      // - Không dùng for-each để remove một phần tử nào đó khởi List(không phải Array)
      // Vì Array không cho phép thêm/xoá phần tử chỉ có List mới được thêm/xoá phần tử
      // -
      String[] cityName = {"HN", "HCM", "TB", "HP"};

      //Java Collection
      // Class: Array/LinkedList/ Vector...
      // Interface: List/Set/Queue
      List<String> cityAddress = new ArrayList<String>();
      System.out.println(cityAddress.size());

      // Compile - add giá trị vào list khi đang coding
      cityAddress.add("HNam");
      cityAddress.add("LSon");
      System.out.println(cityAddress.size());

      //Runtime - add giá trị khi chạy hàm
      // for each: khai báo kiểu của biến trùng với kiểu của mảng/list
      for (String city : cityName){
          cityAddress.add(city);
      }
      System.out.println(cityAddress.size());

      // Java generic
      List<WebElement> links = driver.findElements(By.id(""));

      //Dùng for để xử lý dữ liệu/get text/value...
      for (WebElement link : links){
          // chuyển page
          //Refresh lại DOM/HTML
          // vòng for này sẽ bị fail

      }

  }
@Test
public static void main (String[] args) {

}









//    public static void main (String[] args) // dùng hàm main thì mới chạy được trong java
//    {
//
//    }

}
