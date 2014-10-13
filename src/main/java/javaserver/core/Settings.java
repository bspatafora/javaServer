package javaserver.core;

import javaserver.core.helpers.Log4JLogger;
import javaserver.core.helpers.Logger;

public class Settings {
    public static int port = 5000;
    public static String directory = "src/main/resources/cobspec/";
    public static final Logger LOGGER = new Log4JLogger();
}
