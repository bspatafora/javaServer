package main.java.javaserver.core.helpers;

import main.java.javaserver.core.constants.Method;

import java.util.StringJoiner;

public class HTTP {
    public static String allowedMethods(Class klass) {
        StringJoiner allowedMethods = new StringJoiner(", ");
        for (java.lang.reflect.Method method : klass.getDeclaredMethods()) {
            String methodName = method.getName().toUpperCase();
            if (Method.METHODS.contains(methodName)) {
                allowedMethods.add(methodName);
            }
        }
        return allowedMethods.toString();
    }
}
