
# Static vs Non-Static in Java
 (02-10-2026)

## 1. Introduction

In Java, variables and methods can be static or non-static.

The main difference is that a static member belongs to the class,
while a non-static member belongs to an object.

## 2. Static Variable

A static variable is associated with the class.

Only one shared copy of a static variable exists for the class,
and all objects can access the same variable.

### Syntax

```java
static int count = 0;
Example
class Student {

    static String university = "MBSTU";

}

All Student objects share the same university value.
3. Non-Static Variable
A non-static variable is associated with an object.
Each object has its own separate copy.
Example
class Student {

    String name;

}

4. Static vs Non-Static
   Static                   	Non-Static
Belongs to class	     Belongs to object
Shared among objects	 Separate for each object
One copy	              One copy per object

Can be accessed
using class name         Usually accessed through object

Useful for common
 data	                Useful for object-specific data



5. Object Counting
If we want to count how many objects have been created,
the counter should normally be static.
static int count = 0;

Every time an object is created:
count++;

The shared counter increases.
6. Important Note
static does NOT mean that the value cannot change.
For example:
static int count = 0;

count++;

The value can become:
0 → 1 → 2 → 3

static means there is one shared copy.



#start() & run()


##star()

task1.start();
task2.start();
task3.start();


task1.start();
তখন Java:
1. নতুন একটা thread তৈরি করে
2. সেই thread-কে CPU execution-এর জন্য প্রস্তুত করে
3. তারপর automatically run() method call করে
task1.start()
       |
       ↓
New Thread created
       |
       ↓
run()
       |
       ↓
Task execution

        Main Thread

            |
    -----------------
    |       |       |
    ↓       ↓       ↓

 task1    task2    task3

 Thread   Thread   Thread



কোন thread আগে CPU পাবে সেটা Thread Scheduler ঠিক করে।


Burger is cooking
Pizza is cooking
Pasta is cooking

Pasta is cooking
Burger is cooking
Pizza is cooking

##run()
task1.run();
task2.run();
task3.run();

❌ নতুন thread তৈরি হবে না।
এটা normal method call-এর মতো চলবে।

Main Thread

   |
   ↓

task1.run()
   |
   ↓
finish

task2.run()
   |
   ↓
finish

task3.run()



      start()                 run() 

 নতুন Thread তৈরি করে    | নতুন Thread তৈরি করে না 
 Multithreading হয়     | Normal method call 
 JVM run() call করে    | তুমি manually call করো 
 Concurrent execution  | Sequential execution

hat does start() do in Java Thread?
Answer:
The start() method creates a new thread of execution and internally invokes the run() method.


Why don't we call run() directly?
Answer:
Calling run() directly does not create a new thread; it executes like a normal method in the current thread.


# Java Multithreading

## Thread Concept

## Creating Thread

## start() vs run()

## Static vs Non-static in Thread

## Race Condition

## Synchronization

## AtomicInteger

## Practice Programs





extends Thread
        ↓
create 3 objects
        ↓
start()
        ↓
multiple threads run
        ↓
static = shared data
        ↓
non-static = separate data
        ↓
shared data creates race condition
        ↓
solve using synchronized / AtomicInteger



Now see the concept of race condition, how to solve using synchornised and AtomicInteger
1. What is a race condition?
A race condition occurs when multiple threads access and modify the same shared data at the same time, and the final result depends on the timing/order of the threads.

In your example:

static int staticCount = 0;
There is only one staticCount shared by all three threads.

Each thread executes:

staticCount++;
The problem is that:

staticCount++;
looks like one operation, but internally it is approximately:

1. READ  staticCount
2. ADD   1
3. WRITE staticCount
It is therefore not atomic.

2. How can the problem happen?
Suppose:

staticCount = 10
Two threads execute staticCount++ at nearly the same time.

Thread 1
READ → 10
Thread 2
READ → 10
Both threads have now read 10.

Then:

Thread 1: 10 + 1 = 11
Thread 2: 10 + 1 = 11
And both write:

Thread 1 → WRITE 11
Thread 2 → WRITE 11
You expected:

10 → 11 → 12
But you got:

10 → 11
One increment was effectively lost.

This is called a lost update.


 #Race Condition
এটা সবচেয়ে important।
তোমার:
staticCount++;

দেখতে এক লাইন।
কিন্তু ভিতরে:
READ

+

ADD 1

+

WRITE

হয়।
ধরো:
staticCount = 10

দুইটা thread:
Thread 1:
READ 10

Thread 2:
READ 10

এখন দুইজনের কাছে:
10

আছে।
Thread 1:
10+1=11
WRITE 11

Thread 2:
10+1=11
WRITE 11

শেষে:
11

হল।
কিন্তু হওয়া উচিত ছিল:
12

একটা increment হারিয়ে গেল।
এটাই:
Lost update

6. Experiment কেন 1 million?
এখানে:
for(int i=0;i<1000000;i++)
{
    staticCount++;
}

ধরা:
৩টা thread।
Expected:
3 × 1,000,000

=

3,000,000

কিন্তু race condition হলে:
Actual:

1,847,291

বা

2,478,392

হতে পারে।
কারণ increment হারিয়ে যাচ্ছে।
7. তাহলে সমাধান কী?
দুইটা প্রধান solution:
Solution 1: synchronized
Example:
synchronized void increment(){

    staticCount++;

}

এর মানে:
এক সময়ে একটাই thread ঢুকবে।
Diagram:
Thread 1
   |
 LOCK
   |
count++
   |
UNLOCK


Thread 2 waits

Solution 2: AtomicInteger
Java দেয়:
AtomicInteger count =
new AtomicInteger(0);

তারপর:
count.incrementAndGet();

এটা atomic operation।
মানে:
READ
+
ADD
+
WRITE

একসাথে safe ভাবে হয়।
