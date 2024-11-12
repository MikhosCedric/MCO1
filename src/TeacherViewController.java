import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.Initializable;


public class TeacherViewController implements Initializable {

    @FXML
    private Button btnAddTeacher;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDeleteTeacher;

    @FXML
    private Button btnEditTeacher;

    @FXML
    private TableColumn<teachers, Integer> colTID;

    @FXML
    private TableColumn<teachers, String> colTCourse;

    @FXML
    private TableColumn<teachers, String> colTEmail;


    @FXML
    private TableColumn<teachers, String> colTLocation;

    @FXML
    private TableColumn<teachers, String> colTName;

    @FXML
    private TableColumn<teachers, String> colTSection;

    @FXML
    private TableColumn<teachers, ?> colTTime;

    @FXML
    private TableView<teachers> teacherTableView;

    @FXML
    private Label tfLabel;

     @FXML
    private TextField txt_TeacherCourse;

    @FXML
    private TextField txt_TeacherEmail;

    @FXML
    private TextField txt_TeacherID;

    @FXML
    private TextField txt_TeacherLocation;

    @FXML
    private TextField txt_TeacherName;

    @FXML
    private TextField txt_TeacherSection;

    @FXML
    private TextField txt_TeacherTime;

    ObservableList<teachers> teachersList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void addTeacher() {
        conn = mysqlconnect.ConnectDB();
        String sql = "insert into teachers (teacher_id, name, courses, sections, venue, email) values (?, ?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_TeacherID.getText());
            pst.setString(2, txt_TeacherName.getText());
            pst.setString(3, txt_TeacherCourse.getText());
            pst.setString(4, txt_TeacherSection.getText());
            pst.setString(5, txt_TeacherLocation.getText());
            pst.setString(6, txt_TeacherEmail.getText());
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
            JOptionPane.showMessageDialog(null, e);
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
        txt_TeacherCourse.setText(colTCourse.getCellData(index).toString());
        txt_TeacherSection.setText(colTSection.getCellData(index).toString());
        txt_TeacherLocation.setText(colTLocation.getCellData(index).toString());
        txt_TeacherEmail.setText(colTEmail.getCellData(index).toString());
    }

    public void editTeacher() {
        try {
            conn = mysqlconnect.ConnectDB();
            String value1 = txt_TeacherID.getText();
            String value2 = txt_TeacherName.getText();
            String value3 = txt_TeacherCourse.getText();
            String value4 = txt_TeacherSection.getText();
            String value5 = txt_TeacherLocation.getText();
            String value6 = txt_TeacherEmail.getText();

            String sql = "update teachers set teacher_id= '"+value1+"', name= '"+value2+"', courses= '"+value3+"', sections= '"+value4+"', venue= '"+value5+"', email= '"+value6+"' where teacher_id= '"+value1+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Teacher Info Updated");
            updateTeachersTable();
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void updateTeachersTable() {
        colTID.setCellValueFactory(new PropertyValueFactory<teachers, Integer>("id"));
        colTName.setCellValueFactory(new PropertyValueFactory<teachers, String>("name"));
        colTCourse.setCellValueFactory(new PropertyValueFactory<teachers, String>("course"));
        colTSection.setCellValueFactory(new PropertyValueFactory<teachers, String>("section"));
        colTLocation.setCellValueFactory(new PropertyValueFactory<teachers, String>("location"));
        colTEmail.setCellValueFactory(new PropertyValueFactory<teachers, String>("email"));

        txt_TeacherID.clear();
        txt_TeacherName.clear();
        txt_TeacherCourse.clear();
        txt_TeacherSection.clear();
        txt_TeacherLocation.clear();
        txt_TeacherEmail.clear();


        teachersList = mysqlconnect.getDataTeachers();
        teacherTableView.setItems(teachersList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        updateTeachersTable();
    }

}
