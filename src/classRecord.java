public class classRecord {
  int studentID;
  String studentName, studentEmail;
  float grade;
  
  public int getStudentID() {
    return studentID;
  }

  public void setStudentID(int studentID) {
    this.studentID = studentID;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getStudentEmail() {
    return studentEmail;
  }

  public void setStudentEmail(String studentEmail) {
    this.studentEmail = studentEmail;
  }

  public float getGrade() {
    return grade;
  }

  public void setGrade(float grade) {
    this.grade = grade;
  }

  public classRecord(int studentID, String studentName, String studentEmail,  float grade) {
    this.studentID = studentID;
    this.grade = grade;
    this.studentName = studentName;
    this.studentEmail = studentEmail;
  }

}
