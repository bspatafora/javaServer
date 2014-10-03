package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.constants.Header;

public class TextFileTXT extends FileHandler implements Handler {
    public void setContentType() {
        contentType = Header.TEXT_HTML;
    }
    public void setFileName() {
        fileName = "text-file.txt";
    }

    protected void get() {
        super.get();
    }
}
