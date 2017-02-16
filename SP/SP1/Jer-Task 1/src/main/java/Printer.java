import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <h2>Task 6</h2>
 * <p>
 * The {@link Printer} is a {@link Runnable} which simply prints the alphabet.
 * Your task is to implement the {@link #run()} method to print the alphabet
 * (in the {@link #ALPHABET} constant) one character at the time, one
 * line per character. In the {@link #main(String[])} method you should create
 * four {@link Printer}s, assign them to 4 different threads and start them all
 * simultaneously.
 * <br/>
 * Before you run them, what do you expect will print? Did you see what you
 * expected?
 * </p>
 */
public class Printer implements Runnable {

    // The alphabet to print
    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Printer() {
    }

    /**
     * This method should print the alphabet one line at the time. Each line
     * should only contain one letter.
     */
    @Override
    public void run() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        System.out.printf("Locked? - %s \n", lock.isLocked() );
        System.out.printf("STARTING \n");
        for (char character : ALPHABET.toCharArray()
             ) {
            System.out.printf( "%s \n",character );
        }
        System.out.printf("DONE \n");
        lock.unlock();
    }

    /**
     * Creates four threads and start them simultaneously.
     *
     * EXPECTATION: here was that the alphabet, then would be printed in one alphabet at a time, but still didn't seem to work.
     *
     * CONCLUSION: is, it is difficult to insure synchronus threading in your java program, and needs to be focused
     *
     * @param args Input arguments to the main method. Unused.
     */

    public static void main(String[] args) {
        Thread thread = new Thread( () -> {
                Printer printer = new Printer();
                printer.run();
            }
        );
        Thread threadTwo = new Thread( () -> {
                Printer printer = new Printer();
                printer.run();
            }
        );
        Thread threadThree = new Thread( () -> {
                Printer printer = new Printer();
                printer.run();
            }
        );
        Thread threadFour = new Thread( () -> {
                Printer printer = new Printer();
                printer.run();
            }
        );
        List<Thread> threadList = new ArrayList<>();
        threadList.add(thread);
        threadList.add(threadTwo);
        threadList.add(threadThree);
        threadList.add(threadFour);
        System.out.printf("Size of thread list: %s \n", threadList.size() );
        threadList.forEach(Thread::start);
        threadList.forEach((thread1) -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

//    /**
//     * The expectations here was that the alphabet, then would be printed in one alphabet at a time.
//     *
//     * The conclusion is, it is difficult to insure synchronus threading in your java program, and needs to be focused
//     * @param args
//     */
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        Runnable runnable = () -> {
//            Printer printer = new Printer();
//            printer.run();
//        };
//        executorService.execute(runnable);
//        executorService.execute(runnable);
//        executorService.execute(runnable);
//        executorService.execute(runnable);
//    }
}