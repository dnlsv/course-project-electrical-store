package server;

import server.serverwork.Server;

public class ServerMain {

    public static void main(String[] args) {
        Server server = new Server();
        server.serverStart();
    }
}
