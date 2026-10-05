//Instead of running for 10 seconds, let's make the experiment easier to measure.

//We'll create three threads, and each thread will increment the counter 1,000,000 times.

//Experiment 1 — No synchronization
public class ThreadMain {

    public static void main(String[] args) throws InterruptedException {

        CookingTask task1 = new CookingTask("Cooking");
        CookingTask task2 = new CookingTask("Washing");
        CookingTask task3 = new CookingTask("Cleaning");

        task1.start();
        task2.start();
        task3.start();

        // Wait for all threads to finish
        task1.join();
        task2.join();
        task3.join();

        System.out.println("Expected Count = " + (3 * 1_000_000));
        System.out.println("Actual Count   = " + CookingTask.staticCount);
    }
}


class CookingTask extends Thread {

    static int staticCount = 0;

    private String taskName;

    public CookingTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {

        for (int i = 0; i < 1_000_000; i++) {
            staticCount++;
        }

        System.out.println(taskName + " finished.");
    }
}
//You might expect:

//Expected Count = 3000000
//Actual Count   = 3000000
//But you may get something like:

//Expected Count = 3000000
//Actual Count   = 1847291
or:

//Expected Count = 3000000
//Actual Count   = 2478392
//The exact result can vary.

//That's the race condition.
