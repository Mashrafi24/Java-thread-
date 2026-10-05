public class ThreadMain {

    public static void main(String[] args) {

        CookingTask task1 = new CookingTask("Cooking");
        CookingTask task2 = new CookingTask("Washing");
        CookingTask task3 = new CookingTask("Cleaning");

        task1.start();
        task2.start();
        task3.start();
    }
}


class CookingTask extends Thread {

    private String taskName;

    // ONE copy shared by ALL CookingTask objects
    static int staticCount = 0;

    // ONE copy for EACH CookingTask object
    int nonStaticCount = 0;


    public CookingTask(String taskName) {
        this.taskName = taskName;
    }


    @Override
    public void run() {

        long startTime = System.currentTimeMillis();

        for (;;) {

            // Increase both counters
            staticCount++;
            nonStaticCount++;

            System.out.println(
                    Thread.currentThread().getName()
                    + " | " + taskName
                    + " | Static Count = " + staticCount
                    + " | Non-Static Count = " + nonStaticCount
            );

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }

            // Stop after approximately 10 seconds
            if (System.currentTimeMillis() - startTime >= 10_000) {
                break;
            }
        }

        System.out.println(
                taskName + " finished. "
                + "Final Non-Static Count = " + nonStaticCount
        );
    }
}