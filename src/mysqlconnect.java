import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class mysqlconnect {

  public static Connection ConnectDB() {
      try{
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Put your url, user, and password for your database
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/infomdb","root","Virgo_Ako!20");
        
        return con;
    } catch(Exception e){
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
        teachersList.add(new teachers(Integer.parseInt(rs.getString("teacher_id")), rs.getString("name"), rs.getString("department")));
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
        coursesList.add(new courses(Integer.parseInt(rs.getString("course_id")), rs.getString("course_code"), Integer.parseInt(rs.getString("course_units"))));
      }
    } catch(Exception e){
      System.out.println(e);
    }
    return coursesList;
  }




}
