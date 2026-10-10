# Lab_Thread_Counter — Static vs Non-Static Counter in Multithreading

**Name:** Md. Mashrafi Rahman | **ID:** IT-24034 | **Course:** SEPM-ICT-3107

## 1. Files

- `Mashrafi_Thread.java` – typed version (for running)
- `handwritten/` – photo/scan of handwritten code
- `outputs/` – screenshots of every run (+ raw `.txt`, `results.csv`)
- `run_all.ps1` – script that runs all test cases (5 unsynchronized runs per thread count)
- `show_cases.ps1` – prints the output of one test case (used for screenshots)

- Lab_Thread_Counter
│
├── Mashrafi_Thread.java
├── README.md
├── run_all.ps1
├── show_cases.ps1
│
├── handwritten/
│
└── outputs/
    ├── results.csv
    ├── T1_true_run1.txt
    ├── T1_false_run1.txt
    ├── ...
    └── T100_false_run5.txt

  # 3. How to Run
   Compile the Java Program
   Open PowerShell in the project directory and run:
   javac Mashrafi_Thread.java

   If there is no compilation error, the program has compiled successfully.

  ## Run Thread-Safe Mode
   The true argument selects the AtomicLong implementation:
    java Mashrafi_Thread 10 50000 true

  ## Run Unsynchronized Mode
  The false argument selects the normal long implementation:
  java Mashrafi_Thread 10 50000 false

## Command Format
java Mashrafi_Thread <threads> <increments-per-thread> <true|false>

Where:
threads                = Number of threads
increments-per-thread  = Number of increments performed by each thread
true                   = Thread-safe AtomicLong
false                  = Unsynchronized normal long

# 4.Test Inputs
The experiment contains seven test cases.
Test	Threads	Increments/thread	Expected Count
TC1	1	1,000	1,000
TC2	2	10,000	20,000
TC3	5	10,000	50,000
TC4	10	50,000	500,000
TC5	20	50,000	1,000,000
TC6	50	50,000	2,500,000
TC7	100	50,000	5,000,000


Expected Count Formula
Expected Count
=
Number of Threads × Increments per Thread

For example:
10 × 50,000 = 500,000

# 5. Running All Test Cases
The run_all.ps1 script automatically runs:
Safe Mode       → 1 run for each test case
Unsafe Mode     → 5 runs for each test case

Run the script using:
powershell -ExecutionPolicy Bypass -File .\run_all.ps1

A total of:
7 Safe runs
+
35 Unsafe runs
=
42 executions

will be performed.
The results will be saved in:
outputs/results.csv

# 6. Output Screenshots
Screenshots of the program outputs are stored in the outputs/
directory.
The unsynchronized results may be different between runs because
thread scheduling is not deterministic.

<img width="419" height="251" alt="WhatsApp Image 2026-10-11 at 3 13 33 AM" src="https://github.com/user-attachments/assets/b92d08e6-a34d-4b85-8683-a0d6fe9b69cf" />


TC1
1 thread, 1,000 increments/thread
Safe:
[Insert TC1 Safe Screenshot Here]

Unsafe:
[Insert TC1 Unsafe Screenshot Here]

TC2
<img width="381" height="880" alt="image" src="https://github.com/user-attachments/assets/76cea33e-7233-42ed-bb74-ea10f5f750ff" />

2 threads, 10,000 increments/thread
Safe:
[Insert TC2 Safe Screenshot Here]

Unsafe:
[Insert TC2 Unsafe Screenshot Here]

TC3

<img width="381" height="880" alt="image" src="https://github.com/user-attachments/assets/bf181eee-ef7d-496b-a6ad-d3b7dba619e9" />

5 threads, 10,000 increments/thread
Safe:
[Insert TC3 Safe Screenshot Here]

Unsafe:
[Insert TC3 Unsafe Screenshot Here]

TC4

<img width="416" height="730" alt="WhatsApp Image 2026-10-11 at 3 14 39 AM" src="https://github.com/user-attachments/assets/4da529d8-dd8a-4d8c-850e-d2c28dc4069b" />

10 threads, 50,000 increments/thread
Safe:
[Insert TC4 Safe Screenshot Here]

Unsafe:
[Insert TC4 Unsafe Screenshot Here]

