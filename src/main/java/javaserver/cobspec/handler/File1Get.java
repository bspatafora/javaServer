package javaserver.cobspec.handler;

import javaserver.core.constants.Header;

public class File1Get extends FileGet {
    public File1Get() {
        setContentType(Header.TEXT_HTML);
        setFileName("file1");
    }
}
