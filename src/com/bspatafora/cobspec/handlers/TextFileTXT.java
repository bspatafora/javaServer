package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.constants.Header;

public class TextFileTXT extends FileHandler {
    public TextFileTXT() {
        setContentType(Header.TEXT_HTML);
        setFileName("text-file.txt");
    }

    protected void get() {
        super.get();
    }
}
