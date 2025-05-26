package javaTester.javaOPP.Knowledge;

public interface IComputer {
    //Normal method mà không có phần thân và không gán từ khoá abstract thì Interface tự hiểu đây là 1 hàm abstract
    public void showComputerPerfomance() ;

    //Abstract method
    //Khung để cho các Class con kế thừa nó phải override lại(impliment lại)
    public abstract void showComputerRam ();
}
