
public class students {
  int id;
  String studentName, contactInformation;

  public students(int id, String studentName, String contactInformation) {
    this.id = id;
    this.studentName = studentName;
    this.contactInformation = contactInformation;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public void setContactInformation(String contactInformation) {
    this.contactInformation = contactInformation;
  }

  public int getId() {
    return id;
  }

  public String getStudentName() {
    return studentName;
  }

  public String getContactInformation() {
    return contactInformation;
  }

}