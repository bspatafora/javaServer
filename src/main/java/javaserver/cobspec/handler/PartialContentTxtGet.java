package javaserver.cobspec.handler;

import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.Settings;
import javaserver.core.constants.Header;
import javaserver.core.constants.Status;
import javaserver.core.helpers.FileSystem;

import java.io.File;

public class PartialContentTxtGet implements Handler {
    private final Request request;

    public PartialContentTxtGet(Request request) {
        this.request = request;
    }

    public Response response() {
        File file = new File(Settings.directory + "partial_content.txt");
        Response response = new Response();
        response.setStatus(Status.PARTIAL_CONTENT);
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        response.setBody(FileSystem.readPartial(file, startByteIndex(), endByteIndex()));
        return response;
    }

    private int startByteIndex() {
        return rangeHeaderCharacterAt(13);
    }

    private int endByteIndex() {
        return rangeHeaderCharacterAt(15);
    }

    private int rangeHeaderCharacterAt(int index) {
        String rangeHeader = request.getHeader(Header.RANGE);
        return Character.getNumericValue(rangeHeader.charAt(index));
    }
}
