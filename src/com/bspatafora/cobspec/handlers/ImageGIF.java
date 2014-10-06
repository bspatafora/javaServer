package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.constants.Header;

public class ImageGIF extends FileHandler implements Handler {
    public ImageGIF() {
        setContentType(Header.IMAGE_GIF);
        setFileName("image.gif");
    }

    protected void get() {
        super.get();
    }
}
