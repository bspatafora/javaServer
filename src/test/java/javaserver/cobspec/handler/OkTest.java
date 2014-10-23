package javaserver.cobspec.handler;

import javaserver.core.Response;
import javaserver.core.constants.Status;
import org.junit.Test;

import static org.junit.Assert.*;

public class OkTest {

    @Test
    public void responseStatus() throws Exception {
        Response response = new Ok().response();
        assertEquals("Status is '200 OK'", Status.OK, response.status());
    }
}