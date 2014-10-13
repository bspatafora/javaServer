package javaserver.cobspec.handlers;

import javaserver.cobspec.Resources;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Header;
import javaserver.core.constants.Method;
import javaserver.core.constants.Status;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FormTest {
    private static final Request getRequest = new Request();
    private static final Request postCosbyRequest = new Request();
    private static final Request putHeathcliffRequest = new Request();
    private static final Request deleteRequest = new Request();
    private static final String cosby = "cosby";
    private static final String heathcliff = "heathcliff";

    @BeforeClass
    public static void createRequests() {
        getRequest.setMethod(Method.GET);
        postCosbyRequest.setMethod(Method.POST);
        putHeathcliffRequest.setMethod(Method.PUT);
        deleteRequest.setMethod(Method.DELETE);

        postCosbyRequest.setBody(cosby);
        putHeathcliffRequest.setBody(heathcliff);
    }

    @Test
    public void responseStatus() throws Exception {
        Response getResponse = new Form().response(getRequest);
        Response postResponse = new Form().response(postCosbyRequest);
        Response putResponse = new Form().response(putHeathcliffRequest);
        Response deleteResponse = new Form().response(deleteRequest);
        assertEquals("Status is '200 OK' when GET", Status.OK, getResponse.status());
        assertEquals("Status is '200 OK' when POST", Status.OK, postResponse.status());
        assertEquals("Status is '200 OK' when PUT", Status.OK, putResponse.status());
        assertEquals("Status is '200 OK' when DELETE", Status.OK, deleteResponse.status());
    }

    @Test
    public void responseContentTypeHeaderWhenGET() throws Exception {
        Response response = new Form().response(getRequest);
        assertTrue("Content type header is set to 'text/html' when GET", response.headers().contains(Header.CONTENT_TYPE + Header.TEXT_HTML));
    }

    @Test
    public void responseBodyWhenGET() throws Exception {
        Resources.formResource = cosby;

        Response response = new Form().response(getRequest);
        assertEquals("Body is whatever is stored in the form resource when GET", cosby, new String(response.body()));
    }

    @Test
    public void updateResource() throws Exception {
        new Form().response(postCosbyRequest);
        assertEquals("Form resource is updated with request body when POST", cosby, Resources.formResource);
        new Form().response(putHeathcliffRequest);
        assertEquals("Form resource is updated with request body when PUT", heathcliff, Resources.formResource);
    }

    @Test
    public void deleteResource() throws Exception {
        Resources.formResource = cosby;

        new Form().response(deleteRequest);
        assertEquals("Form resource is deleted", "", Resources.formResource);
    }
}