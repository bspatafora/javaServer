package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.constants.Header;

public class PartialContentTXT extends FileHandler {
    public PartialContentTXT() {
        setContentType(Header.TEXT_HTML);
        setFileName("partial_content.txt");
    }

    protected void get() {
        super.get();
    }
}
