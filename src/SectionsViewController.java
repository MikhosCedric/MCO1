import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.Initializable;


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
  private Button btnViewClass;

  @FXML
  private TableColumn<sections, String> col_sectionCode;

  @FXML
  private TableColumn<sections, String> col_sectionCourse;

  @FXML
  private TableColumn<sections, Integer> col_sectionCourseID;

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
      updateSectionDetailsTable();
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



      public void showDashboardView(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene scene = new Scene(root);
        Stage dashboardStage = new Stage();
        dashboardStage.setTitle("Dashboard");
        dashboardStage.setScene(scene);
        //close the dashboard view
        Stage stage = (Stage) btnSectionBack.getScene().getWindow();
        stage.close();
        dashboardStage.show();
    }

    public void updateSectionDetailsTable() {
        col_sectionID.setCellValueFactory(new PropertyValueFactory<>("class_id"));
        col_sectionCode.setCellValueFactory(new PropertyValueFactory<>("section_code"));
        col_sectionCourseID.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        col_sectionCourse.setCellValueFactory(new PropertyValueFactory<>("course_code"));
        col_sectionTeacher.setCellValueFactory(new PropertyValueFactory<>("section_teacher"));
        col_sectionVenue.setCellValueFactory(new PropertyValueFactory<>("section_venue"));
        col_sectionSchedule.setCellValueFactory(new PropertyValueFactory<>("section_schedule"));

        txt_classID.clear();
        txt_sectionCode.clear();
        txt_sectionCourseID.clear();
        txt_sectionSchedule.clear();
        txt_sectionTeacher.clear();
        txt_sectionVenue.clear();

        sectionsList = mysqlconnect.getSectionsDetails();
        sectionsTableView.setItems(sectionsList);
          }

      @FXML
      void showClassRecordView(ActionEvent event) throws Exception {
          // Get selected student ID
          int selectedIndex = sectionsTableView.getSelectionModel().getSelectedIndex();
          if (selectedIndex == -1) {
              JOptionPane.showMessageDialog(null, "Please select a section.");
              return;
          }
          int selectedClassID = col_sectionID.getCellData(selectedIndex);
          String selectedClassName = col_sectionCode.getCellData(selectedIndex);
          String selectedClassSection = col_sectionCode.getCellData(selectedIndex);
      
          // Load the StudentRecordView
          FXMLLoader loader = new FXMLLoader(getClass().getResource("classRecord.fxml"));
          Parent root = loader.load();
      
          // Pass the selected student ID to the next controller
          ClassRecordViewController controller = loader.getController();
          controller.setClassID(selectedClassID);
          controller.setClassCode(selectedClassName);
          controller.setClassSection(selectedClassSection);
          
      
          // Open the new stage
          Scene scene = new Scene(root);
          Stage classRecordStage = new Stage();
          classRecordStage.setTitle("Class Record");
          classRecordStage.setScene(scene);
      
          // Close the current stage
          Stage stage = (Stage) btnViewClass.getScene().getWindow();
          stage.close();
      
          classRecordStage.show();
      }


  @Override
  public void initialize(URL url, ResourceBundle rb) {
    updateSectionDetailsTable();
  }
}

