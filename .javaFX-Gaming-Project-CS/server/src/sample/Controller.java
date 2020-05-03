package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    static int scorePathateHbe1,scorePathateHbe2,lifeDekhao1,lifeDekhao2;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private ArrayList<GameObject> bullets = new ArrayList<>();
    private ArrayList<GameObject> bulletsleft = new ArrayList<>();
    private ArrayList<GameObject> bullets2 = new ArrayList<>();
    private ArrayList<GameObject> bulletsleft2 = new ArrayList<>();

    //For player1
    Image image = new Image("SpriteAndAnimation/game sprite (1).png");
    ImageView imageView = new ImageView(image);
    Character player = new Character(imageView, 100, 200);

    //For player 2
    Image image2 = new Image("SpriteAndAnimation/game sprite (1).png");
    ImageView imageView2 = new ImageView(image2);
    Character player2 = new Character(imageView2, 200, 200);
    static AnchorPane root = new AnchorPane();

    String changeofPlayer1 = null;
    static  String received=null;
    String player2Position;
    void setPlayer2Position(String  x){
        this.player2Position=x;
    }
    String getChangeofPlayer1(){
        return  changeofPlayer1;
    }
    void setChangeofPlayer1(String str){
        this.changeofPlayer1=str;
    }



    private class Bullet extends GameObject {

        Bullet() {
            super(new Circle(5, 5, 5, Color.rgb(249,212,172)));
        }
    }

    private void addGameObject(GameObject object, double x, double y) {
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }

    private void addBullet(GameObject bullet, double x, double y) {
        if (player.Direction.equals("RIGHT")) bullets.add(bullet);
        else if (player.Direction.equals("LEFT")) bulletsleft.add(bullet);
        addGameObject(bullet, x, y);
    }
    private void addBullet2(GameObject bullet, double x, double y) {
        if (player2.Direction.equals("RIGHT")) bullets2.add(bullet);
        else if (player2.Direction.equals("LEFT")) bulletsleft2.add(bullet);
        addGameObject(bullet, x, y);
    }

    private void BulleteUpdate() {

        for (GameObject bullet : bullets) {
            if (bullet.getView().getTranslateX()>=player2.getTranslateX()+200 &&
                    bullet.getView().getTranslateX()<=player2.getTranslateX()+300
            && bullet.getView().getTranslateY()>=player2.getTranslateY()+200
                    &&
                    bullet.getView().getTranslateY()<=player2.getTranslateY()+300
            ) {
                bullet.getView().setTranslateX(1500);
                bullet.setAlive(false);
                player.score+=50;
                player2.score-=50;
                player2.life-=1;
            }
        }
        for (GameObject bullet : bulletsleft) {
            if (bullet.getView().getTranslateX()>=player2.getTranslateX()+200 &&
                    bullet.getView().getTranslateX()<=player2.getTranslateX()+250
                    && bullet.getView().getTranslateY()>=player2.getTranslateY()+200
                    &&
                    bullet.getView().getTranslateY()<=player2.getTranslateY()+300) {
                bullet.getView().setTranslateX(-1500);
                bullet.setAlive(false);
                player.score+=50;
                player2.score-=50;
                player2.life-=1;
            }
        }
        for (GameObject bullet : bullets2) {
            if (bullet.getView().getTranslateX()>=player.getTranslateX()+200 &&
                    bullet.getView().getTranslateX()<=player.getTranslateX()+300
                    && bullet.getView().getTranslateY()>=player.getTranslateY()+200
                    &&
                    bullet.getView().getTranslateY()<=player.getTranslateY()+300
            ) {
                bullet.getView().setTranslateX(-1500);
                bullet.setAlive(false);
                player2.score+=50;
                player.score-=50;
                player.life-=1;
            }
        }
        for (GameObject bullet : bulletsleft2) {
            if (bullet.getView().getTranslateX()>=player.getTranslateX()+200 &&
                    bullet.getView().getTranslateX()<=player.getTranslateX()+250
                    && bullet.getView().getTranslateY()>=player.getTranslateY()+200
                    &&
                    bullet.getView().getTranslateY()<=player.getTranslateY()+300) {
                bullet.getView().setTranslateX(-1500);
                player2.score+=50;
                player.score-=50;
                player.life-=1;
                bullet.setAlive(false);
            }
        }
        bulletsleft.forEach(GameObject::updateleft);
        bullets.forEach(GameObject::updateright);
        bulletsleft2.forEach(GameObject::updateleft);
        bullets2.forEach(GameObject::updateright);
    }

    private boolean  IsPlayersColliding(){
        if (player2.getTranslateX() >= player.getTranslateX() - 200 &&
                player2.getTranslateX() <= player.getTranslateX() + 35 &&
                player2.getTranslateY() <= player2.getTranslateY() + 40 &&
                player2.getTranslateY() >= player.getTranslateY() - 100)
        {
            return true;
        }
        else{
            return false;
        }

    }

    public void update() throws Exception {

        if (isPressed(KeyCode.UP)) {
            if (player.Direction.equals("RIGHT")) {
                player.animation.setOffsetY(4 * 128);
            }
            if (player.Direction.equals("LEFT")) {
                player.animation.setOffsetY(5 * 128);
            }
            player.animation.play();
            changeofPlayer1 = "player1" + "up";
            player.moveY(-2);
        }
        else if (isPressed(KeyCode.DOWN)) {
            changeofPlayer1 = "player1" + "down";
            if (player.Direction.equals("RIGHT")) {
                player.animation.setOffsetY(4 * 128);
            }
            if (player.Direction.equals("LEFT")) {
                player.animation.setOffsetY(5 * 128);
            }
            player.animation.play();
            player.moveY(2);

        }
        else if (isPressed(KeyCode.RIGHT)) {
            player.Direction = "RIGHT";
            changeofPlayer1 = "player1" + "right";
            player.animation.play();
            player.animation.setOffsetY(128 * 7);
            player.moveX(2);
        }
        else if (isPressed(KeyCode.LEFT)) {
            player.Direction = "LEFT";
            changeofPlayer1 = "player1" + "left";
            player.animation.play();
            player.animation.setOffsetY(6 * 128);
            player.moveX(-2);
        }
        else if (isPressed(KeyCode.G)) {
            player.animation.play();
            changeofPlayer1 = "player1" + "Ghusi";
            if (player.Direction.equals("RIGHT")){
                player.animation.setOffsetY(8 * 128);
            }
            if (player.Direction.equals("LEFT")) {
                player.animation.setOffsetY(9 * 128);
            }
            if(IsPlayersColliding()) {
                player.score += 10;
                player2.score-=1;
            }
        }
        else if (isPressed(KeyCode.L)) {
            changeofPlayer1 = "player1" + "Lathi";
            player.animation.play();
            if (player.Direction.equals("RIGHT")) {
                player.animation.setOffsetY(10 * 128);
                player.moveX(-1);
            }
            if (player.Direction.equals("LEFT")) {
                player.animation.setOffsetY(11 * 128);
                player.moveX(1);
            }
            if(IsPlayersColliding()) {
                player.score += 15;
                player2.score-=1;
            }
        }
        else if (isPressed(KeyCode.Z)) {
            changeofPlayer1 = "player1" + "Guli";
            player.animation.play();
            if (player.Direction.equals("RIGHT")) {
                Bullet bullet = new Bullet();
                player.animation.setOffsetY(2 * 128);
                bullet.setVelocity(new Point2D(5, 0));
                addBullet(bullet, player.getTranslateX() + 200, player.getTranslateY() + 230);
            }
            if (player.Direction.equals("LEFT")) {
                Bullet bullet = new Bullet();
                player.animation.setOffsetY(3 * 128);
                bullet.setVelocity(new Point2D(5, 0));
                addBullet(bullet, player.getTranslateX() + 150, player.getTranslateY() + 232);
            }

        }
        else {
            changeofPlayer1=null;
            player.animation.stop();
        }
    }


    //Update for 2nd player
    public void update2() throws Exception {

        if (player2Position.equalsIgnoreCase("PLAYER2UP")) {
            player2.animation.play();
            if (player2.Direction.equals("RIGHT")) {
                player2.animation.setOffsetY(4 * 128);
            }
            if (player2.Direction.equals("LEFT")) {
                player2.animation.setOffsetY(5 * 128);
            }
        }
        else if (player2Position.equalsIgnoreCase("PLAYER2DOWN")) {
            if (player2.Direction.equals("RIGHT")) {
                player2.animation.setOffsetY(4 * 128);
            }
            if (player2.Direction.equals("LEFT")) {
                player2.animation.setOffsetY(5 * 128);
            }
            player2.animation.play();
            player2.moveY(2);
        }
        else if (player2Position.equalsIgnoreCase("PLAYER2RIGHT")) {
            player2.Direction = "RIGHT";
            player2.animation.play();
            player2.animation.setOffsetY(128 * 7);
            player2.moveX(2);
        }
        else if (player2Position.equalsIgnoreCase("PLAYER2LEFT")) {
            player2.Direction = "LEFT";
            player2.animation.play();
            player2.animation.setOffsetY(6 * 128);
            player2.moveX(-2);
        }
        else if (player2Position.equalsIgnoreCase("PLAYER2GHUSI")) {
            player2.animation.play();
            if(IsPlayersColliding()) {
                player2.score += 10;
               // player.score-=1;
                player.life -= 1;
            }
            if (player2.Direction.equals("RIGHT")) {
                player2.animation.setOffsetY(8 * 128);
            }
            if (player2.Direction.equals("LEFT")) {
                player2.animation.setOffsetY(9* 128);
            }
        }
        else if (player2Position.equalsIgnoreCase("PLAYER2LATHI")) {
            player2.animation.play();
            if(IsPlayersColliding()) {
                player2.score += 15;
//                player.score-=1;
                player.life -= 1;
            }
            if (player2.Direction.equals("RIGHT")) {
                player2.animation.setOffsetY(10 * 128);
                player.moveX(-1);
            }
            if (player2.Direction.equals("LEFT")) {
                player2.animation.setOffsetY(11 * 128);
                player.moveX(1);
            }
        }
        else if (player2Position.equalsIgnoreCase("PLAYER2GULI")) {

            player2.animation.play();
            if (player2.Direction.equals("RIGHT")) {
                Bullet bullet = new Bullet();
                player2.animation.setOffsetY(2 * 128);
                bullet.setVelocity(new Point2D(7, 0));
                addBullet2(bullet, player2.getTranslateX() + 200, player2.getTranslateY() + 230);
            }
            if (player2.Direction.equals("LEFT")) {
                Bullet bullet = new Bullet();
                player2.animation.setOffsetY(3 * 128);
                bullet.setVelocity(new Point2D(5, 0));
                addBullet2(bullet, player2.getTranslateX() + 150, player2.getTranslateY() + 230);
            }

        }
        else{
            player2.animation.stop();
        }
    }


    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }



    public void StartButtonAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GameStage.fxml"));
        Scene scene  = new Scene(root);
        root.getChildren().addAll(player, player2);
        player2.setTranslateX(player.getTranslateX()+550);
        scene.setOnKeyPressed(keyevent -> keys.put(keyevent.getCode(), true));
        scene.setOnKeyReleased(keyevent -> {
            keys.put(keyevent.getCode(), false);
        });
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), eventt -> {
            try {
                update();
                update2();

                BulleteUpdate();
                scorePathateHbe1=player.score;
                scorePathateHbe2=player2.score;
                lifeDekhao1=(int)player.life;
                lifeDekhao2=(int)player2.life;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        timeline.setRate(5);
        Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void HighScoreButtonPushed(ActionEvent event) throws IOException {
        Parent hs = FXMLLoader.load(getClass().getResource("HighScore.fxml"));
        Scene hsScene = new Scene(hs);
        Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        window.setScene(hsScene);
        window.show();
    }

    public void InstructionsButtonPushed(ActionEvent event) throws IOException {
        Parent is = FXMLLoader.load(getClass().getResource("Instructions.fxml"));
        Scene isScene = new Scene(is);
        Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        window.setScene(isScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ClientThread ct = new ClientThread(this);
            Thread t = new Thread(ct);
            t.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

