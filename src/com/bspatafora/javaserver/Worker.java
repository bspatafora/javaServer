package com.bspatafora.javaserver;

import com.bspatafora.handlers.Form;
import com.bspatafora.handlers.Redirect;
import com.bspatafora.handlers.Root;
import com.bspatafora.handlers.Unregistered;
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

public class Worker implements Runnable {
    private Socket socket;
    private final Logger logger = LogManager.getLogger();

    public Worker(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            long startTime = System.nanoTime();
            logger.info(requestReceived());

            Request request = new RequestFactory(in).build();
            logger.info(request.requestString());

            out.write(responseForRoute(request).responseString());
            out.flush();
            logger.info(responseTime(startTime));
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private Response responseForRoute(Request request) {
        Response response;
        switch (request.route()) {
            case "/":
                response = new Root().response(request);
                break;
            case "/form":
                response = new Form().response(request);
                break;
            case "/redirect":
                response = new Redirect().response(request);
                break;
            default:
                response = new Unregistered().response(request);
                break;
        }
        return response;
    }

    private String responseTime(long startTime) {
        return "Response took " + elapsedTime(startTime) + " milliseconds\n";
    }

    private String requestReceived() {
        return "Request received " + currentTimeStamp() + ":\n";
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
