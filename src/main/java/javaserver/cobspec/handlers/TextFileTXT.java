package javaserver.cobspec.handlers;

import javaserver.core.Request;
import javaserver.core.constants.Header;

public class TextFileTXT extends FileHandler {
    public TextFileTXT(Request request) {
        super(request);
        setContentType(Header.TEXT_HTML);
        setFileName("text-file.txt");
    }

    protected void get() {
        super.get();
    }
}
