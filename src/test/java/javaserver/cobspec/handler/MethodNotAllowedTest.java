package javaserver.cobspec.handler;

import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Status;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MethodNotAllowedTest {
    @Test
    public void responseStatus() {
        Response response = new MethodNotAllowed().response(new Request());
        assertEquals("Status is '405 Method Not Allowed'", Status.NOT_ALLOWED, response.status());
    }
}