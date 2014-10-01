package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.constants.Header;

public class ImageGIF extends FileHandler implements Handler {
    public void setContentType() {
        contentType = Header.IMAGE_GIF;
    }
    public void setFileName() {
        fileName = "image.gif";
    }

    protected void get() {
        super.get();
    }
}
