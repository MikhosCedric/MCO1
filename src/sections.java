public class sections {
  int class_id, teacher_id;
  String section_code, course_code, section_teacher, section_venue, section_schedule;

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

  public sections(int class_id, String course_code, int teacher_id, String section_schedule) {
    this.class_id = class_id;
    this.course_code = course_code;
    this.teacher_id = teacher_id;
    this.section_schedule = section_schedule;
  }
}
