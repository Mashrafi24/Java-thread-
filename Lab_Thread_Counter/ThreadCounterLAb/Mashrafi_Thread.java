import java.util.concurrent.atomic.AtomicLong;

public class Mashrafi_Thread implements Runnable {

    // Static counter (shared by ALL objects/threads)
    static AtomicLong safeStaticCount = new AtomicLong(0);   // Experiment A
    static long unsafeStaticCount = 0;                       // Experiment B

    // Non-static counter (each object has its own copy)
    long instanceCount = 0;

    final long increments;
    final boolean threadSafe;

    Mashrafi_Thread(long increments, boolean threadSafe) {
        this.increments = increments;
        this.threadSafe = threadSafe;
    }

    @Override
    public void run() {
        for (long i = 0; i < increments; i++) {
            if (threadSafe) safeStaticCount.incrementAndGet();
            else unsafeStaticCount++;          // race condition here
            instanceCount++;                   // only this thread touches it
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            System.out.println("Usage: java Mashrafi_Thread <threads> <increments> <true|false>");
            return;
        }
        int n = Integer.parseInt(args[0]);
        long m = Long.parseLong(args[1]);
        boolean safe = Boolean.parseBoolean(args[2]);

        Mashrafi_Thread[] tasks = new Mashrafi_Thread[n];
        Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            tasks[i] = new Mashrafi_Thread(m, safe);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }
        for (int i = 0; i < n; i++) threads[i].join();   // wait for all

        long staticCount = safe ? safeStaticCount.get() : unsafeStaticCount;
        long nonStaticTotal = 0;
        for (Mashrafi_Thread t : tasks) nonStaticTotal += t.instanceCount;

        long expected = n * m;
        long diff = Math.abs(staticCount - nonStaticTotal);
        double pct = (nonStaticTotal == 0)
                ? (staticCount == 0 ? 0.0 : Double.NaN)
                : (diff * 100.0) / nonStaticTotal;

        System.out.println("Mode            : " + (safe ? "Thread-safe (AtomicLong)" : "Unsynchronized (long)"));
        System.out.println("Threads         : " + n);
        System.out.println("Increments/thr  : " + m);
        System.out.println("Expected count  : " + expected);
        System.out.println("Static count    : " + staticCount);
        System.out.println("Non-static total: " + nonStaticTotal);
        System.out.println("Absolute diff   : " + diff);
        System.out.println("Difference (%)  : " + (Double.isNaN(pct) ? "undefined" : String.format("%.4f", pct)));
    }
}