package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.constants.Header;

public class File1 extends FileHandler implements Handler {
    public File1() {
        setContentType(Header.TEXT_HTML);
        setFileName("file1");
    }

    protected void get() {
        super.get();
    }
}
