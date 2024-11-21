import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SectionsViewController implements Initializable{

  @FXML
  private Button btnSectionAdd;

  @FXML
  private Button btnSectionBack;

  @FXML
  private Button btnSectionDelete;

  @FXML
  private Button btnSectionUpdate;

  @FXML
  private TableColumn<sections, String> col_sectionCode;

  @FXML
  private TableColumn<sections, String> col_sectionCourse;

  @FXML
  private TableColumn<sections, Integer> col_sectionID;

  @FXML
  private TableColumn<sections, String> col_sectionSchedule;

  @FXML
  private TableColumn<sections, String> col_sectionTeacher;

  @FXML
  private TableColumn<sections, String> col_sectionVenue;

  @FXML
  private TableColumn<sections, String> col_sectionClassID;

  @FXML
  private TableView<sections> sectionsTableView;

  @FXML
  private TextField txt_classID;

  @FXML
  private TextField txt_sectionCode;

  @FXML
  private TextField txt_sectionCourseID;

  @FXML
  private TextField txt_sectionSchedule;

  @FXML
  private TextField txt_sectionTeacher;

  @FXML
  private TextField txt_sectionVenue;

  ObservableList<sections> sectionsList;

  int index = -1;

  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pst = null;

  private boolean courseIDExists(String courseID) {
    conn = mysqlconnect.ConnectDB();
    String sql = "SELECT COUNT(*) FROM courses WHERE course_id = ?";
    try {
      pst = conn.prepareStatement(sql);
      pst.setString(1, courseID);
      rs = pst.executeQuery();
      if (rs.next()) {
        return rs.getInt(1) > 0;
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      try {
        if (rs != null) rs.close();
        if (pst != null) pst.close();
        if (conn != null) conn.close();
      } catch (Exception e) {
        System.out.println(e);
      }
    }
    return false;
  }

  private void insertIntoSections() {
    conn = mysqlconnect.ConnectDB();
    String courseID = txt_sectionCourseID.getText();
    String courseName = getCourseName(courseID);
    
    if (courseName == null) {
      JOptionPane.showMessageDialog(null, "Course ID does not exist");
      return;
    }

    String sql = "insert into sections (class_id, course_id, teacher_id, class_schedule)values(?,?,?,?)";
    try {
      pst = conn.prepareStatement(sql);
      pst.setString(1, txt_classID.getText());
      pst.setString(2, courseID);
      pst.setString(3, txt_sectionTeacher.getText());
      pst.setString(4, txt_sectionSchedule.getText());
      pst.execute();
      JOptionPane.showMessageDialog(null, "Section Added");
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      try {
        if (pst != null) pst.close();
        if (conn != null) conn.close();
      } catch (Exception e) {
        System.out.println(e);
      }
    }
  }

  private void insertIntoSectionDetails() {
    conn = mysqlconnect.ConnectDB();
    String courseID = txt_sectionCourseID.getText();
    String courseName = getCourseName(courseID);
    String teacherName = getTeacherName(txt_sectionTeacher.getText());
    
    if (courseName == null) {
      JOptionPane.showMessageDialog(null, "Course ID does not exist");
      return;
    }

    String sql = "insert into section_details (class_id, section_code, course_id, course_code, section_teacher, section_schedule, section_venue)values(?,?,?,?,?,?,?)";
    try{
      pst = conn.prepareStatement(sql);
      pst.setString(1, txt_classID.getText());
      pst.setString(2, txt_sectionCode.getText());
      pst.setString(3, courseID);
      pst.setString(4, courseName);
      pst.setString(5, teacherName);
      pst.setString(6, txt_sectionSchedule.getText());
      pst.setString(7, txt_sectionVenue.getText());
      pst.execute();
      JOptionPane.showMessageDialog(null, "Section Details Added");
    } catch(Exception e){
      System.out.println(e);
    } finally {
      try {
        if (pst != null) pst.close();
        if (conn != null) conn.close();
      } catch (Exception e) {
        System.out.println(e);
      }
    }
  }

  public void addSection() {
    conn = mysqlconnect.ConnectDB();  
    if(courseIDExists(txt_sectionCourseID.getText())) {
      insertIntoSections();
      insertIntoSectionDetails();
    } else {
      JOptionPane.showMessageDialog(null, "Course ID does not exist");
    }
  }


  private String getCourseName(String courseID) {
    String sql = "SELECT course_code FROM courses WHERE course_id = ?";
    try {
      pst = conn.prepareStatement(sql);
      pst.setString(1, courseID);
      rs = pst.executeQuery();
      if (rs.next()) {
        return rs.getString("course_code");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  private String getTeacherName(String teacherID) {
    String sql = "SELECT name FROM teachers WHERE teacher_id = ?";
    try {
      pst = conn.prepareStatement(sql);
      pst.setString(1, teacherID);
      rs = pst.executeQuery();
      if (rs.next()) {
        return rs.getString("name");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }


  @Override
  public void initialize(URL url, ResourceBundle rb) {
  }
}

