
# Static vs Non-Static in Java

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
