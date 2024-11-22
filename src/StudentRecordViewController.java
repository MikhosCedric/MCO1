import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StudentRecordViewController implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private  TableColumn<StudentRecord, String> colSRCourse;
    
    @FXML
    private  TableColumn<StudentRecord, Float> colSRGrade;
            
    @FXML
    private TableColumn<StudentRecord, String> colSRSchedule;

    @FXML
    private TableColumn<StudentRecord, String> colSRSection;

    @FXML
    private TableColumn<StudentRecord, String> colSRTeacher;

    @FXML
    private TableColumn<StudentRecord, Integer> colSRUnits;

    @FXML
    private TableColumn<StudentRecord, String> colSRVenue;

    @FXML
    private Label lbl_StudentEmail;

    @FXML
    private Label lbl_StudentID;
    
    @FXML
    private Label lbl_StudentName;

    @FXML
    private TableView<StudentRecord> studentRecordTable;
    
    ObservableList<StudentRecord> data = FXCollections.observableArrayList();

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    int index = -1;

    private int studentID;
    private String studentName;
    private String studentEmail;

    public void setStudentID(int studentID) {
        this.studentID = studentID;
        lbl_StudentID.setText(String.valueOf(studentID));
        populateRecordTable();
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
        lbl_StudentName.setText(studentName);
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
        lbl_StudentEmail.setText(studentEmail);
    }

    public void back() {
        Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("students.fxml"));
          Scene scene = new Scene(root);
          Stage studentStage = new Stage();
          studentStage.setTitle("Students");
          studentStage.setScene(scene);
          //close the dashboard view
          Stage stage = (Stage) btnBack.getScene().getWindow();
          stage.close();
          studentStage.show();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    public void populateRecordTable() {
      colSRCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
      colSRSection.setCellValueFactory(new PropertyValueFactory<>("section"));
      colSRTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
      colSRSchedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));
      colSRVenue.setCellValueFactory(new PropertyValueFactory<>("venue"));
      colSRGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
      colSRUnits.setCellValueFactory(new PropertyValueFactory<>("units"));
  
      data = mysqlconnect.getStudentRecords(studentID); // Pass student ID here
      studentRecordTable.setItems(data);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    populateRecordTable();
  }
}
