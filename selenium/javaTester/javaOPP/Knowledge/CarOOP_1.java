package javaTester.javaOPP.Knowledge;

public class CarOOP_1 {
    static String carCompany;
    static  String carType;
    static String fuelType;
    static Float mileAge;
    static  Double carPice;
    public static void main(String[] args) {
        carCompany = "Honda";
        carType = "City";
        fuelType = "Petrol";
        mileAge = 200f;
        carPice = 30000.0d;

        System.out.println("Car Company: " + carCompany);
        System.out.println("carType: " + carType);
        System.out.println("fuelType: " + fuelType);
        System.out.println("mileAge: " + mileAge);
        System.out.println("carPice: " + carPice);

        carCompany = "Huyndai";
        carType = "Accent";
        fuelType = "Diesel";
        mileAge = 150f;
        carPice = 50000.0d;

        System.out.println("Car Company: " + carCompany);
        System.out.println("carType: " + carType);
        System.out.println("fuelType: " + fuelType);
        System.out.println("mileAge: " + mileAge);
        System.out.println("carPice: " + carPice);

    }
}
