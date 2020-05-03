package sample;

import java.net.*;

public class GameServer{

    GameServer() throws Exception{

        ServerSocket serverSocket = new ServerSocket(80);
        Socket clientSocket = serverSocket.accept();
    }
}
