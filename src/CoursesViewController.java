import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;

public class CoursesViewController {

    @FXML
    private Button btnCAdd;

    @FXML
    private Button btnCBack;

    @FXML
    private Button btnCDelete;

    @FXML
    private Button btnCUpdate;

    @FXML
    private TableColumn<courses, Integer> colCID;

    @FXML
    private TableColumn<courses, String> colCLocation;

    @FXML
    private TableColumn<courses, String> colCName;

    @FXML
    private TableColumn<courses, String> colCSection;

    @FXML
    private TableColumn<courses, String> colCTeacher;

    @FXML
    private TableColumn<courses, Integer> colCUnits;

    @FXML
    private TableView<courses> coursesTableView;

    @FXML
    private TextField txt_CourseID;

    @FXML
    private TextField txt_CourseLocation;

    @FXML
    private TextField txt_CourseName;

    @FXML
    private TextField txt_CourseSection;

    @FXML
    private TextField txt_CourseTeacher;

    @FXML
    private TextField txt_CourseUnits;
    
    ObservableList<courses> coursesList;

    int index = -1;

    



}
