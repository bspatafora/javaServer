package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.constants.Header;

public class ImageJPEG extends FileHandler implements Handler {
    public void setContentType() {
        contentType = Header.IMAGE_JPEG;
    }
    public void setFileName() {
        fileName = "image.jpeg";
    }
}
