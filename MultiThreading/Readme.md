 #03-Multithreading
  │
  ├── BasicThread.java
  ├── StartVsRun.java
  ├── StaticVsNonStaticThread.java
  ├── RaceCondition.java
  ├── SynchronizedCounter.java
  └── AtomicIntegerCounter.java



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
