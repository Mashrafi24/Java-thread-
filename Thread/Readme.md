# 05-10-2026
# Java Thread Laboratory

A practical Java multithreading laboratory covering the most important
Thread concepts through small experiments and examples.

---

## What is a Thread?

A **Thread** is an independent path of execution within a program.

A single Java program can have multiple threads running concurrently.
This allows different tasks to make progress at the same time.

For example:

```text
Main Program
     |
     +---- Thread 1 → Cooking
     |
     +---- Thread 2 → Washing
     |
     +---- Thread 3 → Cleaning

In this laboratory, CookingTask is used as a simple example to
understand Java multithreading.
Thread Creation
There are two common ways to create a thread in Java:
1. Extending the Thread class
class CookingTask extends Thread {

    @Override
    public void run() {
        System.out.println("Cooking...");
    }
}

Then:
CookingTask task = new CookingTask();

task.start();

Here, CookingTask itself is a Thread.
2. Implementing Runnable
class CookingTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Cooking...");
    }
}

Then:
CookingTask task = new CookingTask();

Thread thread = new Thread(task);

thread.start();

Here, CookingTask is a Runnable task, and a Thread object executes it.
Important Thread Concepts
This laboratory contains the following experiments:
Experiment	Concept	Purpose
1	start() vs run()	New thread vs normal method call
2	sleep()	Temporarily pauses a thread
3	join()	Waits for another thread
4	getState()	Checks thread lifecycle state
5	getName() / setName()	Identifies threads
6	isAlive()	Checks whether a thread is running
7	interrupt()	Requests interruption of a thread
8	Thread Priority	Provides scheduling priority hint
9	Race Condition	Demonstrates shared-data problems
10	synchronized	Protects shared data
11	AtomicInteger	Provides atomic counter operations
12	volatile	Provides visibility between threads
13	Daemon Thread	Demonstrates background thread behavior
14	synchronized Block	Provides fine-grained locking
15	wait() / notify()	Demonstrates thread coordination


Experiment 1: start() vs run()
start()
start() creates a new thread of execution and causes the JVM to
invoke the run() method.
task.start();

Concept:
main thread
     |
   start()
     |
New Thread
     |
   run()

run()
Calling run() directly does not create a new thread.
task.run();

It behaves like a normal method call.
Key Difference
start()
→ Creates a new thread

run()
→ Normal method call

Experiment 2: sleep()
sleep() temporarily pauses the currently executing thread.
Thread.sleep(1000);

1000 milliseconds = 1 second.
Example:
for(int i = 1; i <= 5; i++) {

    System.out.println(i);

    Thread.sleep(1000);
}

The thread pauses for approximately one second between iterations.
Important
sleep() does not terminate the thread.
RUNNING
   ↓
sleep()
   ↓
TIMED_WAITING
   ↓
RUNNING

Experiment 3: join()
join() makes one thread wait until another thread finishes.
task1.start();

task1.join();

System.out.println("Task completed");

Concept:
task1 starts
     ↓
task1 works
     ↓
task1 finishes
     ↓
main continues

This is useful when the main thread needs to wait for worker threads
before continuing.
Experiment 4: getState()
getState() returns the current state of a thread.
System.out.println(task.getState());

Important thread states include:
NEW
RUNNABLE
BLOCKED
WAITING
TIMED_WAITING
TERMINATED

Example:
Before start → NEW
Running      → RUNNABLE
After finish → TERMINATED

Experiment 5: getName() / setName()
Threads can be given meaningful names.
task.setName("Cooking Thread");

System.out.println(task.getName());

Output:
Cooking Thread

The name is useful when debugging multithreaded programs.
Experiment 6: isAlive()
isAlive() checks whether a thread has started and has not yet finished.
System.out.println(task.isAlive());

Example:
task.start();

System.out.println(task.isAlive());

task.join();

System.out.println(task.isAlive());

Possible output:
true
false

Experiment 7: interrupt()
interrupt() sends an interruption request to a thread.
Example:
task.interrupt();

If the thread is sleeping:
Thread.sleep(10000);

the sleep may be interrupted and an InterruptedException can occur.
Example:
try {

    Thread.sleep(10000);

} catch (InterruptedException e) {

    System.out.println("Thread interrupted!");
}

Important
interrupt() does not automatically forcefully kill a thread.
It is an interruption request.
Experiment 8: Thread Priority
Thread priority provides a scheduling hint to the JVM/thread scheduler.
Priority range:
1 → 10

Example:
task1.setPriority(Thread.MAX_PRIORITY);
task2.setPriority(Thread.MIN_PRIORITY);

Java provides:
Thread.MIN_PRIORITY
Thread.NORM_PRIORITY
Thread.MAX_PRIORITY

Important
Priority does not guarantee which thread will execute first.
Experiment 9: Race Condition
A race condition can occur when multiple threads access and modify
shared data without proper synchronization.
Example:
static int count = 0;

Three threads may execute:
count++;

at the same time.
Although count++ looks like one operation, conceptually it involves:
1. READ count
2. ADD 1
3. WRITE count

Two threads may read the same old value before either writes the
new value.
Example:
count = 10

Thread 1 → READ 10
Thread 2 → READ 10

Thread 1 → WRITE 11
Thread 2 → WRITE 11

Expected:
12

Actual:
11

This is a lost update caused by a race condition.
Experiment 10: synchronized
synchronized is used to protect shared data from concurrent access.
Example:
static int count = 0;

static synchronized void incrementCount() {

    count++;
}

Only one thread at a time can execute the synchronized method for
the relevant lock.
Concept:
Thread 1 → LOCK → increment → UNLOCK
Thread 2 → WAIT
Thread 3 → WAIT

Thread 2 → LOCK → increment → UNLOCK

This prevents the increment operation from being performed by
multiple threads simultaneously.
Experiment 11: AtomicInteger
AtomicInteger provides atomic operations on an integer.
Import:
import java.util.concurrent.atomic.AtomicInteger;

Example:
AtomicInteger count = new AtomicInteger(0);

count.incrementAndGet();

It is useful for thread-safe counters without explicitly writing
a synchronized method.
Example:
static AtomicInteger count =
        new AtomicInteger(0);

count.incrementAndGet();

Experiment 12: volatile
volatile is mainly used to provide visibility of a variable's
latest value between threads.
Example:
volatile boolean running = true;

One thread can change:
running = false;

and other threads can observe the updated value.
Important
volatile does not make compound operations such as:
count++;

atomic.
Therefore:
volatile int count;

does not by itself solve a race condition caused by count++.
Experiment 13: Daemon Thread
A daemon thread is a background/supporting thread.
Example:
Thread task = new Thread(() -> {

    while(true) {
        System.out.println("Background task");
    }

});

task.setDaemon(true);

task.start();

When all user threads finish, the JVM does not wait for daemon
threads to finish.
Concept:
User Threads
     ↓
Finish
     ↓
JVM exits

Daemon Thread
     ↓
Does not keep JVM alive

Experiment 14: synchronized Block
A synchronized block allows only a specific section of code to be
protected.
Example:
synchronized(Counter.class) {

    count++;
}

Instead of locking the entire method, only the critical section
is protected.
This can provide more fine-grained control over synchronization.
Experiment 15: wait() / notify()
wait() and notify() are used for communication and coordination
between threads.
Example:
synchronized void waitForData()
        throws InterruptedException {

    wait();
}

Another thread can notify it:
synchronized void sendData() {

    notify();
}

Concept:
Thread A
   |
 wait()
   |
 WAITING
   |
   ↑
notify()
   |
Thread B

These methods are commonly used in producer-consumer style problems.
Static vs Non-Static in Multithreading
A static variable has one shared copy for the class.
static int staticCount = 0;

All CookingTask objects share it.
             staticCount
                  |
        ┌─────────┼─────────┐
        ↓         ↓         ↓
      task1     task2     task3

A non-static variable has a separate copy for each object.
int nonStaticCount = 0;

task1 → nonStaticCount
task2 → nonStaticCount
task3 → nonStaticCount

Therefore:
static
→ shared

non-static
→ separate for each object

CookingTask Example
class CookingTask extends Thread {

    private String taskName;

    static int staticCount = 0;

    int nonStaticCount = 0;

    public CookingTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {

        staticCount++;
        nonStaticCount++;

        System.out.println(
                Thread.currentThread().getName()
                + " | "
                + taskName
                + " | Static Count = "
                + staticCount
                + " | Non-Static Count = "
                + nonStaticCount
        );
    }
}

Create three threads:
CookingTask task1 =
        new CookingTask("Cooking");

CookingTask task2 =
        new CookingTask("Washing");

CookingTask task3 =
        new CookingTask("Cleaning");

task1.start();
task2.start();
task3.start();

Thread Laboratory Experiment
For the race-condition experiment, three threads can increment a
shared counter for different durations.
Case 1
1 minute

Case 2
5 minutes

Case 3
15 minutes

The expected and actual values can then be compared.
Error
Error = Expected Count - Actual Count

Error Percentage
Error % =
(Expected Count - Actual Count)
------------------------------- × 100
       Expected Count

Example:
Expected = 1000
Actual   = 990

Error = 10

Error % = 10 / 1000 × 100
        = 1%

The exact result can vary because thread scheduling is not
deterministic.
Learning Summary
Thread
  |
  +-- start() / run()
  |
  +-- sleep()
  |
  +-- join()
  |
  +-- getState()
  |
  +-- getName() / setName()
  |
  +-- isAlive()
  |
  +-- interrupt()
  |
  +-- Priority
  |
  +-- Race Condition
  |
  +-- synchronized
  |
  +-- AtomicInteger
  |
  +-- volatile
  |
  +-- Daemon Thread
  |
  +-- synchronized Block
  |
  +-- wait() / notify()

Key Takeaways
- A Thread is an independent path of execution.
- start() creates a new thread; calling run() directly does not.
- sleep() pauses a thread temporarily.
- join() makes one thread wait for another.
- getState() shows the thread's current state.
- isAlive() checks whether a thread is still running.
- interrupt() requests interruption.
- Thread priority is only a scheduling hint.
- Shared mutable data can cause a race condition.
- synchronized can protect critical sections.
- AtomicInteger provides atomic counter operations.
- volatile provides visibility but does not make count++ atomic.
- A daemon thread runs as a background/support thread.
- wait() and notify() are used for thread coordination.
How to Run
Compile:
javac ThreadMain.java

Run:
java ThreadMain

For each experiment, keep the corresponding .java file in the
appropriate experiment folder.

### GitHub folder structure

এটার সাথে আমি এই structure রাখতাম:

```text
java-thread/
│
├── README.md
│
├── 01-start-vs-run/
├── 02-sleep/
├── 03-join/
├── 04-thread-state/
├── 05-thread-name/
├── 06-is-alive/
├── 07-interrupt/
├── 08-priority/
├── 09-race-condition/
├── 10-synchronized/
├── 11-atomic-integer/
├── 12-volatile/
├── 13-daemon-thread/
├── 14-synchronized-block/
└── 15-wait-notify/
