# Answers to general questions

##1
A thread is a list of instructions. We use it in java to run specific code with specific instructions based on the thread.
Threads can run concurrently in our program and therefore we can execute more tasks at once.

##2
"Race conditions" is a term we use when multiple threads run simultaneously. It only occurs if it matters in which order the specific
instructions within the Thread pauses before another Thread runs. To prevent this we can use the SYNCHRONIZED statement in our method
to make sure that only one Thread goes through our method at a time. If we didnt use Synchronized our Thread Scheduler would just
throw random thread into the method (godmode). We could alså use the Lock/Unlock function, again to make sure only one thread
is in the method at a time

##3
A Thread is reusable. In our Controller class the thread can be used to do instructions. The thread can carry out HTTP requests and responds and be ready for a new http request/response again afterwards

##4
Deadlocking occurs when 2 threads wait for the same thing. This means the Thread DIES forever and the program shuts down. 
Detection of deadlock is performed by a lock monitor thread that periodicly searches through the data for potential deadlocks.
Be careful with the .join() method. Deadlock can be prevented by making sure each thread runs through the whole program without stopping.
to avoid deadlock avoid blocking: f. eks the thread is waiting idly or waiting on contested locks.
