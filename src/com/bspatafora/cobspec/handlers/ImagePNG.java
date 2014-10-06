package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.constants.Header;

public class ImagePNG extends FileHandler implements Handler {
    public ImagePNG() {
        setContentType(Header.IMAGE_PNG);
        setFileName("image.png");
    }

    protected void get() {
        super.get();
    }
}
