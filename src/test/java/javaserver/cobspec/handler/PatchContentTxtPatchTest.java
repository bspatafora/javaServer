package javaserver.cobspec.handler;

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

public class PatchContentTxtPatchTest {
    private static final String defaultSHA1 = "60bb224c68b1ed765a0f84d910de58d0beea91c4";
    private static final String patchedSHA1 = "69bc18dc1edc9e1316348b2eaaca9df83898249f";

    private static final Request patchRequest = new Request();
    static {
        patchRequest.setMethod(Method.PATCH);
        patchRequest.setBody("default content");
        patchRequest.addHeader(Header.IF_MATCH + defaultSHA1);
        patchRequest.setContentLength(7);
    }

    @Test
    public void responseStatusWhenPATCH() throws Exception {
        Response response = new PatchContentTxtPatch(patchRequest).response();
        assertEquals("Status is '204 No Content'", Status.NO_CONTENT, response.status());
    }

    @Test
    public void patchContentResourceWhenSHA1Match() throws Exception {
        patchResource();
        assertEquals("patch-content.txt content is updated when request and actual SHA1s match", "patched content\n", resourceContents());
        revertResource();
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

        new PatchContentTxtPatch(request).response();
    }
}