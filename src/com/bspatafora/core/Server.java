package com.bspatafora.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class Server implements Runnable {
    private final int port;
    private final Handler router;
    private final Boolean multiThreaded;

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
