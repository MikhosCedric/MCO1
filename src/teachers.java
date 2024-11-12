public class teachers {
  
  int id;

  String name, email, course, section, location;

  public teachers(int id, String name,  String course, String section, String location, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.course = course;
    this.section = section;
    this.location = location;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public void setSection(String section) {
    this.section = section;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getCourse() {
    return course;
  }

  public String getSection() {
    return section;
  }

  public String getLocation() {
    return location;
  }

  
  
}
