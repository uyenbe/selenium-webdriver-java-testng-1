package javaTester.eclipseTips;

import org.openqa.selenium.WebDriver;

public class Topic_03_Getter_Setter {
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    // các hàm thuộc class bên ngoài topic_03 sẽ không gọi đến biến carName được vì đang set Private
    // Muốn truy cập đến carName thì thông qua các phương thức Getter và Setter
    private String carName;
    private String carType;
    private String carBrand;

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    private String carModel;

// ====================//
    WebDriver driver;

    // page contructor

    public Topic_03_Getter_Setter() {
        this.driver = driver;
    }




    public static void main(String[] args) {
        Topic_03_Getter_Setter topic_03 = new Topic_03_Getter_Setter();

    }

}
