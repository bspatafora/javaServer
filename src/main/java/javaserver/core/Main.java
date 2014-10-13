package main.java.javaserver.core;

import main.java.javaserver.cobspec.RouterFactory;
import main.java.javaserver.core.helpers.CommandLine;

class Main {
    public static void main(String[] args) {
        CommandLine parsedArguments = new CommandLine(args);
        Settings.port = parsedArguments.port();
        Settings.directory = parsedArguments.directory();

        System.out.println("Starting server on port " + Settings.port + "...");
        new Server(Settings.port, new RouterFactory(), true).run();
    }
}
