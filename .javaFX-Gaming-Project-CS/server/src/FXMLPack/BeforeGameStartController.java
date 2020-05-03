package FXMLPack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class BeforeGameStartController implements Initializable {
    @FXML
    Label textArea1 ;
    @FXML
    Label textArea2 ;
    @FXML
    Button buttonProceed;

    String s2;


    public void setTextArea2(String s2){
        this.s2 = s2;
    }

    public void ProceedButtonOnAction(ActionEvent event){

        try{
            Parent menu ;
            menu = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
            Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            window.setScene(new Scene(menu));
            window.show();
        }catch(IOException ioe){
            ioe.toString();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textArea1.setText("Death Land");
        textArea2.setText(s2);
    }
}
