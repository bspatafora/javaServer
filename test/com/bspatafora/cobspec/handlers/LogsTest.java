package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.constants.Status;
import com.bspatafora.cobspec.Resources;
import org.junit.Test;

import java.util.Base64;

import static org.junit.Assert.*;

public class LogsTest {

    @Test
    public void responseStatusUnauthorizedWhenNotAuthenticated() throws Exception {
        Request unauthorizedRequest = new Request();
        Response response = new Logs().response(unauthorizedRequest);
        assertEquals("Status is '401 Unauthorized' when not authenticated", Status.UNAUTHORIZED, response.status());
    }

    @Test
    public void responseBodyWhenNotAuthenticated() throws Exception {
        Request unauthorizedRequest = new Request();
        Response response = new Logs().response(unauthorizedRequest);
        assertEquals("Body is 'Authentication required' when not authenticated", "Authentication required", new String(response.body()));
    }

    @Test
    public void responseStatusOKWhenAuthenticated() throws Exception {
        Request authorizedRequest = new Request();
        authorizedRequest.setCredentials(new String(Base64.getEncoder().encode("admin:hunter2".getBytes())));
        Response response = new Logs().response(authorizedRequest);
        assertEquals("Status is '200 OK' when authenticated", Status.OK, response.status());
    }

    @Test
    public void responseBodyWhenAuthenticated() throws Exception {
        Resources.logs_resource.add("A logged request");
        Request authorizedRequest = new Request();
        authorizedRequest.setCredentials(new String(Base64.getEncoder().encode("admin:hunter2".getBytes())));
        Response response = new Logs().response(authorizedRequest);
        assertEquals("Body contains log resource when authenticated", Resources.logs_resource.toString(), new String(response.body()));
    }
}