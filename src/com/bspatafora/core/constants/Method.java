package com.bspatafora.core.constants;

import java.util.ArrayList;
import java.util.List;

public class Method {
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";
    public static final String OPTIONS = "OPTIONS";
    public static final String HEAD = "HEAD";

    public static final List<String> METHODS;
    static {
        METHODS = new ArrayList<>();
        METHODS.add(GET);
        METHODS.add(POST);
        METHODS.add(PUT);
        METHODS.add(DELETE);
        METHODS.add(OPTIONS);
        METHODS.add(HEAD);
    }
}
