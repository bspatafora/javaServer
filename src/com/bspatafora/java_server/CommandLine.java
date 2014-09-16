package com.bspatafora.java_server;

import java.util.HashMap;
import java.util.Map;

public class CommandLine {
    private static final String PORT = "port";
    private static final String DIRECTORY = "directory";
    private Map<String, Object> argMap;

    public CommandLine (String[] args) {
        this.argMap = parseArguments(args);
    }

    public Map<String, Object> parseArguments(String[] args) {
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

    public int port() {
        return (Integer) argMap.get(PORT);
    }

    public String directory() {
        return (String) argMap.get(DIRECTORY);
    }

    private Map addDefaultArguments(Map<String, Object> argMap) {
        return addArguments(argMap, 5000, "/default/directory/");
    }

    private Map addArguments(Map<String, Object> argMap, int port, String directory) {
        argMap.put(PORT, port);
        argMap.put(DIRECTORY, directory);
        return argMap;
    }
}
