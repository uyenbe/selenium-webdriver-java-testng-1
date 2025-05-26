package javaTester.javaOPP.Overriding;

public class Testing {
    public static void main(String[] args) {
        Student s = new Student();
        s.eat();
        s.workingTime();

        Person p = new Person();
        p.eat();

        Employee e = new Employee();
        e.eat();
        e.workingTime();
    }
}
