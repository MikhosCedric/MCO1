public class CourseRecord {
  String courseCode, sectionCode, teacher, schedule, room;
  int amountStudents, maxAmountStudents;
  
  public CourseRecord(String courseCode, String sectionCode, String teacher, String schedule, String room,
      int amountStudents, int maxAmountStudents) {
    this.courseCode = courseCode;
    this.sectionCode = sectionCode;
    this.teacher = teacher;
    this.schedule = schedule;
    this.room = room;
    this.amountStudents = amountStudents;
    this.maxAmountStudents = maxAmountStudents;
  }
  
  public String getCourseCode() {
    return courseCode;
  }
  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }
  public String getSectionCode() {
    return sectionCode;
  }
  public void setSectionCode(String sectionCode) {
    this.sectionCode = sectionCode;
  }
  public String getTeacher() {
    return teacher;
  }
  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }
  public String getSchedule() {
    return schedule;
  }
  public void setSchedule(String schedule) {
    this.schedule = schedule;
  }
  public String getRoom() {
    return room;
  }
  public void setRoom(String room) {
    this.room = room;
  }
  public int getAmountStudents() {
    return amountStudents;
  }
  public void setAmountStudents(int amountStudents) {
    this.amountStudents = amountStudents;
  }
  public int getMaxAmountStudents() {
    return maxAmountStudents;
  }
  public void setMaxAmountStudents(int maxAmountStudents) {
    this.maxAmountStudents = maxAmountStudents;
  }


  
}
