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
import java.util.Scanner;

public class LogInFile {

    @FXML
    Button signInButton ;
    @FXML
    Button signUpButton;
    @FXML
    TextField textArea;
    @FXML
    PasswordField passwordField;
    MainPack.PlayerNameAndWin playerNameAndWin = new PlayerNameAndWin();
    FXMLPack.BeforeGameStartController nn = new BeforeGameStartController();
    VoiceControl.VoiceControlOfGame vc = new VoiceControlOfGame();

    String toPass,isSign;
    Person p1 = new Person("TaniN","taniN");
    Person p2 = new Person("Ovi","boss");

    public void foundString(){
        String name = playerNameAndWin.getPlayerName();
        System.out.println(name);

        toPass = "Welcome "+ name +". You have "+isSign+" successfully. For further instruction it is recommended that Please press" +
                " the proceed button and follow further instruction. "+name+" Good luck!";
    }

    public void signInButtonOnAction(ActionEvent e) throws Exception{

        //this.save();
        isSign = "sign in";
        String text,password;
        text = textArea.getText();
        password = passwordField.getText();

        try{
            File file = new File("LoggedInFile.txt");
            Scanner scanner = new Scanner(file);

            while(scanner.hasNext()){
               String str = scanner.nextLine();
               String[] arr = str.split("#");
                if(arr[0].equals(text) && arr[1].equals(password)){

                    System.out.println("LogIn Successful");
                    playerNameAndWin.setPlayerName(text);

                    Parent menu = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
                    Scene scene;
                    scene = new Scene(menu);
                    Stage window = (Stage) ((Node) (e.getSource())).getScene().getWindow();
                    window.setScene(scene);
                    window.show();

                    this.foundString();
                    nn.setTextArea2(toPass);
                    //vc.Text2Voice(toPass);
                    break;
                }
            }
        }catch (FileNotFoundException fnFe){
            fnFe.printStackTrace();
        }
    }
    public void signUpButtonOnAction(ActionEvent event) {
        //this.save();
        isSign = "sign up";
        String text,password;
        text = textArea.getText();
        password = passwordField.getText();
        boolean b = false;
//        if(text != "" && password!= ""){
//            b=true;
//        }
//if(b){
        try{
            File file = new File("LoggedInFile.txt");
            FileWriter fileWriter = new FileWriter(file,true);
            String s = text+"#"+password+"\n";
            fileWriter.write(s);
            fileWriter.close();

            Person p = new Person(text,password);
            System.out.println("SignUp Successful");
            playerNameAndWin.setPlayerName(text);

            Parent menu = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
            Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            window.setScene(new Scene(menu));
            window.show();

            this.foundString();
            nn.setTextArea2(toPass);
           // vc.Text2Voice(toPass);

        }catch (FileNotFoundException fnFe){
            fnFe.printStackTrace();
        }catch (IOException ioe){
            System.out.println("IOException when Sign Ip " + ioe.toString());
        }
    }
    public void save() {
        try{

            File fileOfCurrentPlayer = new File("LoggedInFile.txt");
            if(!fileOfCurrentPlayer.exists()){

                fileOfCurrentPlayer.createNewFile();
                FileWriter fileWriter = new FileWriter(fileOfCurrentPlayer,true);

                System.out.println("File Was Created");

                Person p1 = new Person("TaniN","taniN");
                Person p2 = new Person("Ovi","boss");

                String s = "Tanin"+"#"+"tanin"+"\n";
                String s2 = "Ovi"+"#"+"boss"+"\n";

                fileWriter.write(s);
                fileWriter.write(s2);
                fileWriter.close();
            }
            else{
                System.out.println("File was not Created for some reason");
            }

        }catch (FileNotFoundException fnFe){
            fnFe.printStackTrace();
        }catch(IOException ioe){
            System.out.println("IOException when sign Up " + ioe.toString());
        }
    }
}
