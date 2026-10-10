public class ThreadMain2 {

    public static void main(String[] args)
            throws InterruptedException {

        // Case 1: 1 minute
        runExperiment(1);

        // Case 2: 5 minutes
        runExperiment(5);

        // Case 3: 15 minutes
        runExperiment(15);
    }


    static void runExperiment(int durationMinutes)
            throws InterruptedException {

        // Reset shared counter before each experiment
        CookingTask.staticCount = 0;

        CookingTask task1 =
                new CookingTask("Cooking", durationMinutes);

        CookingTask task2 =
                new CookingTask("Washing", durationMinutes);

        CookingTask task3 =
                new CookingTask("Cleaning", durationMinutes);


        System.out.println(
                "\n===== " + durationMinutes
                + " Minute Experiment ====="
        );


        task1.start();
        task2.start();
        task3.start();


        // Wait for all three threads
        task1.join();
        task2.join();
        task3.join();


        // Expected count
        int expected =
                task1.nonStaticCount
                + task2.nonStaticCount
                + task3.nonStaticCount;


        // Actual shared count
        int actual =
                CookingTask.staticCount;


        // Error
        int error = expected - actual;


        // Error percentage
        double errorPercentage =
                ((double) error / expected) * 100;


        System.out.println(
                "Expected Count = " + expected
        );

        System.out.println(
                "Actual Count   = " + actual
        );

        System.out.println(
                "Error Count    = " + error
        );

        System.out.println(
                "Error %        = "
                + errorPercentage + "%"
        );
    }
}


class CookingTask extends Thread {

    private String taskName;


    // ONE shared copy
    static int staticCount = 0;


    // ONE copy for EACH object
    int nonStaticCount = 0;


    private int durationMinutes;


    public CookingTask(
            String taskName,
            int durationMinutes) {

        this.taskName = taskName;
        this.durationMinutes = durationMinutes;
    }


    @Override
    public void run() {

        long startTime =
                System.currentTimeMillis();


        for (;;) {

            // Increase both counters
            staticCount++;
            nonStaticCount++;


            try {

                // Wait for 1 second
                Thread.sleep(1000);

            } catch (InterruptedException e) {

                break;
            }


            // Check whether required time is over
            if (System.currentTimeMillis()
                    - startTime
                    >= durationMinutes * 60_000L) {

                break;
            }
        }


        System.out.println(
                taskName
                + " finished. "
                + "Non-static Count = "
                + nonStaticCount
        );
    }
}