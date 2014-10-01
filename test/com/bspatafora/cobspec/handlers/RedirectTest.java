package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.constants.Header;
import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectTest {
    private static final Request getRequest = new Request();
    static {
        getRequest.setMethod(Method.GET);
    }

    @Test
    public void responseStatus() throws Exception {
        Response response = new Redirect().response(getRequest);
        assertEquals("Status is '302 Moved Permanently'", Status.MOVED_PERMANENTLY, response.status());
    }

    @Test
    public void responseLocationHeader() throws Exception {
        Response response = new Redirect().response(getRequest);
        assertTrue("Location header is 'http://localhost:5000/'", response.headers().contains(Header.LOCATION + "http://localhost:5000/"));
    }
}