package javaTester.javaOPP.Knowledge;

public class Topic_02_Exercise {

    // Khai báo các biến
    private Integer studentID;
    private String studentName;
    private Float theoryScore;
    private Float practicalScore;
   // private Float averageScore;

    //Constructor
//    public Topic_02_Exercise(Integer studentID, String studentName, Float theoryScore, Float practicalScore ){
//        this.studentID = studentID;
//        this.studentName = studentName;
//        this.theoryScore = theoryScore;
//        this.practicalScore = practicalScore;
//       // this.averageScore = averageScore;
//    }

    //Method
    //Getter và Setter
    private Integer getStudentID() {
        return studentID;
    }

    private void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    private String getStudentName() {
        return studentName;
    }

    private void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    private Float getTheoryScore() {
        return theoryScore;
    }

    private void setTheoryScore(Float theoryScore) {
        this.theoryScore = theoryScore;
    }

    private Float getPracticalScore() {
        return practicalScore;
    }

    private void setPracticalScore(Float practicalScore) {
        this.practicalScore = practicalScore;
    }

    private Float getAverageScore() {
        return (this.theoryScore + this.practicalScore*2) / 3;
    }


    // Các hàm
    //Show thông tin sinh viên
    public void showInforStudent(){
        System.out.println("Mã sinh viên: "+ getStudentID());
        System.out.println("Tên sinh viên: "+ getStudentName());
        System.out.println("Điểm lý thuyết: "+ getTheoryScore());
        System.out.println("Điểm thực hành: "+ getPracticalScore());
        System.out.println("Điểm trung bình: "+ getAverageScore());
    }

    public static void main(String[] args) {
// Cách 1:
//        Topic_02_Exercise UyenNT7 =
//                new Topic_02_Exercise(23031997, "UyenNT7",9f,9f);
//        UyenNT7.showInforStudent();
//
//        System.out.println("==============================");
//
//        Topic_02_Exercise ThienNT8 =
//                new Topic_02_Exercise(23081999,"ThienNT8", 9f, 8.5f, (9f+8.5f*2)/3 );
//        ThienNT8.showInforStudent();
//
//        System.out.println("===============================");
//        Topic_02_Exercise NguyenNT6 = new
//                Topic_02_Exercise(2006, "NguyenNT6", 8.8f, 7f,(8.8f+7f*2)/3 );
//        NguyenNT6.showInforStudent();

       // Cách 2:
       Topic_02_Exercise firstStudent = new Topic_02_Exercise();
       Topic_02_Exercise secondStudent = new Topic_02_Exercise();
       Topic_02_Exercise thirdStudent = new Topic_02_Exercise();

       //Infor of first student
        firstStudent.setStudentID(1997);
        firstStudent.setStudentName("UyenNT");
        firstStudent.setTheoryScore(5.0f);
        firstStudent.setPracticalScore(7.0f);
        firstStudent.showInforStudent();

        System.out.println("=======================");
        //Infor of secondStudent
        secondStudent.setStudentID(1997);
        secondStudent.setStudentName("ThienNT     ");
        secondStudent.setTheoryScore(9.0f);
        secondStudent.setPracticalScore(7.0f);
        secondStudent.showInforStudent();


    }


}
