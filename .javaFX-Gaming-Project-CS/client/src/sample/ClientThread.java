package sample;

import javafx.application.Platform;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {


    PrintWriter outToServer;
    BufferedReader inFromServer;
    Controller ob;

    public ClientThread(Controller ob) {
        this.ob = ob;
    }

    @Override
    public void run() {
        try {
            Socket clienSocket = new Socket("192.168.43.121", 6789);
            outToServer = new PrintWriter(clienSocket.getOutputStream(), true);
            inFromServer = new BufferedReader(new InputStreamReader(clienSocket.getInputStream()));

            ClientForSending clientForSending = new ClientForSending(clienSocket, outToServer, this.ob);
            Thread t = new Thread(clientForSending);
            t.start();

            while (true) {
                String modifiedSentence = inFromServer.readLine();
                String[] parts = modifiedSentence.split("#");
                try {
                    try {
                        if ( parts[0]!=null && parts[0].contains("PLAYER1")) {
                            GameStageController gameStageController = new GameStageController();
                            if (parts.length == 6 && parts[5] != null) gameStageController.secondPlayerName(parts[5]);
                        }
                    }
                catch (Exception e) {
                    e.printStackTrace();
                }
                    int len = parts.length;

                    if(len >=5){
                        if (parts[0]!=null ) {
                            Platform.runLater(() -> {
                                try {

                                    double x = Double.parseDouble(parts[1]);
                                    double y = Double.parseDouble(parts[2]);
                                    int scoreOf1 = Integer.parseInt(parts[3].trim());
                                    double lifeOf1 =  Double.parseDouble(parts[4]);


                                    if (parts[0].contains("PLAYER1")) {
                                        ob.player.setTranslateX(x);
                                        ob.player.setTranslateY(y);
                                        ob.player.score = scoreOf1;
                                        ob.player.lifeOfPlayer = lifeOf1;

                                    }
                                    ob.setPlayer1Position(parts[0]);


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
