public class StudentRecord {
  String Course, Section, Teacher, Venue, Schedule;
  float Grade;
  int Units;

  public StudentRecord(String course, String section, String teacher, String venue, String schedule, float grade, int units) {
    this.Course = course;
    this.Section = section;
    this.Teacher = teacher;
    this.Venue = venue;
    this.Schedule = schedule;
    this.Grade = grade;
    this.Units = units;
  }

  public String getCourse() {
    return Course;
  }

  public void setCourse(String course) {
    Course = course;
  }

  public String getSection() {
    return Section;
  }

  public void setSection(String section) {
    Section = section;
  }

  public String getTeacher() {
    return Teacher;
  }

  public void setTeacher(String teacher) {
    Teacher = teacher;
  }

  public String getVenue() {
    return Venue;
  }

  public void setVenue(String venue) {
    Venue = venue;
  }

  public String getSchedule() {
    return Schedule;
  }

  public void setSchedule(String schedule) {
    Schedule = schedule;
  }

  public float getGrade() {
    return Grade;
  }

  public void setGrade(float grade) {
    Grade = grade;
  }

  public int getUnits() {
    return Units;
  }

  public void setUnits(int units) {
    Units = units;
  }




}
