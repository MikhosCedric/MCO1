import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReportCardController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSearchStudent;

    @FXML
    private TableColumn<?, ?> colCourseCode;

    @FXML
    private TableColumn<?, ?> colCourseGrade;

    @FXML
    private TableColumn<?, ?> colCourseTitle;

    @FXML
    private TableColumn<?, ?> colCourseUnits;

    @FXML
    private TableView<?> reportCardTable;

    @FXML
    private TextField txt_StudentID;

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

    
    public void initialize(URL url, ResourceBundle rb){
      
    }

}




  
  


