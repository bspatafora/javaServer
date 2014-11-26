package javaserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private final int port;
    private final RouterFactory routerFactory;

    public Server(int port, RouterFactory routerFactory) {
        this.port = port;
        this.routerFactory = routerFactory;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Executor threadPool = Executors.newFixedThreadPool(5);
            while (true) {
                Socket socket = serverSocket.accept();
                threadPool.execute(new Worker(socket, routerFactory.buildRouter()));
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
