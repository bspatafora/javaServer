package javaserver.cobspec.handler;

import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.Settings;
import javaserver.core.constants.Header;
import javaserver.core.constants.Status;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PatchContentTxtPatch implements Handler {
    private final Request request;
    private String filePath;

    public PatchContentTxtPatch(Request request) {
        this.request = request;
    }

    public Response response() {
        this.filePath = Settings.directory + "patch-content.txt";
        Response response = new Response();
        response.setStatus(Status.NO_CONTENT);
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        if (resourceInExpectedState()) {
            patchResource();
        }
        return response;
    }

    private Boolean resourceInExpectedState() {
        Boolean matchingSHA1s = false;
        try (FileInputStream resourceContents = new FileInputStream(filePath)) {
            String resourceSHA1 = DigestUtils.sha1Hex(resourceContents);
            matchingSHA1s = resourceSHA1.equals(requestSHA1());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return matchingSHA1s;
    }

    private void patchResource() {
        int updateStartByteIndex = 0;
        int updateEndByteIndex = request.contentLength();
        byte[] update = request.body().getBytes();

        try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
            file.write(update, updateStartByteIndex, updateEndByteIndex);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private String requestSHA1() {
        return request.getHeader(Header.IF_MATCH).substring(10);
    }
}
