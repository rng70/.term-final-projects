package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HighScoreController implements Initializable {

    public void BackButtonPushed(ActionEvent event) throws IOException{
        Parent menu_new;
        menu_new = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene_new;
        scene_new = new Scene(menu_new);
        Stage window;
        window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        window.setScene(scene_new);
        window.show();
    }

    @FXML
    private ListView<String> listview;
    private ObservableList<String> list = FXCollections.observableArrayList();
    private int[] arr=new int[100];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            File file = new File("data.txt");
            if (!file.exists()) file.createNewFile();
            Scanner sc=new Scanner(file);
            int i=0;
            while(sc.hasNext())
            {
                int num=sc.nextInt();
                arr[i++]=num;
            }

            Arrays.sort(arr);
            for(int j=99;j>=100-i;j--){
                System.out.println(arr[j]);
                list.add(Integer.toString(arr[j]));
            }
            listview.setItems(list);
        }catch(Exception e){
                e.printStackTrace();
        }
    }
}

