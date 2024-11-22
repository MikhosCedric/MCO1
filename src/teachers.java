public class teachers {
  
  int id;

  String name, email, department;

  public teachers(int id, String name, String department, String email) {
    this.id = id;
    this.name = name;
    this.department = department;
    this.email = email;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDepartment() {
    return department;
  }

  public String getEmail() {
    return email;
  }

  
  
}
