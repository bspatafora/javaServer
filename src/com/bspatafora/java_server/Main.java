package com.bspatafora.java_server;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map argMap = CmdLineUtils.parseArgs(args);
        Server server = new Server((Integer) argMap.get("port"));
        server.run();
    }
}
