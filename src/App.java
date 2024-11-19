import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage Stage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Scene scene = new Scene(root);
        
            Stage.setTitle("ANIMO Organize");
            Stage.setScene(scene);
            Stage.show();
        } catch (IOException e) {
        }
    }
    public static void main(String[] args){

        launch(args);
    }

}
