package com.bspatafora.javaserver;

import com.bspatafora.cobspec.Router;
import com.bspatafora.helpers.CommandLine;

public class Main {
    public static int PORT = 5000;

    public static void main(String[] args) {
        PORT = new CommandLine(args).port();
        Server server = new Server(PORT, new Router(), true);
        System.out.println("Starting server on port " + PORT + "...");
        server.run();
    }
}
