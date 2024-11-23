import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class StudentCourseGradesController implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private Button btnViewGrades;

    @FXML
    private TableColumn<studentCourseGrades, Float> colCGPercentWeight;

    @FXML
    private TableColumn<studentCourseGrades, Float> colCGStudGrade;

    @FXML
    private TableColumn<studentCourseGrades, String> colCGSubType;

    @FXML
    private Label lbl_StudentEmail;

    @FXML
    private Label lbl_course;

    @FXML
    private Label lbl_StudentName;

    @FXML
    private TableView<studentCourseGrades> studentGradesTable;

    ObservableList<studentCourseGrades> studentGradesList = FXCollections.observableArrayList();

    int index = -1;

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private String course;
    private String studentName;

    public void setStudentID(String course) {
        this.course = course;
        lbl_course.setText(course);
        populateRecordTable();
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
        lbl_StudentName.setText(studentName);
    }


    public void populateRecordTable() {
      colCGPercentWeight.setCellValueFactory(new PropertyValueFactory<>("percentWeight"));
      colCGStudGrade.setCellValueFactory(new PropertyValueFactory<>("studGrade"));
      colCGSubType.setCellValueFactory(new PropertyValueFactory<>("subType"));
  
      studentGradesList = mysqlconnect.getStudentCourseGrades(index);
      studentGradesTable.setItems(studentGradesList);
    }



    @FXML
    void back(ActionEvent event) {

    }

    public void initialize(URL location, ResourceBundle resources) {
      populateRecordTable();
    }

}

