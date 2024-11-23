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
  private TableColumn<sections, Integer> col_AmtStudents;

  @FXML
  private TableColumn<sections, String> col_CourseCode;

  @FXML
  private TableColumn<sections, Integer> col_MaxStudents;

  @FXML
  private TableColumn<sections, String> col_Prof;

  @FXML
  private TableColumn<sections, String> col_Room;

  @FXML
  private TableColumn<sections, String> col_Schedule;

  @FXML
  private TableColumn<sections, String> col_SectionCode;

  @FXML
  private TableColumn<sections, Integer> col_sectionID;

  @FXML
  private TableView<sections> sectionsTableView;

  @FXML
  private TextField txt_classID;

  @FXML
  private TextField txt_max;

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

  private boolean courseIDExists(Connection conn, String courseID) {
    boolean exists = false;
    PreparedStatement localPst = null;
    ResultSet localRs = null;

    try {
        String sql = "SELECT COUNT(*) FROM courses WHERE course_id = ?";
        localPst = conn.prepareStatement(sql);
        localPst.setString(1, courseID);
        localRs = localPst.executeQuery();
        if (localRs.next()) {
            exists = localRs.getInt(1) > 0;
        }
    } catch (Exception e) {
        System.out.println("Error checking course ID: " + e.getMessage());
    } finally {
        try {
            if (localRs != null) localRs.close();
            if (localPst != null) localPst.close();
        } catch (Exception e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
    return exists;
}

private boolean teacherIDExists(Connection conn, String teacherID) {
    boolean exists = false;
    PreparedStatement localPst = null;
    ResultSet localRs = null;

    try {
        String sql = "SELECT COUNT(*) FROM teachers WHERE teacher_id = ?";
        localPst = conn.prepareStatement(sql);
        localPst.setString(1, teacherID);
        localRs = localPst.executeQuery();
        if (localRs.next()) {
            exists = localRs.getInt(1) > 0;
        }
    } catch (Exception e) {
        System.out.println("Error checking teacher ID: " + e.getMessage());
    } finally {
        try {
            if (localRs != null) localRs.close();
            if (localPst != null) localPst.close();
        } catch (Exception e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
    return exists;
}




  public void deleteSection() {
    int selectedIndex = sectionsTableView.getSelectionModel().getSelectedIndex();
    if (selectedIndex == -1) {
        JOptionPane.showMessageDialog(null, "Please select a section to delete.");
        return;
    }

    int classID = col_sectionID.getCellData(selectedIndex); // Replace with correct accessor for class_id.

    // Confirm deletion
    int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this section?");
    if (confirmation != JOptionPane.YES_OPTION) {
        return;
    }

    conn = mysqlconnect.ConnectDB();
    try {
        // Delete related rows in section_details
        String deleteDetailsSQL = "DELETE FROM section_details WHERE class_id = ?";
        pst = conn.prepareStatement(deleteDetailsSQL);
        pst.setInt(1, classID);
        pst.executeUpdate();

        // Delete row in sections
        String deleteSectionSQL = "DELETE FROM sections WHERE class_id = ?";
        pst = conn.prepareStatement(deleteSectionSQL);
        pst.setInt(1, classID);
        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Section deleted successfully!");
            updateSectionDetailsTable(); // Refresh the table
        } else {
            JOptionPane.showMessageDialog(null, "Failed to delete section.");
        }
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

  public void addSection() {
    
  }

  public void editSection() {
    boolean hasError = false;

    try {
        conn = mysqlconnect.ConnectDB(); // Open a single connection

        // Get values from text fields
        String value1 = txt_classID.getText();        // class_id
        String value2 = txt_sectionCourseID.getText(); // course_id
        String value3 = txt_sectionCode.getText();    // section_code
        String value4 = txt_sectionTeacher.getText(); // teacher_id
        String value5 = txt_sectionSchedule.getText(); // section_schedule
        String value6 = txt_sectionVenue.getText();   // section_venue
        String value7 = txt_max.getText();            // section_capacity

        // Check if course_id exists
        if (!courseIDExists(conn, value2)) {
            JOptionPane.showMessageDialog(null, "Course ID does not exist.");
            hasError = true;
        }

        // Check if teacher_id exists
        if (!teacherIDExists(conn, value4)) {
            JOptionPane.showMessageDialog(null, "Teacher ID does not exist.");
            hasError = true;
        }

        // Stop execution if there were validation errors
        if (hasError) {
            return;
        }

        // Update the sections table
        String sectionsSQL = "UPDATE sections SET course_id = ?, teacher_id = ?, class_schedule = ?, section_capacity = ? WHERE class_id = ?";
        pst = conn.prepareStatement(sectionsSQL);
        pst.setString(1, value2); // course_id
        pst.setString(2, value4); // teacher_id
        pst.setString(3, value5); // class_schedule
        pst.setInt(4, Integer.parseInt(value7)); // section_capacity
        pst.setInt(5, Integer.parseInt(value1)); // class_id
        pst.execute();

        // Update the section_details table
        String sectionDetailsSQL = "UPDATE section_details SET section_code = ?, section_schedule = ?, section_venue = ? WHERE class_id = ?";
        pst = conn.prepareStatement(sectionDetailsSQL);
        pst.setString(1, value3); // section_code
        pst.setString(2, value5); // section_schedule
        pst.setString(3, value6); // section_venue
        pst.setInt(4, Integer.parseInt(value1)); // class_id
        pst.execute();

        JOptionPane.showMessageDialog(null, "Section Info Updated");
        updateSectionDetailsTable(); // Refresh the TableView
    } catch (Exception e) {
        System.out.println("Error updating section: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
    } finally {
        try {
            if (pst != null) pst.close();
            if (conn != null) conn.close(); // Close connection at the end
        } catch (Exception e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
}






  public void getSelected(MouseEvent event) {
    index = sectionsTableView.getSelectionModel().getSelectedIndex();
    if (index <= -1) {
      return;
    }
    txt_classID.setText(col_sectionID.getCellData(index).toString());
    txt_sectionCourseID.setText(col_CourseCode.getCellData(index));
    txt_sectionCode.setText(col_SectionCode.getCellData(index));
    txt_sectionTeacher.setText(col_Prof.getCellData(index));
    txt_sectionSchedule.setText(col_Schedule.getCellData(index));
    txt_sectionVenue.setText(col_Room.getCellData(index));
    txt_max.setText(col_MaxStudents.getCellData(index).toString());
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
      conn = mysqlconnect.ConnectDB();
      try {
        sectionsList = mysqlconnect.getSectionsDetails();
        col_sectionID.setCellValueFactory(new PropertyValueFactory<>("sectionID"));
        col_CourseCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        col_SectionCode.setCellValueFactory(new PropertyValueFactory<>("sectionCode"));
        col_AmtStudents.setCellValueFactory(new PropertyValueFactory<>("amtStudents"));
        col_MaxStudents.setCellValueFactory(new PropertyValueFactory<>("maxStudents"));
        col_Prof.setCellValueFactory(new PropertyValueFactory<>("professor"));
        col_Schedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        col_Room.setCellValueFactory(new PropertyValueFactory<>("room"));
        sectionsTableView.setItems(sectionsList);
      } catch (Exception e) {
        System.out.println(e);
      }
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
          String selectedClassName = col_CourseCode.getCellData(selectedIndex);
          String selectedClassSection = col_SectionCode.getCellData(selectedIndex);
      
          
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

