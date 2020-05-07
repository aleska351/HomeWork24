public class ConnectionsManager {
    private static volatile ConnectionsManager instance;

    private ConnectionsManager() {
    }

    public static ConnectionsManager getInstance() {
        if (instance == null) {
            instance = new ConnectionsManager();
        }
        return instance;
    }
}

