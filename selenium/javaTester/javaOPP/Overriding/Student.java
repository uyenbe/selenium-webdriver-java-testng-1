package javaTester.javaOPP.Overriding;

public class Student extends Person implements IWork{

    @Override
    public void eat(){
        System.out.println("Suất cơm 5k");
    }

    @Override
    public void workingTime() {
        System.out.println("Học 5h/ngày");
    }
    @Override
    public void sleep(){
        System.out.println("5h");
    }
}
