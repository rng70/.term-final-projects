package sample;

import java.io.PrintWriter;
import java.net.Socket;

public class ClientForSending implements Runnable {

    private Socket clientSocket;
    PrintWriter outToServer;
    Controller ob;

    public ClientForSending(Socket clientSocket,PrintWriter outToServer,Controller ob){
        this.clientSocket = clientSocket;
        this.outToServer = outToServer;
        this.ob = ob;
    }

    @Override
    public void run(){
        while (true){
            String sentence ;
            sentence = ob.changeofPlayer2+"#"+ob.player2.getTranslateX()+"#"+ob.player2.getTranslateY()+"#"+ob.player2.score+ "#"
                    +ob.player2.lifeOfPlayer+"#"+MainPack.PlayerNameAndWin.getPlayerName();
            outToServer.println(sentence);
            System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
            outToServer.flush();
        }
    }
}
