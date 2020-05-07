import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterDemo {

    public static AtomicInteger counter = new AtomicInteger(); // всегда 2000
    //static int counter; //значения от 1993 - до 2000
    //static  volatile int counter; // значения от 1997-2000

    public static void main(String[] args) {
        CountDownLatch doneSignal = new CountDownLatch(2000);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 2000; i++) {
            executor.execute(new IncrementorTask(doneSignal));
        }
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.getMessage();
        }
        System.out.println("Counter " + counter);
        executor.shutdown();
    }

    static class IncrementorTask implements Runnable {
        private final CountDownLatch doneSignal;

        public IncrementorTask(CountDownLatch doneSignal) {
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            counter.getAndIncrement();
            // counter++;
            doneSignal.countDown();
        }
    }
}
