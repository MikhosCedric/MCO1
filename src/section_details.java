public class section_details {
  int class_id, teacher_id, course_id;
  String section_code, course_code, section_teacher, section_venue, section_schedule;
  
  public section_details(int class_id, int teacher_id, int course_id, String section_code, String course_code,
      String section_teacher, String section_venue, String section_schedule) {
    this.class_id = class_id;
    this.teacher_id = teacher_id;
    this.course_id = course_id;
    this.section_code = section_code;
    this.course_code = course_code;
    this.section_teacher = section_teacher;
    this.section_venue = section_venue;
    this.section_schedule = section_schedule;
  }
  
  public int getClass_id() {
    return class_id;
  }
  public void setClass_id(int class_id) {
    this.class_id = class_id;
  }
  public int getTeacher_id() {
    return teacher_id;
  }
  public void setTeacher_id(int teacher_id) {
    this.teacher_id = teacher_id;
  }
  public int getCourse_id() {
    return course_id;
  }
  public void setCourse_id(int course_id) {
    this.course_id = course_id;
  }
  public String getSection_code() {
    return section_code;
  }
  public void setSection_code(String section_code) {
    this.section_code = section_code;
  }
  public String getCourse_code() {
    return course_code;
  }
  public void setCourse_code(String course_code) {
    this.course_code = course_code;
  }
  public String getSection_teacher() {
    return section_teacher;
  }
  public void setSection_teacher(String section_teacher) {
    this.section_teacher = section_teacher;
  }
  public String getSection_venue() {
    return section_venue;
  }
  public void setSection_venue(String section_venue) {
    this.section_venue = section_venue;
  }
  public String getSection_schedule() {
    return section_schedule;
  }
  public void setSection_schedule(String section_schedule) {
    this.section_schedule = section_schedule;
  }

  
}
