package main.java.javaserver.cobspec.handlers;

import main.java.javaserver.core.constants.Header;

public class PatchContentTXT extends FileHandler {
    public PatchContentTXT() {
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
