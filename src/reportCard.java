public class reportCard {
  int student_id, course_units;
  String course_code, course_title;
  float course_grade;

  public reportCard(int student_id, String course_code, String course_title, int course_units, float course_grade) {
    this.student_id = student_id;
    this.course_code = course_code;
    this.course_title = course_title;
    this.course_units = course_units;
    this.course_grade = course_grade;
  }

  public void setStudentID(int id) {
    this.student_id = id;
  }

  public int getStudentID() {
    return this.student_id;
  }

  public void setCourseCode(String code) {
    this.course_code = code;
  }

  public String getCourseCode() {
    return this.course_code;
  }

  public void setCourseTitle(String title) {
    this.course_title = title;
  }

  public String getCourseTitle() {
    return this.course_title;
  }

  public void setCourseUnits(int units) {
    this.course_units = units;
  }

  public int getCourseUnits() {
    return this.course_units;
  }

  public void setCourseGrade(float grade) {
    this.course_grade = Math.round(grade * 100.0f) / 100.0f;
  }

  public float getCourseGrade() {
    return this.course_grade;
  }


}
