public class courses {

  int courseID, courseUnits;

  String courseName, courseSection, courseLocation, courseTeacher;

  public courses(int courseID, String courseName, int courseUnits, String courseSection, String courseLocation, String courseTeacher) {
    this.courseID = courseID;
    this.courseName = courseName;
    this.courseSection = courseSection;
    this.courseLocation = courseLocation;
    this.courseTeacher = courseTeacher;
    this.courseUnits = courseUnits;
  }

  public void setCourseID(int courseID) {
    this.courseID = courseID;
  }

  public void setCourseUnits(int courseUnits) {
    this.courseUnits = courseUnits;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public void setCourseSection(String courseSection) {
    this.courseSection = courseSection;
  }

  public void setCourseLocation(String courseLocation) {
    this.courseLocation = courseLocation;
  }

  public void setCourseTeacher(String courseTeacher) {
    this.courseTeacher = courseTeacher;
  }

  public int getCourseID() {
    return courseID;
  }

  public int getCourseUnits() {
    return courseUnits;
  }

  public String getCourseName() {
    return courseName;
  }

  public String getCourseSection() {
    return courseSection;
  }

  public String getCourseLocation() {
    return courseLocation;
  }

  public String getCourseTeacher() {
    return courseTeacher;
  }
  
}
