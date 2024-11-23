import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class mysqlconnect {

  // public static Connection ConnectDB() {
  //     try{
  //       Class.forName("com.mysql.cj.jdbc.Driver");

  //       //Put your url, user, and password for your database
  //       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/infomdb","root","Virgo_Ako!20");
        
  //       return con;
  //   } catch(Exception e){
  //       System.out.println(e);
  //       return null;
  //   }
  // }

  public static Connection ConnectDB() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");

      // String url =
      // "jdbc:mysql://51.79.175.191:3456/default?useSSL=false&allowPublicKeyRetrieval=true";
      String url = "jdbc:mysql://51.79.175.191:3456/default";
      // Put your url, user, and password for your database
      Connection con = DriverManager.getConnection(
          url,
          "mysql",
          "fh4SKSO8VF1ENA7mUgCZBXCPMElwRBGNkQf62N9BQxW5EcYm9Hs7feq8KjLQIBLY");
      return con;
    } catch (Exception e) {
      System.out.println("MYSQL CONNECT ERROR");
      System.out.println(e);
      return null;
    }
  }

  public static ObservableList<students> getDataStudents(){

    Connection con = ConnectDB();
    ObservableList<students> studentsList = FXCollections.observableArrayList();
    
    try{
      PreparedStatement stmt = con.prepareStatement("select * from students");
      ResultSet rs = stmt.executeQuery();
      while(rs.next()){
        studentsList.add(new students(Integer.parseInt(rs.getString("student_id")), rs.getString("student_name"), rs.getString("contact_information")));
      }
    } catch(Exception e){
      System.out.println(e);
    }
    return studentsList;
  }

  public static ObservableList<teachers> getDataTeachers(){

    Connection con = ConnectDB();
    ObservableList<teachers> teachersList = FXCollections.observableArrayList();
    
    try{
      PreparedStatement stmt = con.prepareStatement("select * from teachers");
      ResultSet rs = stmt.executeQuery();
      while(rs.next()){
        teachersList.add(new teachers(Integer.parseInt(rs.getString("teacher_id")), rs.getString("name"), rs.getString("department"), rs.getString("teacher_email")));
      }
    } catch(Exception e){
      System.out.println(e);
    }
    return teachersList;
  }

  public static ObservableList<courses> getDataCourses(){

    Connection con = ConnectDB();
    ObservableList<courses> coursesList = FXCollections.observableArrayList();
    
    try{
      PreparedStatement stmt = con.prepareStatement("select * from courses");
      ResultSet rs = stmt.executeQuery();
      while(rs.next()){
        coursesList.add(new courses((rs.getString("course_id")), rs.getString("course_code"), Integer.parseInt(rs.getString("course_units"))));
      }
    } catch(Exception e){
      System.out.println(e);
    }
    return coursesList;
  }



    public static ObservableList<sections> getSectionsDetails() {

      Connection con = ConnectDB();
      ObservableList<sections> sectionsList = FXCollections.observableArrayList();


      String sql = "SELECT " +
      "sec.class_id AS 'Section ID', " +
      "c.course_code AS 'Course Code', " +
      "secd.section_code AS 'Section Code', " +
      "t.name AS 'Professor', " +
      "secd.section_schedule AS 'Schedule', " +
      "secd.section_venue AS 'Room', " +
      "COUNT(r.student_id) AS 'Amount of Students', " +
      "sec.section_capacity AS 'Max Capacity' " +
      "FROM " +
      "courses c " +
      "JOIN " +
      "sections sec ON c.course_id = sec.course_id " +
      "JOIN " +
      "section_details secd ON sec.class_id = secd.class_id " +
      "LEFT JOIN " +
      "records r ON sec.class_id = r.section_id " +
      "JOIN " +
      "teachers t ON sec.teacher_id = t.teacher_id " +
      "GROUP BY " +
      "sec.class_id, c.course_code, secd.section_code, t.name, secd.section_schedule, secd.section_venue, sec.section_capacity " +
      "ORDER BY sec.class_id, c.course_code";

      try {
          PreparedStatement pst = con.prepareStatement(sql);
          ResultSet rs = pst.executeQuery();
          while (rs.next()) {
              sectionsList.add(new sections(
                  rs.getInt("Section ID"),
                  rs.getString("Course Code"),
                  rs.getString("Section Code"),
                  rs.getString("Professor"),
                  rs.getString("Schedule"),
                  rs.getString("Room"),
                  rs.getInt("Amount of Students"),
                  rs.getInt("Max Capacity")
              ));
          }
      } catch (Exception e) {
          System.out.println(e);
      }

      return sectionsList;
    }

    public static ObservableList<StudentRecord> getStudentRecords(int studentID) {
      Connection conn = ConnectDB();
      ObservableList<StudentRecord> list = FXCollections.observableArrayList();


      String sql = "SELECT " +
        "c.course_code AS 'Course', " +
        "secd.section_code AS 'Section', " +
        "secd.section_teacher AS 'Faculty', " +
        "secd.section_schedule AS 'Schedule', " +
        "secd.section_venue AS 'Room', " +
        "COALESCE(SUM(sub.grade * (cs.submission_percentage / 100)), 0) AS 'Grade', " +
        "c.course_units AS 'Units' " +
        "FROM " +
        "students s " +
        "JOIN " +
        "records r ON r.student_id = s.student_id " +
        "JOIN " +
        "sections sec ON r.section_id = sec.class_id " +
        "JOIN " +
        "courses c ON sec.course_id = c.course_id " +
        "JOIN " +
        "section_details secd ON sec.class_id = secd.class_id " +
        "LEFT JOIN " +
        "submissions sub ON r.student_id = s.student_id AND r.section_id = sub.section_id " +
        "LEFT JOIN " +
        "course_syllabus cs ON sec.course_id = cs.course_id AND sub.submission_type = cs.submission_type_id " +
        "WHERE " +
        "s.student_id = ? " +
        "GROUP BY " +
        "c.course_code, secd.section_code, secd.section_teacher, secd.section_schedule, secd.section_venue, c.course_units";

                
      try {
          PreparedStatement pst = conn.prepareStatement(sql);
          pst.setInt(1, studentID); // Bind the student ID
          ResultSet rs = pst.executeQuery();
          while (rs.next()) {
              list.add(new StudentRecord(
                  rs.getString("Course"),
                  rs.getString("Section"),
                  rs.getString("Faculty"),
                  rs.getString("Schedule"),
                  rs.getString("Room"),
                  rs.getFloat("Grade"),
                  rs.getInt("Units")
              ));
          }
      } catch (Exception e) {
          System.out.println(e);
      } finally {
          try {
              if (conn != null) conn.close();
          } catch (Exception e) {
              System.out.println(e);
          }
      }
      return list;
  }

  public static ObservableList<TeacherRecord> getTeacherRecords(int teacherID) {
    Connection conn = ConnectDB();
    ObservableList<TeacherRecord> list = FXCollections.observableArrayList();

    String sql = "SELECT " +
      "c.course_code AS 'Course', " +
      "secd.section_code AS 'Section', " +
      "secd.section_schedule AS 'Schedule', " +
      "secd.section_venue AS 'Venue', " +
      "c.course_units AS 'Units' " +
      "FROM " +
      "teachers t " +
      "JOIN " +
      "sections sec ON sec.teacher_id = t.teacher_id " +
      "JOIN " +
      "courses c ON sec.course_id = c.course_id " +
      "JOIN " +
      "section_details secd ON sec.class_id = secd.class_id " +
      "WHERE " +
      "t.teacher_id = ?";

    try {
      PreparedStatement pst = conn.prepareStatement(sql);
      pst.setInt(1, teacherID); // Bind the teacher ID
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        list.add(new TeacherRecord(
            rs.getString("Course"),
            rs.getString("Section"),
            rs.getString("Schedule"),
            rs.getInt("Units"),
            rs.getString("Venue")
        ));
      }
    } catch (Exception e) {
      System.out.println(e);  
    } finally {
      try {
        if (conn != null) conn.close();
      } catch (Exception e) {
        System.out.println(e);
      }
    }
    return list;
  }

  public static ObservableList<classRecord> getClassRecords(int classID) {
    Connection conn = ConnectDB();
    ObservableList<classRecord> list = FXCollections.observableArrayList();

    String sql = "SELECT " +
      "s.student_id AS 'Student ID', " +
      "s.student_name AS 'Student Name', " +
      "s.contact_information AS 'Email', " +
      "COALESCE(SUM(sub.grade * (cs.submission_percentage / 100)), 0) AS 'Grade' " +
      "FROM " +
      "records r " +
      "JOIN " +
      "students s ON r.student_id = s.student_id " +
      "JOIN " +
      "sections sec ON r.section_id = sec.class_id " +
      "JOIN " +
      "submissions sub ON r.student_id = s.student_id AND r.section_id = sub.section_id " +
      "JOIN " +
      "course_syllabus cs ON sec.course_id = cs.course_id AND sub.submission_type = cs.submission_type_id " +
      "WHERE " +
      "sec.class_id = ? " +
      "GROUP BY " +
      "r.student_id, s.student_name, s.contact_information";

    try {
      PreparedStatement pst = conn.prepareStatement(sql);
      pst.setInt(1, classID); // Bind the class ID
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        list.add(new classRecord(
          rs.getInt("Student ID"),
            rs.getString("Student Name"),
            rs.getString("Email"),
            rs.getFloat("Grade")
        ));
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      try {
        if (conn != null) conn.close();
      } catch (Exception e) {
        System.out.println(e);
      }
    }
    return list;
  }
  
  public static ObservableList<CourseRecord> getCourseRecords(String courseID) {
    Connection conn = ConnectDB();
    ObservableList<CourseRecord> list = FXCollections.observableArrayList();

    String sql = "SELECT " +
      "c.course_code AS 'Course Code', " +
      "secd.section_code AS 'Section Code', " +
      "secd.section_teacher AS 'Professor', " +
      "secd.section_schedule AS 'Schedule', " +
      "secd.section_venue AS 'Room', " +
      "COUNT(r.student_id) AS 'Amount of Students', " +
      "sec.section_capacity AS 'Max Capacity' " +
      "FROM " +
      "courses c " +
      "JOIN " +
      "sections sec ON c.course_id = sec.course_id " +
      "JOIN " +
      "section_details secd ON sec.class_id = secd.class_id " +
      "LEFT JOIN " +
      "records r ON sec.class_id = r.section_id " +
      "WHERE " +
      "c.course_id = ? " +
      "GROUP BY " +
      "c.course_code, secd.section_code, secd.section_teacher, secd.section_schedule, secd.section_venue, sec.section_capacity";

    try {
      PreparedStatement pst = conn.prepareStatement(sql);
      pst.setString(1, courseID); // Bind the course ID
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        list.add(new CourseRecord(
          rs.getString("Course Code"),
          rs.getString("Section Code"),
          rs.getString("Professor"),
          rs.getString("Schedule"),
          rs.getString("Room"),
          rs.getInt("Amount of Students"),
          rs.getInt("Max Capacity")
        ));
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      try {
        if (conn != null) conn.close();
      } catch (Exception e) {
        System.out.println(e);
      }
    }

    return list;
  }

  public static ObservableList<studentCourseGrades> getStudentCourseGrades(int studentID) {
    Connection conn = ConnectDB();
    ObservableList<studentCourseGrades> list = FXCollections.observableArrayList();

    String sql = "SELECT" + 
    "cs.submission_percentage AS 'Percentage Weight', " + 
    "sub.grade AS 'Student Grade', " +
    "cs.submission_type_name AS 'Submission Type', " +
    "FROM" + 
    "course_syllabus cs " + 
    "LEFT JOIN" +  
    "courses c ON cs.course_id = c.course_id" +
    "LEFT JOIN" +  
    "sections sec ON c.course_id = sec.course_id" +
    "LEFT JOIN" + 
    "records r ON sec.class_id = r.section_id AND r.student_id = ? " +
    "LEFT JOIN" + 
    "submissions sub ON r.student_id = sub.student_id AND sec.class_id = sub.section_id AND sub.submission_type = cs.submission_type_id" +
    "WHERE" + 
    "sec.class_id = ?";

    try {
      PreparedStatement pst = conn.prepareStatement(sql);
      pst.setInt(1, studentID); // Bind the student ID
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        list.add(new studentCourseGrades(
          rs.getFloat("Percentage Weight"),
          rs.getFloat("Student Grade"),
          rs.getString("Submission Type")
        ));
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      try {
        if (conn != null) conn.close();
      } catch (Exception e) {
        System.out.println(e);
      }
    }

    return list;
  }



}
