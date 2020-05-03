package sample;

import javafx.application.Platform;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {

    Controller ob;

    public ClientThread(Controller ob) {
        this.ob = ob;
    }

    @Override
    public void run() {
        try {
            Socket clienSocket = new Socket("localhost", 6789);
            PrintWriter outToServer = new PrintWriter(clienSocket.getOutputStream(), true);
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clienSocket.getInputStream()));

            ClientForSending clientForSending = new ClientForSending(clienSocket, outToServer, this.ob);
            Thread t = new Thread(clientForSending);
            t.start();

            while (true) {
                try {
                    String modifiedSentence = inFromServer.readLine();
                    String[] parts = modifiedSentence.split("#");
                    if (parts[0] != null) {
                        Platform.runLater(() -> {
                            try {
                                double x = Double.parseDouble(parts[1]);
                                double y = Double.parseDouble(parts[2]);
                                if (parts[0].contains("PLAYER2")) {
                                    ob.player2.setTranslateX(x);
                                    ob.player2.setTranslateY(y);
                                }
                                ob.setPlayer2Position(parts[0]);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    System.out.println("FROM SERVER: ");
                    System.out.println(modifiedSentence);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
