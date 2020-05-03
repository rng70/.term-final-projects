package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.ArrayList;


public class Character extends Pane{

    public  static ArrayList<Rectangle> walls= new ArrayList<>();
    ImageView imageView;
    int count = 5;
    int columns = 5;
    int offsetX = 0;
    int offsetY = 0;
    int width = 128;
    int height = 128;
    int score ;
    double life;
    String Direction;
    Circle removeCir = null;
    SpriteAnimation animation;
    public Point2D playerVelocity = new Point2D(0,0);

    public Character(ImageView imageView,int x,int y){
        this.imageView = imageView;
        this.imageView.setX(x);
        this.imageView.setY(y);
        this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(imageView,Duration.millis(250),count,columns,offsetX,offsetY,width,height);
        Direction="RiGHT";
        getChildren().addAll(imageView);
        score=0;
        life=100.0;
    }

    //Movement
    public void moveX(int x) throws  Exception{
        boolean right = x>0?true:false;
        for(int i = 0; i < Math.abs(x); i++) {
            if (right) {
                this.setTranslateX(this.getTranslateX() + 1);
            }
            else{
                this.setTranslateX(this.getTranslateX() - 1);
            }
        }
    }
    public void moveY(int y) {
        boolean down = y > 0 ? true : false;
        for (int i = 0; i < Math.abs(y); i++) {
            if (down) {
                this.setTranslateY(this.getTranslateY() + 1);
                }
            else this.setTranslateY(this.getTranslateY() - 1);

        }
    }

   public  void CreateWalls()
   {
       Rectangle r=new Rectangle(180,40,220,10);
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
    public double getPositionX(){
        return this.imageView.getX();
    }
    public double getPositionY(){
        return this.imageView.getY();
    }
    public void setImageViewPosition(double x,double y){
        this.imageView.setX(x);
        this.imageView.setY(y);
    }
}
