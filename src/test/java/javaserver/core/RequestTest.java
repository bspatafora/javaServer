package javaserver.core;

import javaserver.core.constants.Header;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RequestTest {
    @Test
    public void initializeHeadersEmpty() throws Exception {
        Assert.assertEquals("Headers is empty on initialization", new ArrayList<String>(), new Request().headers());
    }

    @Test
    public void initializeContentLengthZero() throws Exception {
        assertEquals("Content length is 0 on initialization", 0, new Request().contentLength());
    }

    @Test
    public void initializeCredentialsEmpty() throws Exception {
        assertEquals("Credentials is empty on initialization", "", new Request().credentials());
    }

    @Test
    public void hasHeaderWhenNo() throws Exception {
        assertFalse("False when corresponding header is not present", new Request().hasHeader(Header.CONTENT_LENGTH));
    }

    @Test
    public void hasHeaderWhenYes() throws Exception {
        Request requestWithContentLength = new Request();
        requestWithContentLength.addHeader(Header.CONTENT_LENGTH + "0");
        assertTrue("True when corresponding header is present", requestWithContentLength.hasHeader(Header.CONTENT_LENGTH));
    }

    @Test
    public void getHeaderWhenNotPresent() throws Exception {
        assertEquals("Returns empty string when no header matches passed header signature", "", new Request().getHeader(Header.CONTENT_LENGTH));
    }

    @Test
    public void getHeaderWhenPresent() throws Exception {
        Request requestWithContentLength = new Request();
        String contentLengthHeader = Header.CONTENT_LENGTH + "0";
        requestWithContentLength.addHeader(contentLengthHeader);
        assertEquals("Returns header matching passed header signature when present", contentLengthHeader, requestWithContentLength.getHeader(Header.CONTENT_LENGTH));
    }

    @Test
    public void requestString() throws Exception {
        String requestString = "POST /form HTTP/1.1\r\nContent-Length: 4\r\n\r\nTest\r\n";
        InputStream inputStream = new ByteArrayInputStream(requestString.getBytes(StandardCharsets.UTF_8));
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        Request request = new RequestFactory(in).build();
        assertEquals("Generated request string is equal to the original request string", requestString, request.requestString());
    }
}
