package com.bspatafora.java_server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)
        ) {
            while(true) {
                new Thread(new Worker(serverSocket.accept())).start();
            }
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
}
