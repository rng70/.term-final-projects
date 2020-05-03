package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent menu = FXMLLoader.load(getClass().getResource("/LogInPack/LoginFile.fxml"));
        Scene scene = new Scene(menu);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
