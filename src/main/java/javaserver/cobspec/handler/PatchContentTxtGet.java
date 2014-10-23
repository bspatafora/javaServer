package javaserver.cobspec.handler;

import javaserver.core.constants.Header;

public class PatchContentTxtGet extends FileGet {
    public PatchContentTxtGet() {
        setContentType(Header.TEXT_HTML);
        setFileName("patch-content.txt");
    }
}
