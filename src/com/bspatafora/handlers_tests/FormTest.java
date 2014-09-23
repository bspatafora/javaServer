package com.bspatafora.handlers_tests;

import com.bspatafora.constants.Methods;
import com.bspatafora.constants.StatusLine;
import com.bspatafora.handlers.Form;
import com.bspatafora.helpers.Resources;
import com.bspatafora.java_server.*;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormTest {
    private static Request getRequest = new Request();
    private static Request postCosbyRequest = new Request();
    private static Request putHeathcliffRequest = new Request();
    private static Request deleteRequest = new Request();
    private static final String cosby = "cosby";
    private static final String heathcliff = "heathcliff";

    @BeforeClass
    public static void createRequests() {
        getRequest.setMethod(Methods.GET);
        postCosbyRequest.setMethod(Methods.POST);
        putHeathcliffRequest.setMethod(Methods.PUT);
        deleteRequest.setMethod(Methods.DELETE);

        postCosbyRequest.setBody(cosby);
        putHeathcliffRequest.setBody(heathcliff);
    }

    @Test
    public void responseProtocolVersion() throws Exception {
        Response getResponse = new Form().response(getRequest);
        Response postResponse = new Form().response(postCosbyRequest);
        Response putResponse = new Form().response(putHeathcliffRequest);
        Response deleteResponse = new Form().response(deleteRequest);
        assertEquals("Protocol version is 'HTTP/1.1' when GET", StatusLine.HTTP11, getResponse.protocolVersion());
        assertEquals("Protocol version is 'HTTP/1.1' when POST", StatusLine.HTTP11, postResponse.protocolVersion());
        assertEquals("Protocol version is 'HTTP/1.1' when PUT", StatusLine.HTTP11, putResponse.protocolVersion());
        assertEquals("Protocol version is 'HTTP/1.1' when DELETE", StatusLine.HTTP11, deleteResponse.protocolVersion());
    }

    @Test
    public void responseStatus() throws Exception {
        Response getResponse = new Form().response(getRequest);
        Response postResponse = new Form().response(postCosbyRequest);
        Response putResponse = new Form().response(putHeathcliffRequest);
        Response deleteResponse = new Form().response(deleteRequest);
        assertEquals("Status is '200 OK' when GET", StatusLine.OK, getResponse.status());
        assertEquals("Status is '200 OK' when POST", StatusLine.OK, postResponse.status());
        assertEquals("Status is '200 OK' when PUT", StatusLine.OK, putResponse.status());
        assertEquals("Status is '200 OK' when DELETE", StatusLine.OK, deleteResponse.status());
    }

    @Test
    public void responseBodyWhenGET() throws Exception {
        Resources.form_resource = cosby;

        Response response = new Form().response(getRequest);
        assertEquals("Body is whatever is stored in the form resource when GET", cosby, response.body());
    }

    @Test
    public void responseBodyWhenPostPutDelete() throws Exception {
        Response postResponse = new Form().response(postCosbyRequest);
        Response putResponse = new Form().response(putHeathcliffRequest);
        Response deleteResponse = new Form().response(deleteRequest);
        assertEquals("Body is empty when POST", null, postResponse.body());
        assertEquals("Body is empty when PUT", null, putResponse.body());
        assertEquals("Body is empty when DELETE", null, deleteResponse.body());
    }

    @Test
    public void updateResource() throws Exception {
        new Form().response(postCosbyRequest);
        assertEquals("Form resource is updated with request body when POST", cosby, Resources.form_resource);
        new Form().response(putHeathcliffRequest);
        assertEquals("Form resource is updated with request body when PUT", heathcliff, Resources.form_resource);
    }

    @Test
    public void deleteResource() throws Exception {
        Resources.form_resource = cosby;

        new Form().response(deleteRequest);
        assertEquals("Form resource is deleted", null, Resources.form_resource);
    }
}