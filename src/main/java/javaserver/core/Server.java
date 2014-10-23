package javaserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class Server implements Runnable {
    private final int port;
    private final RouterFactory routerFactory;
    private final Boolean multiThreaded;

    public Server(int port, RouterFactory routerFactory, Boolean multiThreaded) {
        this.port = port;
        this.routerFactory = routerFactory;
        this.multiThreaded = multiThreaded;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)
        ) {
            Executor threadPool = Executors.newFixedThreadPool(5);
            while (multiThreaded) {
                Socket socket = serverSocket.accept();
                threadPool.execute(new Worker(socket, routerFactory.buildRouter()));
            }
            while (!multiThreaded) {
                new Worker(serverSocket.accept(), routerFactory.buildRouter()).run();
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
