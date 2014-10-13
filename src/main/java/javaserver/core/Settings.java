package main.java.javaserver.core;

import main.java.javaserver.core.helpers.Log4JLogger;
import main.java.javaserver.core.helpers.Logger;

public class Settings {
    public static int port = 5000;
    public static String directory = "src/main/resources/";
    public static final Logger LOGGER = new Log4JLogger();
}
