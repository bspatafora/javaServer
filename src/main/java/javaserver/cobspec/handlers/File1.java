package javaserver.cobspec.handlers;

import javaserver.core.constants.Header;

public class File1 extends FileHandler {
    public File1() {
        setContentType(Header.TEXT_HTML);
        setFileName("file1");
    }

    protected void get() {
        super.get();
    }
}
