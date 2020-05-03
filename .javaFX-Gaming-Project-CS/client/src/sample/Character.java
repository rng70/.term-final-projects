package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.ArrayList;


public class Character extends Pane{


    public static ArrayList<Rectangle> walls = new ArrayList<>();

    ImageView imageView;
    int count = 5;
    int columns = 5;
    int offsetX = 0;
    int offsetY = 0;
    int width = 128;
    int height = 128;
    double lifeOfPlayer;
    double rateOfLifeLoss;
    int score;
    String Direction;
    Circle removeCir = null;
    SpriteAnimation animation;

    //Newly added
    double totalBulletCount ;


    //character constructor
    public Character(ImageView imageView, int x, int y) {
        this.imageView = imageView;
        this.imageView.setX(x);
        this.imageView.setY(y);
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(imageView, Duration.millis(250), count, columns, offsetX, offsetY, width, height);
        Direction = "DOWN";
        getChildren().addAll(imageView);
        lifeOfPlayer=100.0;
        score = 0;
        totalBulletCount = 100;
    }

    //movement
    public void moveX(int x) {
        boolean right = x > 0 ? true : false;
        for (int i = 0; i < Math.abs(x); i++) {
            if (right) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
        }
    }

    public void moveY(int y) {
        boolean down = y > 0 ? true : false;
        for (int i = 0; i < Math.abs(y); i++) {
            if (down) this.setTranslateY(this.getTranslateY() + 1);
            else this.setTranslateY(this.getTranslateY() - 1);
        }
    }

    public void CreateWalls() {
        Rectangle r = new Rectangle(180, 40, 220, 10);
        r.setFill(Color.BLACK);
        walls.add(r);
    }

    public boolean playerCollideWalls() {
        for (Rectangle r : walls) {
            if (this.getBoundsInParent().intersects(r.getBoundsInParent()))
                return true;
        }
        return false;
    }

    public double getRateOfLifeLoss() {
        return rateOfLifeLoss;
    }

    public void setRateOfLifeLoss(double rateOfLifeLoss) {
        this.rateOfLifeLoss = rateOfLifeLoss;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public double getTotalBulletCount() {
        return totalBulletCount;
    }

    public void setTotalBulletCount(double totalBulletCount) {
        this.totalBulletCount = totalBulletCount;
    }
    public void setImageViewPosition(double x,double y){
        this.imageView.setX(x);
        this.imageView.setY(y);
    }
}

