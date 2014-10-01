package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;
import com.bspatafora.core.helpers.HTTP;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MethodOptionsTest {
    private static final Request getRequest = new Request();
    private static final Request headRequest = new Request();
    private static final Request postRequest = new Request();
    private static final Request putRequest = new Request();
    private static final Request optionsRequest = new Request();

    @BeforeClass
    public static void createRequests() {
        getRequest.setMethod(Method.GET);
        headRequest.setMethod(Method.HEAD);
        postRequest.setMethod(Method.POST);
        putRequest.setMethod(Method.PUT);
        optionsRequest.setMethod(Method.OPTIONS);
    }

    @Test
    public void responseStatus() throws Exception {
        Response getResponse = new MethodOptions().response(getRequest);
        Response headResponse = new MethodOptions().response(headRequest);
        Response postResponse = new MethodOptions().response(postRequest);
        Response putResponse = new MethodOptions().response(putRequest);
        Response optionsResponse = new MethodOptions().response(optionsRequest);
        assertEquals("Status is '200 OK' when GET", Status.OK, getResponse.status());
        assertEquals("Status is '200 OK' when HEAD", Status.OK, headResponse.status());
        assertEquals("Status is '200 OK' when POST", Status.OK, postResponse.status());
        assertEquals("Status is '200 OK' when PUT", Status.OK, putResponse.status());
        assertEquals("Status is '200 OK' when OPTIONS", Status.OK, optionsResponse.status());
    }

    @Test
    public void responseAllowHeaderWhenOPTIONS() throws Exception {
        Response optionsResponse = new MethodOptions().response(optionsRequest);
        assertTrue("Status is '200 OK' when OPTIONS", optionsResponse.headers().contains(Header.ALLOW + HTTP.allowedMethods(MethodOptions.class)));
    }
}