package com.bspatafora.java_server;

public class CommandLine {
    private int port;
    private String directory;

    public CommandLine (String[] args) {
        parseArguments(args);
    }

    public void parseArguments(String[] args) {
        try {
            switch (args[0]) {
                case "-p":
                    addArguments(args[1], args[3]);
                    break;
                case "-d":
                    addArguments(args[3], args[1]);
                    break;
                default:
                    defaultArguments();
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            defaultArguments();
        }
    }

    public int port() {
        return port;
    }

    public String directory() {
        return directory;
    }

    private void defaultArguments() {
        addArguments("5000", "/default/directory/");
    }

    private void addArguments(String port, String directory) {
        this.port = Integer.parseInt(port);
        this.directory = directory;
    }
}
