public class ConnectionsManager {
    private static ConnectionsManager instance;
    private static final Object lock = new Object();


    private ConnectionsManager() {
    }

    public static ConnectionsManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ConnectionsManager();
                }
                try {

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.getMessage();
                }

            }
        }
        return instance;
    }
}
