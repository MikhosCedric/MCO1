import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

import javafx.fxml.Initializable;

public class DashboardViewController implements Initializable {

    @FXML
    private Button btn_Courses;

    @FXML
    private Button btn_Grades;

    
    @FXML
    private Button btn_Teachers;
    
    @FXML
    private Button btn_Students;

    public void showStudentsView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("students.fxml"));
        Scene scene = new Scene(root);
        Stage studentStage = new Stage();
        studentStage.setTitle("Students");
        studentStage.setScene(scene);
        //close the dashboard view
        Stage stage = (Stage) btn_Students.getScene().getWindow();
        stage.close();
        studentStage.show();
    }

    public void showTeachersView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("teachers.fxml"));
        Scene scene = new Scene(root);
        Stage teacherStage = new Stage();
        teacherStage.setTitle("Teachers");
        teacherStage.setScene(scene);
        //close the dashboard view
        Stage stage = (Stage) btn_Teachers.getScene().getWindow();
        stage.close();
        teacherStage.show();
    }


    public void showCoursesView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("courses.fxml"));
        Scene scene = new Scene(root);
        Stage courseStage = new Stage();
        courseStage.setTitle("Courses");
        courseStage.setScene(scene);
        //close the dashboard view
        Stage stage = (Stage) btn_Courses.getScene().getWindow();
        stage.close();
        courseStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    
    }
}
