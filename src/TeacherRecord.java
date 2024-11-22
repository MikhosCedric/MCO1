public class TeacherRecord {

  String Course, Section, Venue, Schedule;

  int Units;

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

  public int getUnits() {
    return Units;
  }

  public void setUnits(int units) {
    Units = units;
  }

  public TeacherRecord(String course, String section, String venue, int units, String schedule) {
    this.Course = course;
    this.Section = section;
    this.Venue = venue;
    this.Schedule = schedule;
    this.Units = units;
  }

  
}
