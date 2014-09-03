package com.bspatafora.java_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void start(Integer port) {
        try (
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            out.write(statusLine());
            out.write(body());
            serverSocket.close();
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    public String statusLine() {
        return "HTTP/1.1 200 OK" + "\r\n" + "\r\n";
    }

    public String body() {
        return "Hello, world!";
    }
}
