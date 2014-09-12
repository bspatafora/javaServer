package com.bspatafora.java_server;

import java.util.HashMap;
import java.util.Map;

public class CommandLine {
    public static Map parseArguments(String[] args) {
        Map<String, Object> argMap = new HashMap<>();
        try {
            switch (args[0]) {
                case "-p":
                    addArguments(argMap, Integer.parseInt(args[1]), args[3]);
                    break;
                case "-d":
                    addArguments(argMap, Integer.parseInt(args[3]), args[1]);
                    break;
                default:
                    addDefaultArguments(argMap);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            addDefaultArguments(argMap);
        }
        return argMap;
    }

    private static Map addDefaultArguments(Map<String, Object> argMap) {
        return addArguments(argMap, 5000, "/default/directory/");
    }

    private static Map addArguments(Map<String, Object> argMap, int port, String directory) {
        argMap.put("port", port);
        argMap.put("directory", directory);
        return argMap;
    }
}
