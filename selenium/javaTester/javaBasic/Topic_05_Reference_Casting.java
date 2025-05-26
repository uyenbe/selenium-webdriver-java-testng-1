package javaTester.javaBasic;

public class Topic_05_Reference_Casting {
    public String studentName;

    public String getStudentName(){
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;

    }

    public void showStudentName(){
        System.out.println("Student Name: " + studentName);
    }

    public static void main(String[] args) {
        Topic_05_Reference_Casting fistStudent = new Topic_05_Reference_Casting();
        Topic_05_Reference_Casting secondStudent = new Topic_05_Reference_Casting();

        fistStudent.setStudentName("John");
        secondStudent.setStudentName("Jane");

        fistStudent.showStudentName();
        secondStudent.showStudentName();

        //Ép kiểu
        secondStudent = fistStudent;

        fistStudent.showStudentName();
        secondStudent.showStudentName();

        // Gán giá trị mới
        secondStudent.setStudentName("UyenNT7");

        fistStudent.showStudentName();
        secondStudent.showStudentName();





    }

}
