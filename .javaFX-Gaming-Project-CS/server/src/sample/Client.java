package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.input.KeyCode;

public class Client extends Application  {

    static  AnchorPane player1root;
    private HashMap<KeyCode, Boolean> keys = new java.util.HashMap<>();
    private ArrayList<GameObject> bullets = new ArrayList<>();
    private ArrayList<GameObject> bulletsleft = new ArrayList<>();

    Image image = new Image("BAT .png");
    Image image2=new Image("FINAL BAT 2.png");
    ImageView imageView = new ImageView(image);
    ImageView imageView2 = new ImageView(image2);
    Character player1 = new Character(imageView,0,0);
    Character player2 = new Character(imageView2,100,200);

    public Client() throws IOException {
    }


    private class Bullet extends GameObject {

        Bullet() {
            super(new Circle(5, 5, 5, Color.rgb(70,0,112)));
        }
    }

    private void addGameObject(GameObject object, double x, double y) {
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        player1root.getChildren().add(object.getView());
    }
    private void addBullet(GameObject bullet, double x, double y) {
        if(player1.Direction.equals("RIGHT")) bullets.add(bullet);
        else if (player1.Direction.equals("LEFT")) bulletsleft.add(bullet);
        addGameObject(bullet, x, y);
    }
    private void BulleteUpdate() {
        bulletsleft.forEach(GameObject::updateleft);
        bullets.forEach(GameObject::updateright);
    }

    public void update() throws Exception {

        if (isPressed(KeyCode.UP)) {
            player1.Direction="UP";
            player1.animation.play();
            player1.animation.setOffsetY(128*0);

            boolean fly=false;
            if(!fly) {
                player1.moveY(-3);
                fly=true;
            }
        }
        else if (isPressed(KeyCode.DOWN)) {
            player1.Direction="DOWN";
            player1.animation.play();
            player1.animation.setOffsetY(0*128);
            player1.moveY(2);
        }
        else if (isPressed(KeyCode.RIGHT)) {
            player1.Direction="RIGHT";
            player1.animation.play();
            player1.animation.setOffsetY(128*10);
            if (player1.playerCollideWalls()) player1.moveX(0);
            player1.moveX(2);
        }
        else if (isPressed(KeyCode.LEFT)) {
            player1.Direction="LEFT";
            player1.animation.play();
            player1.animation.setOffsetY(9*128);
            player1.moveX(-2);
        }
        else if (isPressed(KeyCode.G)) {
            player1.animation.play();
            if(player1.Direction.equals("RIGHT")){
                player1.animation.setOffsetY(1*128);
            }
            if(player1.Direction.equals("LEFT")) {
                player1.animation.setOffsetY(2 * 128);
            }
        }
        else if (isPressed(KeyCode.L)) {
            player1.animation.play();
            if(player1.Direction.equals("RIGHT")){
                player1.animation.setOffsetY(3*128);
            }
            if(player1.Direction.equals("LEFT")) {
                player1.animation.setOffsetY(4 * 128);
            }
        }

        else if(isPressed(KeyCode.D))
        {
            player1.animation.play();
            if(player1.Direction.equals("RIGHT")){
                Bullet bullet = new Bullet();
                player1.animation.setOffsetY(5*128);
                bullet.setVelocity(new Point2D(7, 0));
                System.out.println(player1.getLayoutX()+" "+player1.getLayoutY());
                addBullet(bullet, player1.getTranslateX()+200, player1.getTranslateY()+230);
            }
            if(player1.Direction.equals("LEFT")) {

                Bullet bullet = new Bullet();
                player1.animation.setOffsetY(6 * 128);
                bullet.setVelocity(new Point2D(5, 0));
                addBullet(bullet, player1.getTranslateX()+150, player1.getTranslateY()+230);
            }
        }
        else{
            player1.animation.stop();
        }
    }

    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        player1root = FXMLLoader.load(getClass().getResource("Client.fxml"));
        Scene scene=new Scene(player1root);

        player1root.getChildren().addAll(player1,player2);

        scene.setOnKeyPressed(keyevent->keys.put(keyevent.getCode(),true));
        scene.setOnKeyReleased(keyevent-> {
            keys.put(keyevent.getCode(), false);
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    update();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                BulleteUpdate();
            }
        };
        timer.start();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws  Exception {
        launch(args);

        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 6789);
        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (true){
            sentence = inFromUser.readLine();
            outToServer.println(sentence);
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
        }
    }
}
