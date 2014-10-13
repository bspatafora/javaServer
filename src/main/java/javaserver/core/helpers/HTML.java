package javaserver.core.helpers;

public class HTML {
    public static String link(String address, String name) {
        return "<a href=\"/" + address + "\">" + name + "</a>";
    }
}
