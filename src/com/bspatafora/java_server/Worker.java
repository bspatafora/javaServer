package com.bspatafora.java_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Worker implements Runnable {
    private Socket socket;

    public Worker(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            Request request = new RequestFactory(in).build();

            if (request.body() != null) {
                Resources.form_resource = request.body();
            } else if (request.method().equals(Methods.DELETE)) {
                Resources.form_resource = "";
            }

            out.write(StatusLines.OK);
            out.write("\r\n");
            if (request.route().equals(Routes.FORM)) {
                out.write(Resources.form_resource);
            } else {
                out.write("Hello, world!");
            }
            out.flush();
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
}
