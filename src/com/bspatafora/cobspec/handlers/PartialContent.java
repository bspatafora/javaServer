package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.constants.Header;

public class PartialContent extends FileHandler implements Handler {
    public PartialContent() {
        setContentType(Header.TEXT_HTML);
        setFileName("partial_content.txt");
    }

    protected void get() {
        super.get();
    }
}
