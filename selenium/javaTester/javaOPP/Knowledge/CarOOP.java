package javaTester.javaOPP.Knowledge;


public class CarOOP {

    //Thuộc tính
    private String carCompany;
    private  String carType;
    private String fuelType;
    private Float mileAge;
    private  Double carPice;

    //Cách viết gọn cho hàm main bằng cách tạo contructor cho nó. Sau đó khai báo xuống hàm main
    public CarOOP(String carCompany, String carType, String fuelType, Float mileAge, Double carPice) {
        this.carCompany = carCompany;
        this.carType = carType;
        this.fuelType = fuelType;
        this.mileAge = mileAge;
        this.carPice = carPice;
    }
    protected String getCarCompany() {
        return carCompany;
    }

    protected void setCarCompany(String carCompany) {
        this.carCompany = carCompany;
    }

    protected String getCarType() {
        return carType;
    }

    protected void setCarType(String carType) {
        this.carType = carType;
    }

    protected String getFuelType() {
        return fuelType;
    }

    protected void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    protected Float getMileAge() {
        return mileAge;
    }

    protected void setMileAge(Float mileAge) {
        this.mileAge = mileAge;
    }

    protected Double getCarPice() {
        return carPice;
    }

    protected void setCarPice(Double carPice) {
        this.carPice = carPice;
    }

    protected void showCarInfor(){
        System.out.println("Car Company: " + getCarCompany());
        System.out.println("carType: " + getCarType());
        System.out.println("fuelType: " + getFuelType());
        System.out.println("mileAge: " + getMileAge());
        System.out.println("carPice: " + getCarPice());
    }

    public static void main(String[] args) {
        //Trong lập trình hướng đối tượng có thuộc tính và phương thức
        CarOOP Honda = new CarOOP("Honda","City","Gasoline",500f,23000d);
//        Honda.setCarCompany("Honda");
//        Honda.setCarType("City");
//        Honda.setFuelType("Gasoline");
//        Honda.setMileAge(500f);
            Honda.showCarInfor();

        // Tạo thêm đối tượng mới
        CarOOP toyota = new CarOOP("toyota","City","Gasoline",400f, 35000d);
//        toyota.setCarCompany("toyota");
//        toyota.setCarType("City");
//        toyota.setFuelType("Gasoline");
//        toyota.setMileAge(500f);
        toyota.showCarInfor();
    }
}
