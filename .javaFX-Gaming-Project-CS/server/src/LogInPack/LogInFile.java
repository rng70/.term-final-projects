package LogInPack;

import FXMLPack.BeforeGameStartController;
import MainPack.PlayerNameAndWin;
import VoiceControl.VoiceControlOfGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

public class LogInFile {

    @FXML
    Button signInButton ;
    @FXML
    Button signUpButton;
    @FXML
    TextField textArea;
    @FXML
    PasswordField passwordField;
    PlayerNameAndWin playerNameAndWin = new PlayerNameAndWin();
    FXMLPack.BeforeGameStartController nn = new BeforeGameStartController();
    VoiceControlOfGame vc = new VoiceControlOfGame();
    ArrayList<Person>list = new ArrayList<>();

    String toPass,isSign;
    Person p1 = new Person("Tanin","tanin");
    Person p2 = new Person("Ovi","ovi");


    public void foundString(){

        String name = playerNameAndWin.getPlayerName();
        toPass = "Welcome "+ name +". You have "+isSign+" successfully. For further instruction it is recommended that Please press" +
                " the proceed button and follow further instruction. "+name+" Good luck!";

    }

    public void signInButtonOnAction(ActionEvent e) throws Exception{

        this.save();
        isSign = "sign in";
        String text,password;
        text = textArea.getText();
        password = passwordField.getText();

        try{
            FileInputStream fileInputStream = new FileInputStream("LoggedInfileOfPlayer.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Person person = (Person) objectInputStream.readObject();

            while(person != null){
                System.out.println(person.getName() + " " + person.getPassWord());
                if(person.getName().equals(text) && person.getPassWord().equals(password)){

                    System.out.println("LogIn Successful");
                    playerNameAndWin.setPlayerName(text);

                    Parent menu = FXMLLoader.load(getClass().getResource("/FXMLPack/BeforegameStart.fxml"));
                    Scene scene;
                    scene = new Scene(menu);
                    Stage window = (Stage) ((Node) (e.getSource())).getScene().getWindow();
                    window.setScene(scene);
                    window.show();

                    this.foundString();
                    nn.setTextArea2(toPass);
                    vc.Text2Voice(toPass);

                    break;
                }
                person = (Person) objectInputStream.readObject();
            }
        }catch (FileNotFoundException fnFe){
            fnFe.printStackTrace();
        }
    }
    public void signUpButtonOnAction(ActionEvent event) {
        this.save();
        isSign = "sign up";
        String text,password;
        text = textArea.getText();
        password = passwordField.getText();
        Person p = new Person(text,password);
        try{
            FileInputStream fileInputStream = new FileInputStream("LoggedInfileOfPlayer.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(fileInputStream));
                while (true){
                    try{
                        Person person = (Person) objectInputStream.readObject();
                        list.add(person);
                        System.out.println("Adding to list "+ person.getName()+" "+person.getPassWord());
                    }catch (Exception e){
                        System.out.println("Exception after while loop " + e.toString());
                        break;
                    }finally {
                        objectInputStream.close();
                        fileInputStream.close();
                    }
                }
            list.add(p);

            FileOutputStream fileOutputStream = new FileOutputStream("LoggedInfileOfPlayer.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
            list.clear();

            System.out.println("SignUp Successful");
            playerNameAndWin.setPlayerName(text);

            Parent menu = FXMLLoader.load(getClass().getResource("/FXMLPack/BeforegameStart.fxml"));
            Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            window.setScene(new Scene(menu));
            window.show();

            this.foundString();
            nn.setTextArea2(toPass);
            vc.Text2Voice(toPass);

        }catch (FileNotFoundException fnFe){
            fnFe.printStackTrace();
        }catch(IOException ioe){
            System.out.println("IOException when Sign Ip " + ioe.toString());
            ioe.printStackTrace();
        }
    }
    public void save() {
        try{

            File fileOfCurrentPlayer = new File("LoggedInfileOfPlayer.txt");
            if(!fileOfCurrentPlayer.exists()){
                fileOfCurrentPlayer.createNewFile();
                System.out.println("File Was Created");
                FileOutputStream fileOutputStream = new FileOutputStream(fileOfCurrentPlayer);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(fileOutputStream));

//                objectOutputStream.writeObject(p1);
//                objectOutputStream.writeObject(p2);
                list.add(p1);
                list.add(p2);
                objectOutputStream.writeObject(list);
                objectOutputStream.close();
                fileOutputStream.close();
                list.clear();
            }
            else{
                System.out.println("Already Exits");
                System.out.println("Fucking File was not Created for some reason");
            }

        }catch (FileNotFoundException fnFe){
            fnFe.printStackTrace();
        }catch(IOException ioe){
            System.out.println("IOException when sign Up " + ioe.toString());
        }
    }
}
