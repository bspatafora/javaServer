package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.constants.Header;

public class ImageJPEG extends FileHandler implements Handler {
    public ImageJPEG() {
        setContentType(Header.IMAGE_JPEG);
        setFileName("image.jpeg");
    }

    protected void get() {
        super.get();
    }
}
