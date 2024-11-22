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

import javax.swing.JOptionPane;

import javafx.fxml.Initializable;

public class ClassRecordViewController implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private TableView<classRecord> classRecordView;

    @FXML
    private TableColumn<classRecord, String> colCRName;

    @FXML
    private TableColumn<classRecord, String> colCREmail;

    @FXML
    private TableColumn<classRecord, Integer> colCRID;

    @FXML
    private TableColumn<classRecord, Integer> colCRGrade;

    @FXML
    private Label lbl_classCode;

    @FXML
    private Label lbl_classID;

    @FXML
    private Label lbl_classSection;

    ObservableList<classRecord> data = FXCollections.observableArrayList();

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    int index = -1;

    private int classID;
    private String classCode, classSection;

    public void setClassID(int classID) {
        this.classID = classID;
        lbl_classID.setText(String.valueOf(classID));
        populateRecordTable();
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
        lbl_classCode.setText(classCode);
    }

    public void setClassSection(String classSection) {
        this.classSection = classSection;
        lbl_classSection.setText(classSection);
    }


    public void populateRecordTable() {
        colCRID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colCRName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colCREmail.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
        colCRGrade.setCellValueFactory(new PropertyValueFactory<>("studentGrade"));

        data = mysqlconnect.getClassRecords(classID);
        classRecordView.setItems(data);
    }

    @FXML
    void back(ActionEvent event) {
        Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("sections.fxml"));
          Scene scene = new Scene(root);
          Stage sectionsStage = new Stage();
          sectionsStage.setTitle("Classes");
          sectionsStage.setScene(scene);
          //close the dashboard view
          Stage stage = (Stage) btnBack.getScene().getWindow();
          stage.close();
          sectionsStage.show();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

  

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}

