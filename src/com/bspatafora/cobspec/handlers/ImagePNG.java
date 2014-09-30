package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.constants.Header;

public class ImagePNG extends Image implements Handler {
    public void setContentType() {
        contentType = Header.IMAGE_PNG;
    }
    public void setFileName() {
        fileName = "image.png";
    }
}
