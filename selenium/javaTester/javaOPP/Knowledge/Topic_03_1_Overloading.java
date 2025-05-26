package javaTester.javaOPP.Knowledge;

public class Topic_03_1_Overloading {
    static int plusMethod(int a, int b) {
        return a + b;
    }

    static double plusMethod(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        plusMethod(1, 2);
        plusMethod(2.5, 7.8);
    }
}
