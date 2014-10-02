package com.bspatafora.core.helpers;

import org.apache.logging.log4j.LogManager;

public class Log4JLogger implements Logger {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();

    public void requestReceived(String timeStamp) {
        logger.info("Request received " + timeStamp + "\n");
    }

    public void request(String request) {
        logger.info("Request:\n" + request);
    }

    public void responseTime(double elapsedTime) {
        logger.info("Response took " + elapsedTime + " milliseconds\n");
    }
}
