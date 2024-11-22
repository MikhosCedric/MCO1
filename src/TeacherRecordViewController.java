import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

import javax.swing.JOptionPane;

public class TeacherRecordViewController implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private TableColumn<TeacherRecord, String> colTRCourse;

    @FXML
    private TableColumn<TeacherRecord, String> colTRSchedule;

    @FXML
    private TableColumn<TeacherRecord, String> colTRSection;

    @FXML
    private TableColumn<TeacherRecord, Integer> colTRUnits;

    @FXML
    private TableColumn<TeacherRecord, String> colTRVenue;

    @FXML
    private Label lbl_TeacherEmail;

    @FXML
    private Label lbl_TeacherID;

    @FXML
    private Label lbl_TeacherName;

    @FXML
    private TableView<TeacherRecord> teacherRecordView;

    ObservableList<TeacherRecord> data = FXCollections.observableArrayList();

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    int index = -1;

    private int teacherID;
    private String teacherName, teacherEmail;

    public void setTeacherID(int teacherID) {
      this.teacherID = teacherID;
      lbl_TeacherID.setText(String.valueOf(teacherID));
      populateRecordTable();
  }

  public void setTeacherName(String teacherName) {
      this.teacherName = teacherName;
      lbl_TeacherName.setText(teacherName);
  }

  public void setteacherEmail(String teacherEmail) {
      this.teacherEmail = teacherEmail;
      lbl_TeacherEmail.setText(teacherEmail);
  }

    @FXML
    void back(ActionEvent event) {
        Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("teachers.fxml"));
          Scene scene = new Scene(root);
          Stage teacherStage = new Stage();
          teacherStage.setTitle("Teachers");
          teacherStage.setScene(scene);
          //close the dashboard view
          Stage stage = (Stage) btnBack.getScene().getWindow();
          stage.close();
          teacherStage.show();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }

    public void populateRecordTable() {
      colTRCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
      colTRSection.setCellValueFactory(new PropertyValueFactory<>("section"));
      colTRSchedule.setCellValueFactory(new PropertyValueFactory<>("section"));
      colTRUnits.setCellValueFactory(new PropertyValueFactory<>("units"));
      colTRVenue.setCellValueFactory(new PropertyValueFactory<>("venue"));

  
      data = mysqlconnect.getTeacherRecords(teacherID); // Pass student ID here
      teacherRecordView.setItems(data);
  }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
