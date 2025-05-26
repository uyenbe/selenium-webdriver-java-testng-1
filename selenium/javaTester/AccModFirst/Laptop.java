package javaTester.AccModFirst;

public class Laptop {
    public static void main(String[] args){
        Computer comp = new Computer();
        //Biến private
//        comp.ssdSize = 500;
//        System.out.println(comp.ssdSize);
//        comp.setSsdSize(10);
//        System.out.println(comp.ssdSize);
    //Biến default
        //truy cập qua biến
        comp.ramProductName = "Kingdom";
        System.out.println(comp.ramProductName);

        //truy cập qua method
        comp.setRamProductName("Kingmax");
        System.out.println(comp.ramProductName);

        //Biến protected
        comp.cpuProductName = "Samsung";
        comp.setCpuProductName("iPhone");

        //Biến public
        //
        comp.vgaSize = 10000;
        System.out.println(comp.vgaSize);

        //
        comp.getVgaSize(50);
        System.out.println(comp.vgaSize);
    }
}
