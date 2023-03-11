package server.serverwork;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() {
    }

    ;

    public void serverStart() {
        int counter = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(2524);
            System.out.println("Server started...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress().getHostName() + " connected");
                counter++;
                System.out.println("Client № " + counter + " is connected");
                ServerThread thread = null;
                thread = new ServerThread(socket, counter);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
