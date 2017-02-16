# Exercise 1: Handling threads with Jonas

## Task 1
Basicly this is about threads.
- TASK: *Starts three threads that execute three methods simultaneously.*
- TASK: *Stop / kill the thread running the third method after waiting 10 seconds.*
- LEARNED: *So the task learned me that to start threads, if you wanna stop / interrupt them, you need to have the reference to them.*
- LEARNED: *And that place the thread in the highest level possible of your code, so you have the complete sections of code run, but it still seems i don't control it from there*

## Task 2
Basicly this is about calculations with threads
- TASK: *This class should only return even numbers. It will be called from many different threads, so it has to be thread-safe*
- LEARNED: *Both the AtomicLong, and the Reentrantlock solved this issue, with not calculating it by 2 every time the thread was called simultaniesly*

## Task 3 - 5
- Forked and looked on, but skipped to try Task 6 before this.

### Task 3 (Blocking the GUI-thread)
- Looked on, but skipped to try Task 6 before this.

### Exercise 5 (race condition)
- Looked on, but skipped to try Task 6 before this.

## Task 6
Basicly this task is about looping read commands (printing) in simultanious threads
- TASK: Assign alpabet printing to 4 different threads and start them all simultaneously.
- TASK: Before you run explain why you run them, what do you expect will print? Did you see what you expected? 
- LEARNED: I expected the alphabet to be printed fully from a to z before turning to the next alphabet, but didn't work.
- LEARNED: CachedThreadPool is a great tool for making it concurrent.