package javaserver.cobspec.handler;

import javaserver.core.Handler;
import javaserver.core.Response;

public class Ok implements Handler {
    public Response response() {
        return new Response();
    }
}
