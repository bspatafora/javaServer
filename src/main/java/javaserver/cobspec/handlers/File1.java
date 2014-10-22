package javaserver.cobspec.handlers;

import javaserver.core.Request;
import javaserver.core.constants.Header;

public class File1 extends FileHandler {
    public File1(Request request) {
        super(request);
        setContentType(Header.TEXT_HTML);
        setFileName("file1");
    }

    protected void get() {
        super.get();
    }
}
