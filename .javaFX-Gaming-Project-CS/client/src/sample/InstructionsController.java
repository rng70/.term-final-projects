package sample;

import VoiceControl.VoiceControlOfGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;

public class InstructionsController{

    @FXML
    TextArea textArea;
    @FXML
    Button backButton;

    public void BackButtonPushed(ActionEvent event) throws IOException{
        Parent menu = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene menuScene = new Scene(menu);
        Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        window.setScene(menuScene);
        window.show();

        //Game joKhon shesh
        String stringToRead = textArea.getText();
        VoiceControl.VoiceControlOfGame  voiceControlOfGame = new VoiceControlOfGame();
        voiceControlOfGame.Text2Voice(stringToRead);
    }
}
