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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.Initializable;

public class TeacherViewController implements Initializable{

    @FXML
    private Button btnAddTeacher;

    @FXML
    private Button btnViewTeacher;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDeleteTeacher;

    @FXML
    private Button btnUpdateTeacher;

    @FXML
    private TableColumn<teachers, String> colTDepartment;

    @FXML
    private TableColumn<teachers, Integer> colTID;

    @FXML
    private TableColumn<teachers, String> colTName;

    @FXML
    private TableColumn<teachers, String> colTEmail;

    @FXML
    private TableView<teachers> teacherTableView;

    @FXML
    private TextField txt_TeacherDepartment;

    @FXML
    private TextField txt_TeacherName;

    @FXML
    private TextField txt_TeacherID;

    @FXML
    private TextField txt_TeacherEmail;


    ObservableList<teachers> teachersList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void addTeacher() {
        conn = mysqlconnect.ConnectDB();
        String sql = "insert into teachers (teacher_id, name, department, teacher_email) values (?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_TeacherID.getText());
            pst.setString(2, txt_TeacherName.getText());
            pst.setString(3, txt_TeacherDepartment.getText());
            pst.setString(4, txt_TeacherEmail.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Teacher Added");
            updateTeachersTable();
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void deleteTeacher() {
        conn = mysqlconnect.ConnectDB();
        String sql = "delete from teachers where teacher_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_TeacherID.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Teacher Deleted");
            updateTeachersTable();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void getSelectedTeachers(MouseEvent event) {
        index = teacherTableView.getSelectionModel().getSelectedIndex(); 
        if(index <= -1){
            return;
        }
        
        txt_TeacherID.setText(colTID.getCellData(index).toString());
        txt_TeacherName.setText(colTName.getCellData(index).toString());
        txt_TeacherDepartment.setText(colTDepartment.getCellData(index).toString());
        txt_TeacherEmail.setText(colTEmail.getCellData(index).toString());

    }

    public void editTeacher() {
        try {
            conn = mysqlconnect.ConnectDB();
            String value1 = txt_TeacherID.getText();
            String value2 = txt_TeacherName.getText();
            String value3 = txt_TeacherDepartment.getText();
            String value4 = txt_TeacherEmail.getText();

            String sql = "update teachers set teacher_id= '"+value1+"', name= '"+value2+"', department= '"+value3+"', teacher_email= '"+value4+"' where teacher_id= '"+value1+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Teacher Info Updated");
            updateTeachersTable();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateTeachersTable() {
        colTID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colTEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        txt_TeacherID.clear();
        txt_TeacherName.clear();
        txt_TeacherDepartment.clear();
        txt_TeacherEmail.setText("");


        teachersList = mysqlconnect.getDataTeachers();
        teacherTableView.setItems(teachersList);
    }

    public void showDashboardView(ActionEvent event) throws IOException {
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

    @FXML
    void showTeacherRecordView(ActionEvent event) throws Exception {
        // Get selected student ID
        int selectedIndex = teacherTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please select a teacher.");
            return;
        }
        int selectedTeacherID = colTID.getCellData(selectedIndex);
        String selectedTeacherName = colTName.getCellData(selectedIndex);
        String selectedTeacherContactInfo = colTEmail.getCellData(selectedIndex);
    
        // Load the StudentRecordView
        FXMLLoader loader = new FXMLLoader(getClass().getResource("teacherRecord.fxml"));
        Parent root = loader.load();
    
        // Pass the selected student ID to the next controller
        TeacherRecordViewController controller = loader.getController();
        controller.setTeacherID(selectedTeacherID);
        controller.setTeacherName(selectedTeacherName);
        controller.setteacherEmail(selectedTeacherContactInfo);
    
        // Open the new stage
        Scene scene = new Scene(root);
        Stage teacherRecordStage = new Stage();
        teacherRecordStage.setTitle("Teacher Record");
        teacherRecordStage.setScene(scene);
    
        // Close the current stage
        Stage stage = (Stage) btnViewTeacher.getScene().getWindow();
        stage.close();
    
        teacherRecordStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTeachersTable();
    }

}
