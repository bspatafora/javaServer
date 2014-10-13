package javaserver.cobspec.handlers;

import javaserver.cobspec.Resources;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Method;
import javaserver.core.constants.Status;
import org.junit.Test;

import java.util.Base64;

import static org.junit.Assert.*;

public class LogsTest {
    private static final Request unauthorizedRequest = new Request();
    static {
        unauthorizedRequest.setMethod(Method.GET);
    }
    private static final Request authorizedRequest = new Request();
    static {
        authorizedRequest.setMethod(Method.GET);
        authorizedRequest.setCredentials(new String(Base64.getEncoder().encode("admin:hunter2".getBytes())));
    }

    @Test
    public void responseStatusUnauthorizedWhenNotAuthenticated() throws Exception {
        Response response = new Logs().response(unauthorizedRequest);
        assertEquals("Status is '401 Unauthorized' when not authenticated", Status.UNAUTHORIZED, response.status());
    }

    @Test
    public void responseBodyWhenNotAuthenticated() throws Exception {
        Response response = new Logs().response(unauthorizedRequest);
        assertEquals("Body is 'Authentication required' when not authenticated", "Authentication required", new String(response.body()));
    }

    @Test
    public void responseStatusOKWhenAuthenticated() throws Exception {
        Response response = new Logs().response(authorizedRequest);
        assertEquals("Status is '200 OK' when authenticated", Status.OK, response.status());
    }

    @Test
    public void responseBodyWhenAuthenticated() throws Exception {
        Resources.logsResource.add("A logged request");

        Response response = new Logs().response(authorizedRequest);
        assertEquals("Body contains log resource when authenticated", Resources.logsResource.toString(), new String(response.body()));
    }
}