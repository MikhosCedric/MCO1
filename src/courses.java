public class courses {

  int courseUnits;

  String courseName, courseID;

  public courses(String courseID, String courseName, int courseUnits) {
    this.courseID = courseID;
    this.courseName = courseName;
    this.courseUnits = courseUnits;
  }

  public String getCourseID() {
    return courseID;
  }

  public void setCourseID(String courseID) {
    this.courseID = courseID;
  }

  public int getCourseUnits() {
    return courseUnits;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public void setCourseUnits(int courseUnits) {
    this.courseUnits = courseUnits;
  }

}