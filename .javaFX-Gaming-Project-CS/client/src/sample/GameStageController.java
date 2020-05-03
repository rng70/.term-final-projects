package sample;

import MainPack.PlayerNameAndWin;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameStageController implements Initializable {
    static String secondPlayerName;

    public void BackButtonPushed(ActionEvent event) throws IOException{
        Parent menu = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene menuScene = new Scene(menu);
        Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        window.setScene(menuScene);
        window.show();
    }
    public void secondPlayerName(String s){
        this.secondPlayerName = s;
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

    void player1LifeChange(){
        if((Controller.lifeDekhao1<100)&&(Controller.lifeDekhao1>95)){
            Rectangle r = new Rectangle(14,52,23*.5,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao1<=95)&&(Controller.lifeDekhao1>90)){
            Rectangle r = new Rectangle(14,52,23*1,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao1<=90)&&(Controller.lifeDekhao1>85)){
            Rectangle r = new Rectangle(14,52,23*1.5,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao1<=85)&&(Controller.lifeDekhao1>80)){
            Rectangle r = new Rectangle(14,52,23*2,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao1<=80)&&(Controller.lifeDekhao1>75)){
            Rectangle r = new Rectangle(14,52,23*2.5,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao1<=75)&&(Controller.lifeDekhao1>70)){
            Rectangle r = new Rectangle(14,52,23*3,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao1<=70)&&(Controller.lifeDekhao1>65)){
            Rectangle r = new Rectangle(14,52,23*3.5,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao1<=65)&&(Controller.lifeDekhao1>60)){
            Rectangle r = new Rectangle(14,52,23*4,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao1<=60)&&(Controller.lifeDekhao1>55)){
            Rectangle r = new Rectangle(14,52,23*4.5,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao1<=55)&&(Controller.lifeDekhao1>50)){
            Rectangle r = new Rectangle(14,52,23*5,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao1<=50)&&(Controller.lifeDekhao1>40)){
            Rectangle r = new Rectangle(14,52,23*6,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao1<=40)&&(Controller.lifeDekhao1>30)){
            Rectangle r = new Rectangle(14,52,23*7,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao1<=30)&&(Controller.lifeDekhao1>20)){
            Rectangle r = new Rectangle(14,52,23*1,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao1<=20)&&(Controller.lifeDekhao1>10)){
            Rectangle r = new Rectangle(14,52,23*8,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao1<=10)&&(Controller.lifeDekhao1>0)){
            Rectangle r = new Rectangle(14,52,23*9,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
    }
    void player2lifeChange(){
        if((Controller.lifeDekhao2<100)&&(Controller.lifeDekhao2>95)){
            Rectangle r = new Rectangle(1598,52,23*.5,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao2<=95)&&(Controller.lifeDekhao2>90)){
            Rectangle r = new Rectangle(1598,52,23*1,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao2<=90)&&(Controller.lifeDekhao2>85)){
            Rectangle r = new Rectangle(1598,52,23*1.5,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao2<=85)&&(Controller.lifeDekhao2>80)){
            Rectangle r = new Rectangle(1598,52,23*2,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao2<=80)&&(Controller.lifeDekhao2>75)){
            Rectangle r = new Rectangle(1598,52,23*2.5,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao2<=75)&&(Controller.lifeDekhao2>70)){
            Rectangle r = new Rectangle(1598,52,23*3,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao2<=70)&&(Controller.lifeDekhao2>65)){
            Rectangle r = new Rectangle(1598,52,23*3.5,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao2<=65)&&(Controller.lifeDekhao2>60)){
            Rectangle r = new Rectangle(1598,52,23*4,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao2<=60)&&(Controller.lifeDekhao2>55)){
            Rectangle r = new Rectangle(1598,52,23*4.5,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao2<=55)&&(Controller.lifeDekhao2>50)){
            Rectangle r = new Rectangle(1598,52,23*5,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao2<=50)&&(Controller.lifeDekhao2>40)){
            Rectangle r = new Rectangle(1598,52,23*6,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao2<=40)&&(Controller.lifeDekhao2>30)){
            Rectangle r = new Rectangle(1598,52,23*7,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao2<=30)&&(Controller.lifeDekhao2>20)){
            Rectangle r = new Rectangle(1598,52,23*1,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao2<=20)&&(Controller.lifeDekhao2>10)){
            Rectangle r = new Rectangle(1598,52,23*8,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
        if((Controller.lifeDekhao2<=10)&&(Controller.lifeDekhao2>0)){
            Rectangle r = new Rectangle(1598,52,23*9,11);
            r.setFill(Color.rgb(250,0,0));
            Controller.root.getChildren().add(r);
        }
    }
    void player1BulletLeft(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                nameLabel1.setText(MainPack.PlayerNameAndWin.getPlayerName());
                if(secondPlayerName != null)
                    nameLabel2.setText(secondPlayerName);

                player1LifeChange();
                player2lifeChange();

                score1.setText("Score : " + Integer.toString(Controller.scorePathateHbe1));
                score2.setText("Score : " + Integer.toString(Controller.scorePathateHbe2));
                life1.setText("Life : "+ Integer.toString(Controller.lifeDekhao1)+ "%");
                life2.setText("Life : "+ Integer.toString(Controller.lifeDekhao2)+ "%");


                try {
                    if (Controller.lifeDekhao1 == 0) {
                        File file = new File("data.txt");
                        FileWriter fwriter = new FileWriter(file);
                        fwriter.write(Controller.scorePathateHbe2);
                        fwriter.close();
                    }else if (Controller.lifeDekhao2 == 0) {
                        File file = new File("data.txt");
                        FileWriter fwriter = new FileWriter(file);
                        fwriter.write(Controller.scorePathateHbe1);
                        fwriter.close();
                    }

                }catch (Exception e){

                }




            }
        };
        timer.start();
    }
}
