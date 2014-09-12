package com.bspatafora.java_server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {
    private int port;
    private boolean infinite;

    public Server(int serverPort, boolean continuous) {
        port = serverPort;
        infinite = continuous;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)
        ) {
            while(infinite) {
                new Thread(new Worker(serverSocket.accept())).start();
            }
            new Thread(new Worker(serverSocket.accept())).start();
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
}
