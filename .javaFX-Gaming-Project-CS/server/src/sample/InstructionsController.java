package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class InstructionsController {

    public void BackButtonPushed(ActionEvent event) throws IOException {
        Parent menu= FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene menuScene =new Scene(menu);
        Stage window=(Stage)((Node)(event.getSource())).getScene().getWindow();
        window.setScene(menuScene);
        window.show();
    }
}
