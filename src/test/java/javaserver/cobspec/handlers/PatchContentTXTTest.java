package javaserver.cobspec.handlers;

import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.Settings;
import javaserver.core.constants.Header;
import javaserver.core.constants.Method;
import javaserver.core.constants.Status;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PatchContentTXTTest {
    private static final String defaultSHA1 = "60bb224c68b1ed765a0f84d910de58d0beea91c4";
    private static final String patchedSHA1 = "69bc18dc1edc9e1316348b2eaaca9df83898249f";

    private static final Request getRequest = new Request();
    private static final Request patchRequest = new Request();
    private static final Request notAllowedRequest = new Request();
    static {
        getRequest.setMethod(Method.GET);
        patchRequest.setMethod(Method.PATCH);
        notAllowedRequest.setMethod(Method.PUT);

        patchRequest.setBody("default content");
        patchRequest.addHeader(Header.IF_MATCH + defaultSHA1);
        patchRequest.setContentLength(7);
    }

    @Test
    public void responseStatusWhenGET() throws Exception {
        Response response = new PatchContentTXT(getRequest).response();
        assertEquals("Status is '200 OK'", Status.OK, response.status());
    }

    @Test
    public void responseContentTypeHeaderWhenGET() throws Exception {
        Response response = new PatchContentTXT(getRequest).response();
        assertTrue("Content type header is set to 'text/html'", response.headers().contains(Header.CONTENT_TYPE + Header.TEXT_HTML));
    }

    @Test
    public void responseBodyWhenGET() throws Exception {
        Response response = new PatchContentTXT(getRequest).response();
        assertEquals("Body is CobSpec’s patch-content.txt contents", resourceContents(), new String(response.body()));
    }

    @Test
    public void responseStatusWhenPATCH() throws Exception {
        Response response = new PatchContentTXT(patchRequest).response();
        assertEquals("Status is '204 No Content'", Status.NO_CONTENT, response.status());
    }

    @Test
    public void patchContentResourceWhenSHA1Match() throws Exception {
        patchResource();
        assertEquals("patch-content.txt content is updated when request and actual SHA1s match", "patched content\n", resourceContents());
        revertResource();
    }

    @Test
    public void responseStatusWhenNotAllowed() throws Exception {
        Response response = new PatchContentTXT(notAllowedRequest).response();
        assertEquals("Status is '405 Method Not Allowed'", Status.NOT_ALLOWED, response.status());
    }

    @Test
    public void responseAllowedHeaderWhenNotAllowed() throws Exception {
        Response response = new PatchContentTXT(notAllowedRequest).response();
        assertTrue("Allowed header is set and indicates GET", response.headers().contains(Header.ALLOW + "GET"));
    }

    private String resourceContents() throws IOException {
        File file = new File(Settings.directory + "patch-content.txt");
        return new String(Files.readAllBytes(file.toPath()));
    }

    private void patchResource() {
        updateResource(defaultSHA1, "patched content");
    }

    private void revertResource() {
        updateResource(patchedSHA1, "default content");
    }

    private void updateResource(String sha1, String content) {
        Request request = new Request();
        request.setMethod(Method.PATCH);
        request.addHeader(Header.IF_MATCH + sha1);
        request.setContentLength(7);
        request.setBody(content);

        new PatchContentTXT(request).response();
    }
}