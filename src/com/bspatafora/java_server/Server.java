package com.bspatafora.java_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private int port;

    public Server(int serverPort) {
        port = serverPort;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            out.write(statusLine());
            out.write(headers());
            out.write(body());
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    private String statusLine() {
        return "HTTP/1.1 200 OK" + "\r\n";
    }

    private String headers() {
        return "Connection: close" + "\r\n" + "\r\n";
    }

    private String body() {
        return "Hello, world!";
    }
}
