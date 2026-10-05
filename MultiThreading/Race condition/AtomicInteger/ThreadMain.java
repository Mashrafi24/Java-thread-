import java.util.concurrent.atomic.AtomicInteger;

public class ThreadMain {

    public static void main(String[] args) throws InterruptedException {

        CookingTask task1 = new CookingTask("Cooking");
        CookingTask task2 = new CookingTask("Washing");
        CookingTask task3 = new CookingTask("Cleaning");

        task1.start();
        task2.start();
        task3.start();

        task1.join();
        task2.join();
        task3.join();

        System.out.println("Expected Count = " + (3 * 1_000_000));
        System.out.println("Actual Count   = " + CookingTask.staticCount.get());
    }
}


class CookingTask extends Thread {

    static AtomicInteger staticCount = new AtomicInteger(0);

    private String taskName;

    public CookingTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {

        for (int i = 0; i < 1_000_000; i++) {
            staticCount.incrementAndGet();
        }

        System.out.println(taskName + " finished.");
    }
}