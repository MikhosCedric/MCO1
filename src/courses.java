public class courses {

  int courseID, courseUnits;

  String courseName;

  public courses(int courseID, String courseName, int courseUnits) {
    this.courseID = courseID;
    this.courseName = courseName;
    this.courseUnits = courseUnits;
  }

  public int getCourseID() {
    return courseID;
  }

  public void setCourseID(int courseID) {
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