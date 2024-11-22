import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

import javax.swing.Action;
import javax.swing.JOptionPane;

import javafx.fxml.Initializable;


public class StudentViewController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<students, String> studContactInfo;

    @FXML
    private TableColumn<students, String> studName;

    @FXML
    private TableColumn<students, Integer> studentID;

    @FXML
    private TableView<students> studentTableView;

    @FXML
    private TextField txt_StudName;

    @FXML
    private TextField txt_StudentID;

    @FXML
    private TextField txt_contactInfo;


    ObservableList<students> studentsList;


    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void addStudent() {
        conn = mysqlconnect.ConnectDB();
        String sql = "insert into students(student_id, student_name, contact_information) values (?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_StudentID.getText());
            pst.setString(2, txt_StudName.getText());
            pst.setString(3, txt_contactInfo.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Student Added");
            updateStudentTable();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteStudent() {
        conn = mysqlconnect.ConnectDB();
        String sql = "delete from students where student_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_StudentID.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Student Deleted");
            updateStudentTable();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void getSelectedStudent(MouseEvent event) {
        index = studentTableView.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txt_StudentID.setText(studentID.getCellData(index).toString());
        txt_StudName.setText(studName.getCellData(index));
        txt_contactInfo.setText(studContactInfo.getCellData(index));
    }

    public void editStudent() {
        try {
            conn = mysqlconnect.ConnectDB();
            String value1 = txt_StudentID.getText();
            String value2 = txt_StudName.getText();
            String value3 = txt_contactInfo.getText();
            String sql = "update students set student_id= '" + value1 + "', student_name= '" + value2 + "', contact_information= '" + value3 + "' where student_id='" + value1 + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Student Updated");
            updateStudentTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void updateStudentTable() {
        studentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        studName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studContactInfo.setCellValueFactory(new PropertyValueFactory<>("contactInformation"));

        txt_StudName.clear();
        txt_StudentID.clear();
        txt_contactInfo.clear();

        studentsList = mysqlconnect.getDataStudents();
        studentTableView.setItems(studentsList);
    }

    public void showDashboardView(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene scene = new Scene(root);
        Stage dashboardStage = new Stage();
        dashboardStage.setTitle("Dashboard");
        dashboardStage.setScene(scene);
        //close the dashboard view
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        dashboardStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateStudentTable();
    }
}

