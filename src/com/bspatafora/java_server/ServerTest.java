package com.bspatafora.java_server;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServerTest {

    @Test
    public void testStatusLineStatusCode() throws Exception {
        Server server = new Server();
        assertTrue("Status line contains a status code of 200", server.statusLine().contains("200"));
    }

    @Test
    public void testStatusLineBlankLine() throws Exception {
        Server server = new Server();
        assertTrue("Status line ends with a blank line", server.statusLine().endsWith("\r\n\r\n"));
    }

    @Test
    public void testBody() throws Exception {
        Server server = new Server();
        assertTrue("Body contains 'Hello, world!'", server.body().contains("Hello, world!"));
    }
}