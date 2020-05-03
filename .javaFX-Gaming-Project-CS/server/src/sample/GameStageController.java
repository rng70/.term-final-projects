package sample;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameStageController implements Initializable {

    public void BackButtonPushed(ActionEvent event) throws IOException{
        Parent menu = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene menuScene = new Scene(menu);
        Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        window.setScene(menuScene);
        window.show();
    }

    @FXML
    Label nameLabel1,nameLabel2;
    @FXML
    Rectangle lifeLabel01;
    @FXML
    Rectangle lifeLabel02;
    @FXML
    Label ending;
    @FXML
    Label score1,score2;
    @FXML
    Label life1,life2;

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                //For Player1
                if((Controller.lifeDekhao1 < 100) && (Controller.lifeDekhao1 >90)){
                    Rectangle r = new Rectangle(14,51,23,13);
                    r.setFill(Color.rgb(255,255,255));
                    Controller.root.getChildren().add(r);
                    System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
                }
                else if((Controller.lifeDekhao1 < 90)){
                    Rectangle r = new Rectangle(14,51,23*15,13);
                    r.setFill(Color.rgb(255,255,255));
                    Controller.root.getChildren().add(r);
                    System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");

                }


                //For Player2
                if((Controller.lifeDekhao2 < 100) && (Controller.lifeDekhao2 >90)){
                    Rectangle r = new Rectangle(14,51,23,13);
                    r.setFill(Color.rgb(255,255,255));
                    Controller.root.getChildren().add(r);
                    System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");

                }
                //&& (Controller.lifeDekhao2 >80)
                else if((Controller.lifeDekhao2 < 90) ){
                    Rectangle r = new Rectangle(14,51,23*15,13);
                    r.setFill(Color.rgb(255,255,255));
                    Controller.root.getChildren().add(r);
                    System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");

                }








                score1.setText("Score : " + Integer.toString(Controller.scorePathateHbe1));
                score2.setText("Score : " + Integer.toString(Controller.scorePathateHbe2));
                life1.setText("Life : "+ Integer.toString(Controller.lifeDekhao1)+ "%");
                life2.setText("Life : "+ Integer.toString(Controller.lifeDekhao2)+ "%");

//                if(Controller.lifeDekhao2>0 && Controller.lifeDekhao1>0){
//                    ending.setText(" ");
//                }
//                else if(Controller.lifeDekhao1<=0)
////                    ending.setText("PLAYER2 WINS!");
//                else if(Controller.lifeDekhao2<=0)
////                    ending.setText(" PLAYER1 WINS");
            }
        };
        timer.start();
    }
}
