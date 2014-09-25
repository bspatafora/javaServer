package com.bspatafora.core;

import com.bspatafora.cobspec.Router;
import com.bspatafora.helpers.CommandLine;

public class Main {
    public static void main(String[] args) {
        CommandLine parsedArguments = new CommandLine(args);
        Settings.port = parsedArguments.port();
        Settings.directory = parsedArguments.directory();
        Server server = new Server(Settings.port, new Router(), true);
        System.out.println("Starting server on port " + Settings.port + "...");
        server.run();
    }
}
