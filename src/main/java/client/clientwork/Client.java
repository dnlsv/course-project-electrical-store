package client.clientwork;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    public static Socket socket;
    public static ObjectOutputStream outputStream;
    public static ObjectInputStream inputStream;

    public static void startClient(){
        try {
            socket = new Socket("127.0.01", 2524);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
