package javaserver.cobspec.handlers;

import javaserver.core.Request;
import javaserver.core.constants.Header;

public class PatchContentTXT extends FileHandler {
    public PatchContentTXT(Request request) {
        super(request);
        setContentType(Header.TEXT_HTML);
        setFileName("patch-content.txt");
    }

    protected void get() {
        super.get();
    }

    protected void patch() {
        super.patch();
    }
}
