package javaTester.AccModSecond;

import javaTester.AccModFirst.Computer;

public class PC extends Computer {
    public void showSsdSize(){
        // Biến private
      //  System.out.println(ssdSize);// không truy cập được

        // kế thừa class có biến default >> không truy cập được
        //truy cập qua biến
//        comp.ramProductName = "Kingdom";
//        System.out.println(comp.ramProductName);
//
//        //truy cập qua method
//        comp.setRamProductName("Kingmax");
//        System.out.println(comp.ramProductName);

        //bến protected
        cpuProductName = "Samsung";
        System.out.println(cpuProductName);
        //
        setCpuProductName("ADDM");
        System.out.println(cpuProductName);

        // Biến public
        vgaSize = 10000;
        System.out.println(vgaSize);

        //
        getVgaSize(50);
        System.out.println(vgaSize);
    }
}
