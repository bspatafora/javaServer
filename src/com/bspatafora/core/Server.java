package com.bspatafora.core;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {
    private int port;
    private Handler router;
    private Boolean multiThreaded;

    public Server(int port, Handler router, Boolean multiThreaded) {
        this.port = port;
        this.router = router;
        this.multiThreaded = multiThreaded;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)
        ) {
            if (multiThreaded) {
                while(true) {
                    new Thread(new Worker(serverSocket.accept(), router)).start();
                }
            } else {
                while(true) {
                    new Worker(serverSocket.accept(), router).run();
                }
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
