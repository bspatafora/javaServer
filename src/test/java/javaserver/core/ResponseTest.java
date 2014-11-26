package javaserver.core;

import javaserver.cobspec.handler.NotFound;
import javaserver.core.constants.Status;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ResponseTest {
    @Test
    public void initializeStatus200() throws Exception {
        Assert.assertEquals("Status defaults to '200 OK'", Status.OK, new Response().status());
    }

    @Test
    public void protocolVersion() throws Exception {
        Assert.assertEquals("Protocol version is 'HTTP/1.1'", Status.HTTP11, new Response().protocolVersion());
    }

    @Test
    public void initializeHeadersEmpty() throws Exception {
        assertEquals("Headers is empty on initialization", new ArrayList<String>(), new Response().headers());
    }

    @Test
    public void initializeBodyEmpty() throws Exception {
        byte[] emptyByteArray = new byte[0];
        assertTrue("Body is empty on initialization", Arrays.equals(emptyByteArray, new Response().body()));
    }

    @Test
    public void head() throws Exception {
        String responseHead = "HTTP/1.1 404 Not Found\r\nContent-Type: text/html\r\n\r\n";

        Response response = new NotFound().response();
        assertEquals("Generated response head is correct", responseHead, response.head());
    }
}