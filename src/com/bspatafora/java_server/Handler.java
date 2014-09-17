package com.bspatafora.java_server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Handler implements Runnable {
    private Socket socket;
    private final Logger logger = LogManager.getLogger();

    public Handler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            long startTime = System.nanoTime();

            logger.info("Request received " + currentTimeStamp() + ":\n");

            Request request = new RequestFactory(in).build();

            logger.info(request.requestString());

            updateResources(request);
            Response response = new ResponseFactory(request).build();
            out.write(response.responseString());
            out.flush();

            logger.info("Response took " + elapsedTime(startTime) + " milliseconds\n");
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    private void updateResources(Request request) {
        if (request.route().equals(Routes.FORM)) {
            if (request.body() != null) {
                Resources.form_resource = request.body();
            } else if (request.method().equals(Methods.DELETE)) {
                Resources.form_resource = "";
            }
        }
    }

    private double elapsedTime(long startTime) {
        return (System.nanoTime() - startTime) / 1e6;
    }

    private String currentTimeStamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }
}
