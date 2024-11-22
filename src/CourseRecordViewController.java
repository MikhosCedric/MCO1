import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class CourseRecordViewController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private TableColumn<CourseRecord, Integer> colCVAmtStud;

    @FXML
    private TableColumn<CourseRecord, String> colCVCode;

    @FXML
    private TableColumn<CourseRecord, Integer> colCVMax;

    @FXML
    private TableColumn<CourseRecord, String> colCVProfessor;

    @FXML
    private TableColumn<CourseRecord, String> colCVRoom;

    @FXML
    private TableColumn<CourseRecord, String> colCVSched;

    @FXML
    private TableColumn<CourseRecord, String> colCVSectionCode;

    @FXML
    private TableView<CourseRecord> courseRecordView;

    @FXML
    private Label lbl_courseCode;

    @FXML
    private Label lbl_courseID;

    ObservableList<CourseRecord> data = FXCollections.observableArrayList();

    int index = -1;

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    private String courseID;
    private String courseCode;

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
        lbl_courseCode.setText(courseCode);
        populateRecordTable();
    }

    @FXML
    void back(ActionEvent event) {
        Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("courses.fxml"));
          Scene scene = new Scene(root);
          Stage coursesStage = new Stage();
          coursesStage.setTitle("Courses");
          coursesStage.setScene(scene);
          //close the dashboard view
          Stage stage = (Stage) btnBack.getScene().getWindow();
          stage.close();
          coursesStage.show();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    public void populateRecordTable() {
        colCVCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        colCVSectionCode.setCellValueFactory(new PropertyValueFactory<>("sectionCode"));
        colCVProfessor.setCellValueFactory(new PropertyValueFactory<>("professor"));
        colCVRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
        colCVSched.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        colCVAmtStud.setCellValueFactory(new PropertyValueFactory<>("amountStudents"));
        colCVMax.setCellValueFactory(new PropertyValueFactory<>("maxStudents"));

        data = mysqlconnect.getCourseRecords(courseID);
        courseRecordView.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}

