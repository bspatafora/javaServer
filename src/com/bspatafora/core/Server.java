package com.bspatafora.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
            Executor threadPool = Executors.newFixedThreadPool(5);
            while (multiThreaded) {
                Socket socket = serverSocket.accept();
                threadPool.execute(new Worker(socket, router));
            }
            while (!multiThreaded) {
                new Worker(serverSocket.accept(), router).run();
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