TC5

<img width="443" height="713" alt="WhatsApp Image 2026-10-11 at 3 15 03 AM" src="https://github.com/user-attachments/assets/a6e9a0b9-e2fe-4f95-a105-a937b20f598d" />

20 threads, 50,000 increments/thread
Safe:
[Insert TC5 Safe Screenshot Here]

Unsafe:
[Insert TC5 Unsafe Screenshot Here]

TC6
<img width="443" height="713" alt="WhatsApp Image 2026-10-11 at 3 15 03 AM" src="https://github.com/user-attachments/assets/6e6e7699-5531-44b8-bd26-c67cec9aac78" />

50 threads, 50,000 increments/thread
Safe:
[Insert TC6 Safe Screenshot Here]

Unsafe:
[Insert TC6 Unsafe Screenshot Here]

TC7
<img width="410" height="451" alt="WhatsApp Image 2026-10-11 at 3 15 20 AM" src="https://github.com/user-attachments/assets/272a6e9c-c180-4d4c-b471-47bfb23964ee" />

100 threads, 50,000 increments/thread
Safe:
[Insert TC7 Safe Screenshot Here]

Unsafe:
[Insert TC7 Unsafe Screenshot Here]

# 7. Result Analysis
7.1 Thread-Safe Experiment — AtomicLong
The thread-safe mode uses:
safe.incrementAndGet();

The shared counter is an AtomicLong.
Expected result:
Expected Count = Static Count = Non-static Total
Difference = 0
Difference (%) = 0%

Threads	Increments/thread	Expected	Static	Non-static	Abs Diff	Diff %
1	1,000	1,000				
2	10,000	20,000				
5	10,000	50,000				
10	50,000	500,000				
20	50,000	1,000,000				
50	50,000	2,500,000				
100	50,000	5,000,000				


Fill the Static, Non-static, Abs Diff and Diff % columns using the
actual values from outputs/results.csv.

7.2 Unsynchronized Experiment
The unsynchronized mode uses:
unsafe++;

The operation is performed on a normal static long.




Each test case is run five times.

    Threads	Run	Expected	Static (Unsafe)	Non-static	Abs Diff	Diff %
    
    1	1	1,000				
    1	2	1,000				
    1	3	1,000				
    1	4	1,000				
    1	5	1,000				
    2	1	20,000				
    2	2	20,000				
    2	3	20,000				
    2	4	20,000				
    2	5	20,000				
    5	1	50,000				
    5	2	50,000				
    5	3	50,000				
    5	4	50,000				
    5	5	50,000				
    10	1	500,000				
    10	2	500,000				
    10	3	500,000				
    10	4	500,000				
    10	5	500,000				
    20	1	1,000,000				
    20	2	1,000,000				
    20	3	1,000,000				
    20	4	1,000,000				
    20	5	1,000,000				
    50	1	2,500,000				
    50	2	2,500,000				
    50	3	2,500,000				
    50	4	2,500,000				
    50	5	2,500,000				
    100	1	5,000,000				
    100	2	5,000,000				
    100	3	5,000,000				
    100	4	5,000,000				
    100	5	5,000,000				

    
    7.3 Average of Five Unsynchronized Runs
    After running all five unsynchronized trials, calculate the average.
    Threads	Average Static	Average Abs Diff	Average Diff %	Minimum %	Maximum %
    1					
    2					
    5					
    10					
    20					
    50					
    100					


    # 8. Calculation
    Absolute Difference
    The absolute difference is calculated as:
    Absolute Difference
    =
    |Static Count - Non-static Total|
    
    Example:
    Static Count       = 505,713
    Non-static Total   = 1,000,000
    
    Absolute Difference
    =
    |505,713 - 1,000,000|
    
    =
    494,287
    
    Percentage Difference
    Difference (%)
    =
    (Absolute Difference / Non-static Total) × 100
    
    For example:
    Absolute Difference = 494,287
    Non-static Total    = 1,000,000
    
    Difference (%)
    =
    494,287 / 1,000,000 × 100
    
    =
    49.4287%

# 9. Observation
Thread-Safe Experiment
The AtomicLong counter provides safe atomic updates when multiple
threads increment the same shared counter.
The expected count should match the static counter and the total of
the non-static counters.
Therefore, the difference should be:
0

