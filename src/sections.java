public class sections {
  int sectionID, amtStudents, maxStudents;
  String courseCode, sectionCode, professor, schedule, room;
  
  public sections(int sectionID, String courseCode, String sectionCode, String professor, String schedule, String room, int amtStudents, int maxStudents) {
    this.sectionID = sectionID;
    this.amtStudents = amtStudents;
    this.maxStudents = maxStudents;
    this.courseCode = courseCode;
    this.sectionCode = sectionCode;
    this.professor = professor;
    this.schedule = schedule;
    this.room = room;
  }
  public int getSectionID() {
    return sectionID;
  }
  public void setSectionID(int sectionID) {
    this.sectionID = sectionID;
  }
  public int getAmtStudents() {
    return amtStudents;
  }
  public void setAmtStudents(int amtStudents) {
    this.amtStudents = amtStudents;
  }
  public int getMaxStudents() {
    return maxStudents;
  }
  public void setMaxStudents(int maxStudents) {
    this.maxStudents = maxStudents;
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
  public String getProfessor() {
    return professor;
  }
  public void setProfessor(String professor) {
    this.professor = professor;
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

  public sections(int classID, String classCode, String professor, String schedule, int maxStudents) {
    this.sectionID = classID;
    this.courseCode = classCode;
    this.professor = professor;
    this.schedule = schedule;
    this.maxStudents = maxStudents;
  }



  


}
