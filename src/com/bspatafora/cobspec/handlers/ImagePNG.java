package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.Settings;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Status;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImagePNG extends Image implements Handler {
    public void setContentType() {
        contentType = Header.IMAGE_PNG;
    }
    public void setFileName() {
        fileName = "image.png";
    }
}
