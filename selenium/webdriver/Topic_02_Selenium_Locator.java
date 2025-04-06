package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //Mở trang web ra


        /*
        FirstName textbox - HTML code
        HTML Element: <tagname attribute_1 ="attribute_value">
        * <input type="text" data-val="true" data-val-required="First name is required."
        id="FirstName" name="FirstName">
        >> Tên thẻ của element (HTML tagname): input
        >> Tên của thuộc tính (Attribute name): type, data-val,..., id, name,
        >> Giá trị của thuộc tính (Attribute value): "text",...
        * */

    }
    @Test
    public void TC_01_ID() {
        driver.get("https://demo.nopcommerce.com/register");
        // tìm element có id = FirstName
        driver.findElement(By.id("FirstName"));

    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-logo"));
    }
    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("DateOfBirthDay"));
    }
    @Test
    public void TC_04_TagName() {
        driver.findElement(By.tagName("input"));
    }
    @Test
    public void TC_05_LinkText() {
        driver.findElement(By.linkText("My account"));
    }
    @Test
    public void TC_06_Partial_linkText() {
        driver.findElement(By.partialLinkText("vendor account"));
    }
    @Test
    public void TC_07_Css() {   // handel được các case ở trên
// Css với ID
        driver.findElement(By.cssSelector("input[id='FirstName']")); //input#FirstName/
// Css với Class
        driver.findElement(By.cssSelector("div[class='page-title']")); //div.page-title/.page-title
// Css với Name
        driver.findElement(By.cssSelector("input[name='FirstName")); //
// Css với Tagname
        driver.findElement(By.cssSelector("input"));
// Css với link
        driver.findElement(By.cssSelector("a[href='/shipping-returns']"));
// Css với Partial
        driver.findElement(By.cssSelector("a[href*='/shipping-returns")); //a[href*='info']
        driver.findElement(By.cssSelector("a[href*='info']"));
    }
    @Test
    public void TC_08_XPath() {
// XPath không cho viết tắt như Css
// XPath với ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));
// XPath với Class
        driver.findElement(By.xpath("//div[@class='page-title']")); //div.page-title/.page-title
// XPath với Name
        driver.findElement(By.xpath("//input[@name='FirstName']")); //
// XPath với Tagname
        driver.findElement(By.xpath("//input"));
// XPath với link
        driver.findElement(By.xpath("//a[@href='/shipping-returns']")); // Lấy full link
        driver.findElement(By.xpath("//a[text()='Addresses']")); // Lấy theo text
// XPath với Partial
        driver.findElement(By.xpath("//a[contains(@href,'addresses')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));
    }
    @Test
    public void TC_09_XPath_Tech(){
        //Link thực hành: https://automationfc.github.io/basic-form/
        //Có 2 XPath Type:
        //1. XPath tương đối: //input[@id='FirstName']
        //2. XPath tuyệt đối: /html/body/div[6]/div[3]/div/div/div/div[2]/form/div[1]/div[2]/div[2]/input
        //Các XPath Technical:
        //1. Dựa vào cú pháp của XPath tương đối: //tagname[@attribute='value']
        driver.findElement(By.xpath("//input[@id='FirstName']"));
        //2. Parent node (lấy từ cấp cha >> con): Lấy từ cấp cha để tìm ra element là duy nhất( tìm được 1 matching node)
        // Dùng parent node trong TH:
        //- Khi 1 element không thể xác định được nó là duy nhất bới các atrribute và value của nó
        //- Khi một element không có attribute nào hết
        // parent-tagname[@attribute='value']//child-tagname[@attribute='value']
        driver.findElement(By.xpath("//div[@class='form-fields']//input[@id='FirstName']"));
        //3+4. Có 2 cách lấy Xpath:
        // 3.1. Lấy tuyệt đối: text()= /string()=/ @attribute=
        driver.findElement(By.xpath("//label[text()='First name:']"));
        driver.findElement(By.xpath("//label[@for='FirstName']"));
        // 3.2. Lấy tương đối: contains()/ starts-with() trong hàm () sẽ dùng dấu phẩy chứ không dùng dấu =
        driver.findElement(By.xpath("//strong[contains(text(),'Details')]"));
        driver.findElement(By.xpath("//strong[starts-with(text(),'Your Personal')]"));
        // 5. Handle text trong XPath [Dùng nhiều nhất là text()= và contains(text(),'..')
        //5.1. Dùng hàm text()=  (để lấy tuyệt đối) trong những TH:
        //+ Text nằm cùng hàng với tagname (node), không nằm trong node con và khồng phải nested text (text lồng nhau trong cùng 1 thẻ)
        driver.findElement(By.xpath("//h1[text()='Selenium WebDriver API']"));
        //+ Text có index nằm tại node hoặc đầu/giữa/cuối của thẻ đều có thể lấy được
        driver.findElement(By.xpath("//h5[text()='Michael Jackson']"));
        //+ Lấy text tuyệt đối: tức là không có khoảng trắng ở đầu/cuối chuỗi hay tab ở đầu/cuối chuỗi, không có xuống dòng
        driver.findElement(By.xpath("//h5[text()='Michael Jackson']"));
        //5.2 Dùng Contains() - Để lấy tương đối
        //5.2.1 Dùng Contains(text(),'..') trong các TH:
        //- Text đó nằm trên chính node đó (TH text nằm trong node con >> không lấy được )
        driver.findElement(By.xpath("//h5[contains(text(),'Michael Jackson')]"));
        //- Text ở dạng NESTED NHƯNG text phải nằm ở index đầu tiên
        driver.findElement(By.xpath("//h5[contains(text(),'Hello World!')]"));
        //- Text đó phải có khoảng trắng, xuống dòng, tab ở đầu cuối của text
        driver.findElement(By.xpath("//h5[contains(text(),'Michael Jackson')]"));
        //5.2.3 Dùng hàm Contains(.,'..') trong các TH:
        //-Text nằm trên chính node đó hoặc nằm trong node con ở bất kỳ vị trí nào
        driver.findElement(By.xpath("//h5[contains(.,'Ignore')]"));
        //- Là NESTED nhưng text ở bất kỳ vị trí nào
        driver.findElement(By.xpath("//h5[contains(.,'Ignore')]"));
        //- Text có khoảng trắng, tab, xuống dòng ở đầu hoặc cuối text
        //5.2.4 Dùng hàm Contains(string(),'..') tương tự như Contains(.,'..')
        //5.2.5 Dùng hàm Concat() - Nối chuỗi
        //Dùng trong TH text chứa cả dấu nháy đơn và dấu nháy đôi.
        // TH text chứa dấu nháy đơn 'a' >> dùng nháy "a" trong concat để bọc
        // TH text chứa dấu nháy đối "b" >> dùng nháy 'b' trong concat để bọc
        //Nối chuỗi trong XPath:
        driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What',\"'s happened?\")]"));
        //span[text()=concat('Hello "John", What',"'s happened?")]
        //
        /*VD trong lập trình:
        //                String firstName = "Nguyễn";
        //                String lastName = "Uyến";
        //         //Nỗi chuỗi C1:
        //        System.out.println(firstName + " "+ lastName);
        //        // Nối chuỗi C2:
        //        System.out.println(firstName.concat(" ").concat(lastName));*/

        //6.
        // 6.1 AND vs OR
        //Link demo: https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx
        //AND chỉ cần 1 cái sai >> sẽ SAI hết
        driver.findElement(By.xpath(""));
        // OR chỉ cần 1 cái đúng >> sẽ ĐÚNG
        driver.findElement(By.xpath(""));
        //AND >> Lấy tuyệt đối
        //OR >> Lấy tương đối
        // Thực tế dùng AND nhiều hơn để lọc điêều kiện và tránh sự trùng lặp
        //6.2 NOT - Phủ định lại điều kiện
        // NOT (Điều kiện đúng) = SAI
        // NOT (Điều kiện sai) = ĐÚNG
        //Dùng NOT khi có nhiều điều kiện trùng nhau, mà mình muốn phủ định lại để lấy điều kiện mình muốn
        //div[not(@style='display:none;')]//div[@class='raDiv'] >> XPath
        driver.findElement(By.xpath("//div[not(@style='display:none;')]//div[@class='raDiv']"));
        //7.
        //7.1 Inside parent và Outside parent
        //Link demo:https://automationfc.github.io/jquery-selectable/
        //7.1.1 Inside parent: Khi các element ngang hàng nhau cùng nằm trong 1 thẻ cha
        // >> lấy theo index của element cũng được
        driver.findElement(By.xpath("//li[1]"));
        //7.1.2 Outside parent: Khi các element ngang hàng nhau không nằm trong 1 thẻ cha
        //Link demo:http://live.techpanda.org/index.php/mobile.html
        driver.findElement(By.xpath("(//span[text()='Add to Cart'])[1]"));
        //lấy ra elememt ở vị trí có index = 1 trong các thẻ cha khác nhau (phải cho tất cả vào trong ngoặc tròn)
        //7.1.3 Position() vs Last()
        //Link demo: https://automationfc.github.io/jquery-selectable/
        //Position() dùng tương tự như Inside parent
        driver.findElement(By.xpath(""));
        //last() dùng để lấy element ở vị trí cuối cùng mà không quan tâm toàn bộ có bao nhiêu element
        driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee'][last()]"));
        //8 AXES
        // - Luôn nhớ mình đang đứng ở node nào để xét tiếp
        // - Lấy cái cố định (key word) để tìm cái không cố định (thường là lấy title)
        //=>> Dùng theo cách này thì dù có thay đổi vị trí của element cũng vẫn lấy được
        // Các key words:
        //1. ancentor: node tổ tiên
        //2. parent: node cha (đi từ node con)
        //3. preceding: node bác
        //4. following: node chú
        //5. preceding-sibling: node anh
        //6. following-sibling: node em
        //7.child: node con
        //8. descendant: node cháu chắt
        //Demo: Lấy ra ngày xuất bản của 1 quyển sách bất kỳ:
        // Lấy cái cố định : Tên sách để tìm cái không cố định: Ngày xuất bản/Giá/Số trang...
        // link:https://subscription.packtpub.com/search?query=java&_gl=1*122wvo2*_ga*MTAzMzc1MDU3NC4xNzAzMDQ1NTQ3*_ga_Q4R8G7SJDK*MTcwMzA0NTU0Ni4xLjEuMTcwMzA0NjIxNy43LjAuMA..
        // div[text()='Java Programming for Complete Beginners']/ancestor::a/following-sibling::a/div[@class='product-footer']/div[1]
        //9. CSS -- Chưa hiểu gì cả
        //10. Tips and Tricks
        //10.1 Elememt Tab >> Nơi tìm kiếm các element: string/XPath/Css
        //10.2 Console Tab: Mục đích dể verify lại các giá trị + viết các đoạn code javascript + lấy các properties (thuộc tính) của element
        // Cú pháp: XPath: $x("XPath")
        // Css: $$("Css")
        //jquery: $("Css")
        //10.3 Chrome - Console Tab: để get các giá trị trong textbox/text area/ editable dropdown...
        // Lấy ra 1 giá trị vừa nhập mà chưa sent request lên server =>> Test tính năng Clear Form
        // Verify lại giá trị
        // Verify lại giá trị mà element bị ẩn/ che khuất




        // Note: nếu dùng 1 / thì chỉ tìm đến 1 node con sau node cha.
        // Nếu dùng 2 // thì tìm được bất kỳ node nào dưới node cha.
        // Hàm của XPath: tên hàm + (). VD: text(), string(), last(), contains(), starts-with()...
        // Attribute đi với aaaaaaaa
        //XPath không support với hàm ends-with()
        //Css vẫn support $=
        // Sử dụng parent node trong TH:
        //1. Node hiện tại không có sự khác nhau >> tìm đến các node cha.
        // 2. TH tìm thấy > 1 matching node.
        // 3. Tagname không có các attribute như: id/class/name
        //Handle text trong XPath: thì chỉ lấy text của thẻ đó, không lấy text của thẻ con
        //Css không support tìm Ancestor hay Preceding được vì nó không đi ngược lên để tìm được.


    }

    @AfterClass
    public void afterClass() {
       // driver.quit();
    }
}
