import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage teacherStage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("teachers.fxml"));
            Scene scene = new Scene(root);
        
            teacherStage.setTitle("ANIMO Organize");
            teacherStage.setScene(scene);
            teacherStage.show();
        } catch (IOException e) {
        }
    }
    public static void main(String[] args){

        launch(args);
    }

}
