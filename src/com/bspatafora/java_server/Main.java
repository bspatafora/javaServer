package com.bspatafora.java_server;

public class Main {
    public static void main(String[] args) {
        int port = new CommandLine(args).port();
        Server server = new Server(port, true);
        System.out.println("Starting server on port " + port + "...");
        server.run();
    }
}
