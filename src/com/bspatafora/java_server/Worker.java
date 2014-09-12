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
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            int contentLength = 0;
            StringBuilder requestBuilder = new StringBuilder();
            String currentLine = in.readLine();
            Boolean requestHasBody = isPost(currentLine) || isPut(currentLine);

            requestBuilder.append(currentLine);
            while ((currentLine = in.readLine()).length() > 0) {
                requestBuilder.append(currentLine);
                if (requestHasBody) {
                    if (contentLengthHeader(currentLine)) {
                        contentLength = parseContentLength(currentLine);
                    }
                }
            }
            String request = requestBuilder.toString();

            StringBuilder rawBody = new StringBuilder();
            if (requestHasBody) {
                for (int i = 0; i < contentLength; i++) {
                    rawBody.append((char) in.read());
                }
            }
            String body = rawBody.toString();

            String status200 = "HTTP/1.1 200 OK\r\n\r\n";
            if (requestHasBody) {
                Resources.form_resource = body;
                out.write(status200);
            } else if (request.contains("DELETE")) {
                Resources.form_resource = "";
                out.write(status200);
            } else if (request.contains("/form")) {
                out.write(status200);
                out.write(Resources.form_resource);
            } else {
                out.write(status200);
                out.write("Hello, world!");
            }
            out.flush();
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    private boolean isPut(String currentLine) {
        return currentLine.startsWith("PUT");
    }

    private boolean isPost(String currentLine) {
        return currentLine.startsWith("POST");
    }

    private int parseContentLength(String currentLine) {
        return Integer.parseInt((currentLine.substring(16)));
    }

    private boolean contentLengthHeader(String currentLine) {
        return currentLine.startsWith("Content-Length: ");
    }
}
