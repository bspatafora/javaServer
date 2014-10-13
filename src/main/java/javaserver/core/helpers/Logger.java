package javaserver.core.helpers;

public interface Logger {
    void requestReceived(String timeStamp);
    void request(String request);
    void responseTime(double elapsedTime);
}
