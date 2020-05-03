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

    long time_started,time_ended;
    static int scorePathateHbe1,scorePathateHbe2,lifeDekhao1,lifeDekhao2;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private ArrayList<GameObject> bullets = new ArrayList<>();
    private ArrayList<GameObject> bulletsleft = new ArrayList<>();
    private ArrayList<GameObject> bullets2 = new ArrayList<>();
    private ArrayList<GameObject> bulletsleft2 = new ArrayList<>();
    Stage window;

    //For player1
    Image image = new Image("SpriteAndAnimation/game sprite (1).png");
    ImageView imageView = new ImageView(image);
    Character player = new Character(imageView, 100, 200);

    //For player 2
    Image image2 = new Image("SpriteAndAnimation/game sprite (1).png");
    ImageView imageView2 = new ImageView(image2);
    Character player2 = new Character(imageView2, 200, 200);
    static AnchorPane root = new AnchorPane();
    static String changeofPlayer1 = null;
    String changeofPlayer2=null;
    String player1Position=null;
    Timeline timeLine;

    void setPlayer1Position(String  x){
        this.player1Position=x;
    }
    String getChangeofPlayer1(){
        return changeofPlayer2;
    }
    void setChangeofPlayer1(String str){
        this.changeofPlayer1 = str;
    }

    private class Bullet extends GameObject{
        Bullet() {
            super(new Circle(5, 5, 5, Color.rgb(249,212,172)));
        }
    }

    private void addGameObject(GameObject object, double x, double y){
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }

    private void addBullet(GameObject bullet, double x, double y){
        if (player.Direction.equals("RIGHT")) bullets.add(bullet);
        else if (player.Direction.equals("LEFT")) bulletsleft.add(bullet);
        addGameObject(bullet, x, y);
    }

    private void addBullet2(GameObject bullet, double x, double y) {
        if (player2.Direction.equals("RIGHT")) bullets2.add(bullet);
        else if (player2.Direction.equals("LEFT")) bulletsleft2.add(bullet);
        addGameObject(bullet, x, y);
    }

    private void BulleteUpdate(){

        for(GameObject bullet : bullets){
            if(bullet.getView().getTranslateX()>=player2.getTranslateX()+200 &&
                    bullet.getView().getTranslateX()<=player2.getTranslateX()+300
                    && bullet.getView().getTranslateY()>=player2.getTranslateY()+200
                    &&
                    bullet.getView().getTranslateY()<=player2.getTranslateY()+300
            ){
                bullet.getView().setTranslateX(1500);
                bullet.setAlive(false);
                player.score+=20;
                player2.lifeOfPlayer-=0.1;
            }
        }
        for(GameObject bullet : bulletsleft){
            if(bullet.getView().getTranslateX()>=player2.getTranslateX()+200 &&
                    bullet.getView().getTranslateX()<=player2.getTranslateX()+250
                    && bullet.getView().getTranslateY()>=player2.getTranslateY()+200
                    &&
                    bullet.getView().getTranslateY()<=player2.getTranslateY()+300) {
                bullet.getView().setTranslateX(-1500);
                bullet.setAlive(false);
                player.score+=20;
                player2.lifeOfPlayer-=0.1;

            }
        }
        for(GameObject bullet : bullets2){
            if(bullet.getView().getTranslateX()>=player.getTranslateX()+200 &&
                    bullet.getView().getTranslateX()<=player.getTranslateX()+300
                    && bullet.getView().getTranslateY()>=player.getTranslateY()+200
                    &&
                    bullet.getView().getTranslateY()<=player.getTranslateY()+300
            ){
                bullet.getView().setTranslateX(-1500);
                bullet.setAlive(false);
                player2.score+=20;
                player.lifeOfPlayer-=0.1;
            }
        }
        for(GameObject bullet : bulletsleft2){
            if(bullet.getView().getTranslateX() >= player.getTranslateX() + 200 &&
                    bullet.getView().getTranslateX() <= player.getTranslateX() + 250
                    && bullet.getView().getTranslateY() >= player.getTranslateY() + 200
                    &&
                    bullet.getView().getTranslateY() <= player.getTranslateY() + 300)
            {
                bullet.getView().setTranslateX(-1500);
                player2.score += 20;
                player.lifeOfPlayer-=0.1;
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
        if (player1Position.contains("PLAYER1UP")) {
            player.animation.play();
            if(player.Direction.equals("RIGHT")){
                player.animation.setOffsetY(4 * 128);
            }
            if(player.Direction.equals("LEFT")){
                player.animation.setOffsetY(5 * 128);
            }

            player.moveY(-2);
        }
        else if(player1Position.contains("PLAYER1DOWN")){
            player.animation.play();
            if(player.Direction.equals("RIGHT")){
                player.animation.setOffsetY(4 * 128);
            }
            if(player.Direction.equals("LEFT")){
                player.animation.setOffsetY(5 * 128);
            }

            player.moveY(2);
        }
        else if(player1Position.contains("PLAYER1RIGHT")){
            player.animation.play();
            player.Direction = "RIGHT";
            changeofPlayer1 = "player1#" + "right";
            player.animation.setOffsetY(128 * 7);

            player.moveX(2);
        }
        else if(player1Position.contains("PLAYER1LEFT")){
            player.animation.play();
            player.Direction = "LEFT";
            changeofPlayer1 = "player1#" + "left";
            player.animation.setOffsetY(6 * 128);

            player.moveX(-2);
        }
        else if(player1Position.contains("PLAYER1GHUSI")){
            player.animation.play();
            if(player.Direction.equals("RIGHT")){
                player.animation.setOffsetY(8 * 128);
            }
            if(player.Direction.equals("LEFT")){
                player.animation.setOffsetY(9 * 128);
            }
            if(IsPlayersColliding()){
                player.score += 10;
                player2.lifeOfPlayer -= 0.1;
            }

        }
        else if(player1Position.contains("PLAYER1LATHI")){
            player.animation.play();
            if(player.Direction.equals("RIGHT")){
                player.animation.setOffsetY(10 * 128);
            }
            if(player.Direction.equals("LEFT")){
                player.animation.setOffsetY(11 * 128);
            }
            if(IsPlayersColliding()){
                player.score += 15;
                player2.lifeOfPlayer -= 0.1;
            }

        }
        else if(player1Position.contains("PLAYER1GULI")){

            if(player.getTotalBulletCount() >= 0){
                if(player.Direction.equals("RIGHT")){
                    Bullet bullet = new Bullet();
                    player.animation.setOffsetY(2 * 128);
                    bullet.setVelocity(new Point2D(7, 0));
                    addBullet(bullet, player.getTranslateX() + 200, player.getTranslateY() + 230);

                }
                if(player.Direction.equals("LEFT")){
                    Bullet bullet = new Bullet();
                    player.animation.setOffsetY(3* 128);
                    bullet.setVelocity(new Point2D(5, 0));
                    addBullet(bullet, player.getTranslateX() + 150, player.getTranslateY() + 232);

                }
                double bulletCount = player.getTotalBulletCount() - 0.20;
                player.setTotalBulletCount(bulletCount);
            }
            else {
                System.out.println("No more bullet");
            }
            player.animation.play();
        }
        else{
            player.animation.stop();
        }
    }

    //Update for 2nd player
    public void update2() throws Exception{
        if(isPressed(KeyCode.UP)){

            if(player2.Direction.equals("RIGHT")){
                player2.animation.setOffsetY(4 * 128);
            }
            if(player2.Direction.equals("LEFT")){
                player2.animation.setOffsetY(5 * 128);
            }
            player2.animation.play();
            changeofPlayer2= "player2" + "up";
            player2.moveY(-2);
        }
        else if(isPressed(KeyCode.DOWN)){

            if(player2.Direction.equals("RIGHT")){
                player2.animation.setOffsetY(4 * 128);
            }
            if (player2.Direction.equals("LEFT")){
                player2.animation.setOffsetY(5 * 128);
            }
            player2.animation.play();
            changeofPlayer2 = "player2" + "down";
            player2.moveY(2);
        }
        else if(isPressed(KeyCode.RIGHT)){
            player2.Direction = "RIGHT";
            player2.animation.play();
            changeofPlayer2 = "player2" + "right";
            player2.animation.setOffsetY(128 * 7);
            player2.moveX(2);
        }
        else if(isPressed(KeyCode.LEFT)){
            player2.Direction = "LEFT";
            player2.animation.play();
            changeofPlayer2 = "player2" + "left";
            player2.animation.setOffsetY(6 * 128);
            player2.moveX(-2);
        }
        else if(isPressed(KeyCode.G)){

            if(player2.Direction.equals("RIGHT")){
                player2.animation.setOffsetY(8 * 128);
            }
            if(player2.Direction.equals("LEFT")){
                player2.animation.setOffsetY(9 * 128);
            }
            if(IsPlayersColliding()){
                player2.score += 10;
                player.lifeOfPlayer -= 0.1;
            }
            player2.animation.play();
            changeofPlayer2 = "player2" + "Ghusi";
        }
        else if(isPressed(KeyCode.L)){

            if(player2.Direction.equals("RIGHT")){
                player2.animation.setOffsetY(10 * 128);
            }
            if(player2.Direction.equals("LEFT")){
                player2.animation.setOffsetY(11 * 128);
            }
            if(IsPlayersColliding()){
                player2.score += 15;
                player.lifeOfPlayer -= 0.1;
            }
            player2.animation.play();
            changeofPlayer2= "player2" + "Lathi";
        }
        else if(isPressed(KeyCode.Z)){



            if(player2.getTotalBulletCount() >= 0){
                if(player2.Direction.equals("RIGHT")){
                    Bullet bullet = new Bullet();
                    player2.animation.setOffsetY(2 * 128);
                    bullet.setVelocity(new Point2D(7, 0));
                    addBullet2(bullet, player2.getTranslateX() + 200, player2.getTranslateY() + 230);

                }
                if(player2.Direction.equals("LEFT")){
                    Bullet bullet = new Bullet();
                    player2.animation.setOffsetY(3 * 128);
                    bullet.setVelocity(new Point2D(5, 0));
                    addBullet2(bullet, player2.getTranslateX() + 150, player2.getTranslateY() + 232);

                }
                double totalBulletCount = player2.getTotalBulletCount() - 0.20;
                player2.setTotalBulletCount(totalBulletCount);
            }
            else{
                System.out.println("No more Bullets");
            }
            player2.animation.play();
            changeofPlayer2= "player2" + "GULI";
        }
        else{
            changeofPlayer2=null;
            player2.animation.stop();
        }
    }


    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    public void StartButtonAction(ActionEvent event) throws IOException {
        time_started = System.currentTimeMillis();
        root = FXMLLoader.load(getClass().getResource("/sample/GameStage.fxml"));
        Scene scene = new Scene(root);
        root.getChildren().addAll(player, player2);
        player2.setTranslateX(player.getTranslateX()+550);

        scene.setOnKeyPressed(keyevent -> keys.put(keyevent.getCode(), true));
        scene.setOnKeyReleased(keyevent -> {
            keys.put(keyevent.getCode(), false);
        });

        timeLine = new Timeline(new KeyFrame(Duration.millis(100),event1 -> {
            try {
                scorePathateHbe1 = player.score;
                scorePathateHbe2 = player2.score;
                lifeDekhao1 = (int) player.lifeOfPlayer;
                lifeDekhao2 = (int) player2.lifeOfPlayer;
                {
                    update();
                    update2();
                    BulleteUpdate();
                    //player2.animation.stop();
                    //player.animation.stop();
                }
                if(lifeDekhao1<=0 || lifeDekhao2<=0)
                {
                    player2.animation.stop();
                    player.animation.stop();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } ));
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();
        timeLine.setRate(3);

        window = (Stage)((Node)(event.getSource())).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void HighScoreButtonPushed(ActionEvent event) throws IOException{
        Parent hs = FXMLLoader.load(getClass().getResource("/sample/HighScore.fxml"));
        Scene hsScene = new Scene(hs);
        Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        window.setScene(hsScene);
        window.show();
    }

    public void InstructionsButtonPushed(ActionEvent event) throws IOException{
        Parent is = FXMLLoader.load(getClass().getResource("/sample/Instructions.fxml"));
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

