package sample;

import java.io.PrintWriter;
import java.net.Socket;

public class ClientForSending implements Runnable{

    private Socket clienSocket;
    PrintWriter outToServer;
    Controller ob;

    public ClientForSending(Socket clienSocket, PrintWriter outToServer, Controller ob) {
        this.clienSocket = clienSocket;
        this.outToServer = outToServer;
        this.ob = ob;
    }

    @Override
    public void run() {
        while (true) {
            String sentence;
            sentence = ob.changeofPlayer1+"#"+ob.player.getTranslateX()+"#"+ob.player.getTranslateY();
            outToServer.println(sentence);
            System.out.println(sentence);
            outToServer.flush();
        }
    }
}
