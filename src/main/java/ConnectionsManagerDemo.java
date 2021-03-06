import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionsManagerDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch startSignal = new CountDownLatch(1);
        ExecutorService executor = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 200000; i++) {
            executor.execute(new WorkerRunnable(startSignal));
        }
        startSignal.countDown();
        executor.shutdown();
    }
}

class WorkerRunnable implements Runnable {
    private final CountDownLatch startSignal;

    public WorkerRunnable(CountDownLatch startSignal) {
        this.startSignal = startSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            ConnectionsManager.getInstance();
            //System.out.println(ConnectionsManager.getInstance().hashCode());
        } catch (InterruptedException e) {
            e.getMessage();
        }

    }
}