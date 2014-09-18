package com.bspatafora.java_server;

public class Main {
    public static int PORT = 5000;

    public static void main(String[] args) {
        PORT = new CommandLine(args).port();
        Server server = new Server(PORT, true);
        System.out.println("Starting server on port " + PORT + "...");
        server.run();
    }
}
