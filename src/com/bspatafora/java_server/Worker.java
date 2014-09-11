package com.bspatafora.java_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Worker implements Runnable {
    private Socket socket;

    public Worker(Socket clientSocket) {
        socket = clientSocket;
    }

    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            if (in.readLine().startsWith("POST")) {
                out.write(statusLine());
                out.write("You made a POST request.");
            } else {
                out.write(statusLine());
                out.write(body());
            }
            out.flush();
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    private String statusLine() {
        return "HTTP/1.1 200 OK" + "\r\n" + "\r\n";
    }

    private String body() {
        return "Hello, world!";
    }
}
