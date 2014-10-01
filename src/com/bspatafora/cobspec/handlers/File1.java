package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.constants.Header;

public class File1 extends FileHandler implements Handler {
    public void setContentType() {
        contentType = Header.TEXT_HTML;
    }
    public void setFileName() {
        fileName = "file1";
    }
}
