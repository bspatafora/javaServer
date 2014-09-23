package com.bspatafora.java_server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {
    private int port;
    private Boolean multiThreaded;

    public Server(int port, Boolean multiThreaded) {
        this.port = port;
        this.multiThreaded = multiThreaded;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)
        ) {
            if (multiThreaded) {
                while(true) {
                    new Thread(new Worker(serverSocket.accept())).start();
                }
            } else {
                while(true) {
                    new Worker(serverSocket.accept()).run();
                }
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
