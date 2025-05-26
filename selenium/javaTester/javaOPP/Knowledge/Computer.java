package javaTester.javaOPP.Knowledge;

public abstract class Computer {

    //Normal method
    public void showComputerPerfomance() {
        System.out.println("showComputerPerfomance");
    }

    //Abstract method
    //Khung để cho các Class con kế thừa nó phải override lại(impliment lại)
    public abstract void showComputerRam ();
}
