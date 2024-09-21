package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_08_Handle_Droplist_Custom {
    WebDriver driver;

    // wait tường minh: hiển thị trạng thái cụ thể cho element:
    // Visible/ Invisible/ Presence/ Number/ Clickable/ ...
    WebDriverWait expliciWait;

    // Ham wait cua selenium đều chung 1 cơ chế:
    //- Nếu thoả mãn điều kiện rồi thì >> không cần chờ hết timeout
    //- Nếu chưa thoả mãn điều kiện thì tiếp tục chờ (cơ chế lặp lại/ check lại điều kiện sau mỗi tần số bao lâu
    // Bây giờ muốn dùng hàm wait >> phải khởi tạo >> khởi tạo sau webdriver
    // (vì driver là cái đầu tiên, cái open browser để thực hiện thao tác)
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");


    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
           System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
          //  WebDriverManager.chromedriver().setup();
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }

        driver = new ChromeDriver();
        // cách khởi tạo của expliciWait - Wait: đợi cho 1 element thoả mãn điều kiện
        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // wait ngầm định: không rõ ràng cho 1 trạng thái cụ thể nào hết của element
        // Ngầm định cho việc tìm element
        // wait này: đợi cho element được tìm thấy
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
       // driver.get("https://demo.nopcommerce.com");
        //https://demo.nopcommerce.com/register

    }
    @Test
    public void TC_00_Droplist_Custom() {
        //Để xác định được cách handle Droplist, Cần xác định xem Droplist ở dạng customize hay default. Dựa vào cấu trúc HTML của thẻ chứa Droplist
        // Dạng custom: Cấu trúc HTML khách select - option
            //Có thể là: div/ul/li/span...
        // Thao tác đối với droplist: Click vào 1 thẻ để show các item trong droplist
        // 2.1 Sẽ show hết tất cả các item trong droplist
        // 2.2 Sẽ show 1 phần và đang load thêm (TH droplist có quá nhiều item)
        // 3.1 TH item cần chọn đang hiển thị trong droplist >> click vào item
        // 3.2 TH item cần chọn không hiển thị >> 1 số TH scroll để kéo xuóng để hiện lên rồi mới chọn
        // 4 Trước khi click cần check: nếu text của item = text cần chọn >> Click vào item
        // Khi end-user tương tác lên element thì dữ liệu mới được render/load ra >> tăng trải nghiệm người dùng
        // Các cái hàm học trước trong bài này:
        //1. WebDriverWait
        //2. Vòng lặp for
        //3. Điều kiện If
        //4. break
        //5. Cách viết hàm
    }

    @Test
    public void TC_01_Register (){
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        // bước 1. Click vào droplist
        driver.findElement(By.xpath("//span[@id='number-button']")).click();
        sleepInSeconds(10);

        // 2.1 Sẽ show hết tất cả các item trong droplist >> TH có ít item
        // 2.2 Sẽ show 1 phần và đang load thêm (TH droplist có quá nhiều item)
        // Chờ cho nó show ra tất cả item trong droplist (bắt 1 locator đại diện cho tất cả các item)
        // >> dùng hàm:
        //expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("")));
        //dòng code trên Không thoả mãn điều kiện. Vì có những droplist không visible tất cả các item mà phải scroll xuống mới thấy
        // >> phải dùng hàm khác:presence
        //expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(""))); -- chờ cho đến khi element thoả mãn điều kiện
        // presence khác với visible:
        // Visible (Display/ Visibility): Nhìn thấy và thao tác được (tức là có trên UI và có trong HTML)
        // Presence: nhìn thấy/ không nhìn thấy đều thoả mãn (có thể có/ko có trên UI, nhưng bắt buộc phải có đầy đủ trong HTML)
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='number-menu']//div")));

        // Lấy ra all element trong page hiện tại < find all element within current page>
        // >> Dùng hàm List <WebElement> để lấy ra all item
        List<WebElement> allItems = driver.findElements(By.xpath("//ul[@id='number-menu']//div"));

        // Để thao tác với all item >> dùng vòng lặp và ở đây dùng vòng for: for each để dùng với list element
        // Cách viết hàm for each: khai báo biển item có kiểu dữ liệu là WebElement >> duyêt qua tất cả các item (lúc này biến item mang đầy đủ thuộc tính của kiểu WebElement)
        // >> sau mỗi lần duyệt qua thì sẽ dùng hàm getText()
        for (WebElement item : allItems) {
            // TH element click chọn xong rồi >> sẽ bị mất trong HTML thì hàm getText() sẽ bị fail
            // >> dùng beak để thoát vòng if để ko bị fail

            String textItem = item.getText();
            System.out.println("Text item = " + textItem);
            // 3.1 check text của từng item thoả mãn điều kiện >> chỉ cần click vào item

            // Sau đó dùng hàm if để check. Các loại hàm if:
            //1. if
//            int a = 10;
//            if (a < 15) {
//                // đúng thì vào trong phần thân này
//            }
//
//            //2. if - else
//            if (a < 15) {
//                // đúng thì vào trong phần thân này
//            }
//            else {
//                // đúng thì vào trong phần thân này
//            }
//
//            //3. if lồng: if - else - if - else: check nhiều điều kiện
//            if (a<15){} else if (a<9) {
//
//            }else {}
            // Đối với các kiểu dữ liệu tham chiếu: string,... >> dùng equals để so sánh. Nếu kiểu dữ liệu nguyên thuỷ: int, boolean >> dùng ==
            if (textItem.equals("8")) {
                item.click();
                break; // từ 9 > 19 không check
            }

        }

    };

    @Test
    public void TC_02_React(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        driver.findElement(By.xpath("//i[@class='dropdown icon']")).click();
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='item']//span")));
        List<WebElement> allItem = driver.findElements(By.xpath("//div[@class='item']//span"));
        for (WebElement item : allItem){
            String textItem = item.getText() ;
            System.out.println("Text Item = " + textItem);

            if (textItem.equals("Nguyen Uyen")){
                item.click();
               // break;
            }
            else if (textItem.equals("Stevie Feliciano")){
                item.click();
                break;
            }
        }

    };

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
    public void sleepInSeconds(long timeInSecond){
        try{
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        };

    }

}
