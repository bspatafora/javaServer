package com.bspatafora.java_server;

import java.util.HashMap;
import java.util.Map;

public class CommandLine {
    public static Map parseArguments(String[] args) {
        Map<String, Object> argMap = new HashMap<>();
        if (args[0].equals("-p")) {
            argMap.put("port", Integer.parseInt(args[1]));
            argMap.put("directory", args[3]);
        } else {
            argMap.put("port", Integer.parseInt(args[3]));
            argMap.put("directory", args[1]);
        }
        return argMap;
    }
}
