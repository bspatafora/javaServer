package com.bspatafora.core;

import com.bspatafora.core.helpers.Log4JLogger;
import com.bspatafora.core.helpers.Logger;
import com.bspatafora.core.helpers.NullLogger;

public class Settings {
    public static int port = 5000;
    public static String directory = "/Users/ben/Dropbox/dev/cob_spec/public/";
    public static final Logger LOGGER = new Log4JLogger();
}
