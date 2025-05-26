package javaTester.AccModFirst;

public class Computer {

    //property
    private int ssdSize;
    String ramProductName; // không set private hay public >> mặc định là default
    protected String cpuProductName;
    public int vgaSize;

    //Method
    private void setSsdSize(int ssdSize) {
        this.ssdSize = ssdSize;
        // dùng this trong TH biến global và local trùng tên nhau.
        // Nên khi dùng hàm this nó sẽ hiểu và lấy biến global gán bằng biến local
        // muốn ko dùng hàm this >> đặt tên biến local khác vs biến global

    }
    void setRamProductName(String ramProduct) {
        ramProductName = ramProduct;
    }

    protected void setCpuProductName(String cpuProduct) {
        cpuProductName = cpuProduct;
    }

    public int getVgaSize(int i) {
        return vgaSize;
    }

    public static void main(String[] args){
        Computer comp = new Computer();
        comp.ssdSize = 500;
        System.out.println(comp.ssdSize);
        comp.setSsdSize(10);
        System.out.println(comp.ssdSize);

        //truy cập qua biến
        comp.ramProductName = "Kingdom";
        System.out.println(comp.ramProductName);

        //truy cập qua method
        comp.setRamProductName("Kingmax");
        System.out.println(comp.ramProductName);

        comp.cpuProductName = "Samsung";
        System.out.println(comp.cpuProductName);
        //
        comp.setCpuProductName("iPhone");
        System.out.println(comp.cpuProductName);

        //
        comp.vgaSize = 10000;
        System.out.println(comp.vgaSize);

        //
        comp.getVgaSize(50);
        System.out.println(comp.vgaSize);

    }
}
