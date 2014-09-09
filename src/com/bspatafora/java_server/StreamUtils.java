package com.bspatafora.java_server;

public class StreamUtils {
    static String streamToString(java.io.InputStream inputStream) {
        java.util.Scanner scanner = new java.util.Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
