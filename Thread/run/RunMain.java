public class RunMain {
    public static void main(String[] args) {

        CookingTask task1 = new CookingTask("Cooking");
        CookingTask task2 = new CookingTask("Washing");
        CookingTask task3 = new CookingTask("Cleaning");

        task1.run(); // This will run in the main thread
        task2.run(); // This will run in the main thread
        task3.run(); // This will run in the main thread

        System.out.println("All tasks run.");
    }
}

class CookingTask extends Thread {

    private String taskName;

    public CookingTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {

        long startTime = System.currentTimeMillis();

        while (true) {

            System.out.println(
                    Thread.currentThread().getName()
                    + " - Running: " + taskName
            );

            // Wait 1 second before next iteration
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(taskName + " interrupted.");
                break;
            }

            // Stop after 10 seconds
            if (System.currentTimeMillis() - startTime >= 10_000) {
                break;
            }
        }

        System.out.println(taskName + " finished.");
    }
}