and the percentage difference should be:
0%

Unsynchronized Experiment
The unsynchronized counter uses:
unsafe++;

This is a shared static variable accessed by multiple threads.
The operation is not atomic.
Therefore, multiple threads can interfere with each other's updates,
causing some increments to be lost.
10. Race Condition
The statement:
unsafe++;

looks like one operation, but conceptually it involves three steps:
1. Read the current value
2. Add 1
3. Write the new value

For example:
Initial value = 10

Thread 1 → Read 10
Thread 2 → Read 10

Thread 1 → Write 11
Thread 2 → Write 11

Both threads read the same value.
The expected result after two increments is:
12

but the actual result can become:
11

One increment has been lost.
This is called a race condition or lost update.
11. Static vs Non-Static
Static Variable
static long unsafe = 0;

A static variable belongs to the class.
There is one shared copy:
              static counter
                    |
        ┌───────────┼───────────┐
        ↓           ↓           ↓
     Thread 1    Thread 2    Thread 3

All threads access the same variable.
Non-Static Variable
long count = 0;

A non-static variable belongs to an object.
Each Counter object has its own copy:
Thread 1 → count
Thread 2 → count
Thread 3 → count

Therefore, each thread can safely maintain its own instance counter.
12. Why join() Is Used
The program uses:
x.join();

join() makes the main thread wait until the worker thread finishes.
Without join(), the main thread could calculate the final result before
all worker threads have completed their increments.
13. Why AtomicLong Is Used
The safe counter uses:
AtomicLong safe = new AtomicLong();

and:
safe.incrementAndGet();

AtomicLong provides an atomic increment operation, so concurrent
threads can safely update the shared counter without losing increments.
## 14. Analysis Questions
     1. What is the difference between static and non-static variables?
       A static variable belongs to the class and has one shared copy.
     A non-static variable belongs to an object, so every object has its own
     copy.
    2. Why can the static counter produce an incorrect value?
    Because the static counter is shared by multiple threads and the normal
    long increment operation is not atomic.
    3. Why is the non-static counter correct?
    Each thread has its own Counter object and therefore its own
    non-static count variable.
    4. Why is join() necessary?
    join() ensures that the main thread waits for all worker threads to
    finish before calculating the final result.
    5. What causes lost updates?
    Lost updates occur when multiple threads perform a read-modify-write
    operation on the same shared variable at the same time.
    6. Does increasing the number of threads always increase the
    percentage difference?
    No.
    The number of lost updates can increase, but the percentage difference
    does not necessarily increase monotonically.
    The result depends on thread scheduling, CPU resources, timing and
    runtime conditions.
    7. Why can the result change between runs?
    Thread scheduling is not deterministic.
    Different executions can produce different thread interleavings, causing
    different numbers of lost updates.
    8. Why does AtomicLong prevent lost updates?
    AtomicLong.incrementAndGet() performs the increment atomically, so
    competing threads do not overwrite each other's increments.
    15. Conclusion
    This experiment demonstrates that static vs non-static describes
    variable ownership, not thread safety.
    A static variable has one shared copy for the entire class, while a
    non-static variable has a separate copy for each object.
    The unsynchronized static counter uses:
    unsafe++;
    
    which is not atomic and can therefore produce lost updates when multiple
    threads access it concurrently.
    The thread-safe implementation uses:
    safe.incrementAndGet();

which provides atomic updates.
The experiment therefore demonstrates the importance of using proper
thread-safety mechanisms when multiple threads access shared data.
The exact unsynchronized result can vary between runs because thread
execution and scheduling are not deterministic.
16. Submission Checklist
- [ ] Mashrafi_Thread.java
- [ ] README.md
- [ ] Handwritten Java code photograph/scan
- [ ] Safe output for TC1–TC7
- [ ] Five unsafe runs for TC1–TC7
- [ ] outputs/results.csv
- [ ] Output screenshots
- [ ] Result analysis
- [ ] Average of five unsafe runs
- [ ] Observation
- [ ] Conclusion
- [ ] Analysis question answers
Student Information
Name: Md. Mashrafi Rahman
ID: IT-24034
Course: SEPM-ICT-3107
