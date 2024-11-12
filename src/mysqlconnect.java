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
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/infomproj","root","Virgo_Ako!20");
        
        return con;
    } catch(Exception e){
        System.out.println(e);
        return null;
    }
  }

  public static ObservableList<teachers> getDataTeachers(){

    Connection con = ConnectDB();
    ObservableList<teachers> teachersList = FXCollections.observableArrayList();
    
    try{
      PreparedStatement stmt = con.prepareStatement("select * from teachers");
      ResultSet rs = stmt.executeQuery();
      while(rs.next()){
        teachersList.add(new teachers(Integer.parseInt(rs.getString("teacher_id")), rs.getString("name"), rs.getString("courses"), rs.getString("sections"), rs.getString("venue"),  rs.getString("email")));
      }
    } catch(Exception e){
      System.out.println(e);
    }
    return teachersList;
  }

}
