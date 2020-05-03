package sample;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

    static ArrayList<WorkerThread> threads =new ArrayList<>();
    static  String s1=null;

    public static void main(String argv[]) throws Exception {
        int workerThreadCount = 0;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("IP Address:- " + inetAddress.getHostAddress());
//        System.out.println("Host Name:- " + inetAddress.getHostName());
        int id = 1;
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            WorkerThread wt = new WorkerThread(connectionSocket, id);
            Thread t = new Thread(wt);
            t.start();
            workerThreadCount++;
//            System.out.println("Client [" + id + "] is now connected. No. of worker threads = " + workerThreadCount);
            id++;
        }
    }
}

class WorkerThread implements Runnable {

    ArrayList<WorkerThread> allThreads=Server.threads;
    PrintWriter outToClient2;
    private Socket connectionSocket;
    private int id ;

    public WorkerThread(Socket s, int id) {
        this.connectionSocket = s;
        this.id = id;
    }

    public void run() {

        allThreads.add(this);
        System.out.println(Server.threads.size());
        while (true) {
            String clientSentence;
            String capitalizedSentence;
            try {
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                outToClient2 = new PrintWriter(connectionSocket.getOutputStream());
                clientSentence = inFromClient.readLine();
                capitalizedSentence = clientSentence.toUpperCase();

                for(WorkerThread t : Server.threads) {
                    t.outToClient2.println(capitalizedSentence);
                    t.outToClient2.flush();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
