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

public class TeacherViewController implements Initializable{

    @FXML
    private Button btnAddTeacher;

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
    private TableView<teachers> teacherTableView;

    @FXML
    private TextField txt_TeacherDepartment;

    @FXML
    private TextField txt_TeacherName;

    @FXML
    private TextField txt_TeacherID;


    ObservableList<teachers> teachersList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void addTeacher() {
        conn = mysqlconnect.ConnectDB();
        String sql = "insert into teachers (teacher_id, name, department) values (?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_TeacherID.getText());
            pst.setString(2, txt_TeacherName.getText());
            pst.setString(3, txt_TeacherDepartment.getText());
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

    }

    public void editTeacher() {
        try {
            conn = mysqlconnect.ConnectDB();
            String value1 = txt_TeacherID.getText();
            String value2 = txt_TeacherName.getText();
            String value3 = txt_TeacherDepartment.getText();

            String sql = "update teachers set teacher_id= '" + value1 + "', name= '" + value2 + "', department= '" + value3 + "' where teacher_id= '" + value1 + "'";
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

        txt_TeacherID.clear();
        txt_TeacherName.clear();
        txt_TeacherDepartment.clear();


        teachersList = mysqlconnect.getDataTeachers();
        teacherTableView.setItems(teachersList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTeachersTable();
    }

}
