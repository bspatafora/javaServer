package com.bspatafora.java_server;

import java.util.HashMap;
import java.util.Map;

public class CommandLine {
    public static Map parseArguments(String[] args) {
        Map<String, Object> argMap = new HashMap<>();
        try {
            if (args[0].equals("-p")) {
                argMap.put("port", Integer.parseInt(args[1]));
                argMap.put("directory", args[3]);
            } else if (args[0].equals("-d")) {
                argMap.put("port", Integer.parseInt(args[3]));
                argMap.put("directory", args[1]);
            } else {
                argMap.put("port", 5000);
                argMap.put("directory", "/default/directory/");
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            argMap.put("port", 5000);
            argMap.put("directory", "/default/directory/");
        }
        return argMap;
    }
}
