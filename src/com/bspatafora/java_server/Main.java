package com.bspatafora.java_server;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map serverOptions = CommandLine.parseArguments(args);
        Server server = new Server((Integer) serverOptions.get(CommandLine.PORT));
        System.out.println("Starting server on port " + serverOptions.get(CommandLine.PORT) + "...");
        server.run();
    }
}
