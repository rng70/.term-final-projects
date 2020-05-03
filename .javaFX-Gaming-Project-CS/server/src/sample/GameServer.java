package sample;

import  java.net.*;

public class GameServer {

     GameServer() throws  Exception {
        ServerSocket serverSocket = new ServerSocket(80);
        System.out.println("Server Started!");
        Socket clientSocket=serverSocket.accept();
    }
}
