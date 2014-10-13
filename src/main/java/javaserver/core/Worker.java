package javaserver.core;

import javaserver.core.helpers.Logger;

import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class Worker implements Runnable {
    private final Socket socket;
    private final Handler router;
    private final Logger logger = Settings.LOGGER;

    public Worker(Socket socket, Handler router) {
        this.socket = socket;
        this.router = router;
    }

    public void run() {
        try (OutputStream byteOut = socket.getOutputStream();
             PrintWriter textOut = new PrintWriter(byteOut, true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            long startTime = System.nanoTime();
            logger.requestReceived(currentTimeStamp());

            Request request = new RequestFactory(in).build();
            logger.request(request.requestString());

            Response response = router.response(request);
            textOut.write(response.head());
            textOut.flush();
            byteOut.write(response.body());
            logger.responseTime(elapsedTime(startTime));
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
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
