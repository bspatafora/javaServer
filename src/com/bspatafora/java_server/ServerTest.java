package com.bspatafora.java_server;

import org.junit.Before;
import org.junit.Test;

import java.net.Socket;

import static org.junit.Assert.*;

public class ServerTest {
    Thread serverThread;

    @Before
    public void initialize() {
        serverThread = new Thread(new Server(9000));
    }

    @Test
    public void testServerGetRootStatusLine() throws Exception {
        serverThread.start();
        Socket testSocket = new Socket("localhost", 9000);
        String response = Stream.toString(testSocket.getInputStream());
        assertTrue("Response starts with a status line of 'HTTP/1.1 200 OK'", response.startsWith("HTTP/1.1 200 OK"));
    }

    @Test
    public void testServerGetRootBody() throws Exception {
        serverThread.start();
        Socket testSocket = new Socket("localhost", 9000);
        String response = Stream.toString(testSocket.getInputStream());
        assertTrue("Response ends with a body consisting of string 'Hello, World!'", response.endsWith("Hello, world!"));
    }
}