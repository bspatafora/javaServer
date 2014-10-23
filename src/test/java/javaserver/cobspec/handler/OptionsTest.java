package javaserver.cobspec.handler;

import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Header;
import org.junit.Test;

import static org.junit.Assert.*;

public class OptionsTest {

    @Test
    public void responseContainsAllowedMethods() throws Exception {
        Request request = new Request();
        request.setRoute("/method_options");

        Response response = new Options(request).response();
        assertTrue("Response contains methods allowed by '/method_options'", response.headers().contains(Header.ALLOW + "HEAD, POST, GET, OPTIONS, PUT"));
    }
}