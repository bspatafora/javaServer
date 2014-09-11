package com.bspatafora.java_server;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map argMap = CommandLine.parseArguments(args);
        Server server = new Server((Integer) argMap.get("port"), true);
        server.run();
    }
}
