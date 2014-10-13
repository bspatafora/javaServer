package javaserver.cobspec;

public class RouterFactory implements javaserver.core.RouterFactory {
    public Router buildRouter() {
        return new Router();
    }
}
