package javaTester.javaOPP.Overriding;

public class Employee extends Person implements IWork {
    @Override
    public void eat(){
        System.out.println("Suất cơm 15k");
    }

    @Override
    public void workingTime() {
        System.out.println("Làm 8h/ngày");
    }

    @Override
    public void sleep() {
        System.out.println("5h");
    }
}
