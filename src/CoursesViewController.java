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

public class CoursesViewController implements Initializable{

    @FXML
    private Button btnCAdd;

    @FXML
    private Button btnView;

    @FXML
    private Button btnCBack;

    @FXML
    private Button btnCDelete;

    @FXML
    private Button btnCUpdate;

    @FXML
    private TableColumn<courses, String> colCID;

    @FXML
    private TableColumn<courses, String> colCName;

    @FXML
    private TableColumn<courses, Integer> colCUnits;

    @FXML
    private TableView<courses> coursesTableView;

    @FXML
    private TextField txt_CourseID;

    @FXML
    private TextField txt_CourseName;

    @FXML
    private TextField txt_CourseUnits;

    int index = -1;

    ObservableList<courses> courseList;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void AddCourse() {
        conn = mysqlconnect.ConnectDB();
        String sql = "insert into courses(course_id, course_code, course_units) values(?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_CourseID.getText());
            pst.setString(2, txt_CourseName.getText());
            pst.setString(3, txt_CourseUnits.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Course Added");
            updateCoursesTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getSelected (MouseEvent event) {
        index = coursesTableView.getSelectionModel().getSelectedIndex();
        if(index <= -1) {
            return;
        }
        txt_CourseID.setText(colCID.getCellData(index).toString());
        txt_CourseName.setText(colCName.getCellData(index).toString());
        txt_CourseUnits.setText(colCUnits.getCellData(index).toString());
    }

    public void deleteCourse() {
        conn = mysqlconnect.ConnectDB();
        String sql = "delete from courses where course_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_CourseID.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Course Deleted");
            updateCoursesTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void updateCoursesTable() {
        colCID.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        colCName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCUnits.setCellValueFactory(new PropertyValueFactory<>("courseUnits"));

        txt_CourseID.clear();
        txt_CourseName.clear();
        txt_CourseUnits.clear();

        courseList = mysqlconnect.getDataCourses();
        coursesTableView.setItems(courseList);
    }

    public void editCourse() {
        try {
            conn = mysqlconnect.ConnectDB();
            String value1 = txt_CourseID.getText();
            String value2 = txt_CourseName.getText();
            String value3 = txt_CourseUnits.getText();

            String sql = "update courses set course_id= '" + value1 + "', course_code= '" + value2 + "', course_units= '" + value3 + "' where course_id= '" + value1 + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Course Info Updated");
            updateCoursesTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void getSelectedCourses(MouseEvent event) {
        index = coursesTableView.getSelectionModel().getSelectedIndex();
        if(index <= -1) {
            return;
        }

        txt_CourseID.setText(colCID.getCellData(index).toString());
        txt_CourseName.setText(colCName.getCellData(index).toString());
        txt_CourseUnits.setText(colCUnits.getCellData(index).toString());
    }

        public void showDashboardView(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene scene = new Scene(root);
        Stage dashboardStage = new Stage();
        dashboardStage.setTitle("Dashboard");
        dashboardStage.setScene(scene);
        //close the dashboard view
        Stage stage = (Stage) btnCBack.getScene().getWindow();
        stage.close();
        dashboardStage.show();
    }

    @FXML
    void showCoursesRecordView(ActionEvent event) throws Exception {
        // Get selected student ID
        int selectedIndex = coursesTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please select a Course.");
            return;
        }
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("courseRecord.fxml"));
        Parent root = loader.load();

        // Get the selected student ID
        String selectedCourseID = colCID.getCellData(selectedIndex);
        String selectedCourseCode = colCName.getCellData(selectedIndex);
    
        // Pass the selected student ID to the next controller
        CourseRecordViewController controller = loader.getController();
        controller.setCourseID(selectedCourseID);
        controller.setCourseCode(selectedCourseCode);
    
        // Open the new stage
        Scene scene = new Scene(root);
        Stage studentRecordStage = new Stage();
        studentRecordStage.setTitle("Courses Record");
        studentRecordStage.setScene(scene);
    
        // Close the current stage
        Stage stage = (Stage) btnView.getScene().getWindow();
        stage.close();
    
        studentRecordStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateCoursesTable();
    }

}
