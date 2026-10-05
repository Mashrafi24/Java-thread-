
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




