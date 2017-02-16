import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <h2>Task 2</h2>
 * <p>
 * This class should only return even numbers. It will be called from many
 * different threads, so it has to be thread-safe. In other words it has to be
 * able to avoid race-conditions if several threads calls it at once.
 * </p>
 * <p>
 * Your task is to implement the {@link #next()} method to return 0 as the
 * first number, 2 as the second, 4 and so on.
 * </p>
 */
public class Even implements Iterator<Long> {

    // The counter containing the number the iterator has reached.
    private AtomicLong counter =  new AtomicLong(-2);

    /**
     * This has 2^64 numbers. That's a lot!
     *
     * @return Always true.
     */
    @Override
    public boolean hasNext() {
        // Don't change this: we assume we always have another number

        return true;
    }

    /**
     * @return The next even number in the iterator.
     */
    @Override
    public synchronized Long next() {
        return counter.addAndGet(2);

    }

    private AtomicLong getCounter() {
        return counter;
    }

    /**
     * Test your solution here by calling {@link #next()} from different threads.
     *
     * @param args Input arguments to the main method. Unused.
     */
    public static void main(String[] args) {
        Even even = new Even();
        Runnable runnable = () -> {
            even.next();
            System.out.printf("Number: %s \n", even.getCounter());
            if ( (even.getCounter().get() % new AtomicLong(2).get()) == (new AtomicLong(1).get()) ) {
                System.out.printf("--------------------------: ERROR at %s \n", even.getCounter());
            }
        };
        Even evenTwo = new Even();
        Runnable runnableTwo = () -> {
            evenTwo.next();
            System.out.printf("Number: %s \n", evenTwo.getCounter());
            if ( (evenTwo.getCounter().get() % new AtomicLong(2).get()) == (new AtomicLong(1).get()) ) {
                System.out.printf("--------------------------: ERROR at %s \n", evenTwo.getCounter());
            }
        };
        Even evenThree = new Even();
        Runnable runnableThree = () -> {
            evenThree.next();
            System.out.printf("Number: %s \n", evenThree.getCounter());
            if ( (evenThree.getCounter().get() % new AtomicLong(2).get()) == (new AtomicLong(1).get()) ) {
                System.out.printf("--------------------------: ERROR at %s \n", evenThree.getCounter());
            }
        };
        Even evenFour = new Even();
        Runnable runnableFour = () -> {
            evenFour.next();
            System.out.printf("Number: %s \n", evenFour.getCounter());
            if ( (evenFour.getCounter().get() % new AtomicLong(2).get()) == (new AtomicLong(1).get()) ) {
                System.out.printf("--------------------------: ERROR at %s \n", evenFour.getCounter());
            }
        };
        List<Thread> threadList = new ArrayList<>();
        boolean isReady = true;
        while (isReady) {
            Thread thread = new Thread( runnable );
            threadList.add( thread );
            Thread threadTwo = new Thread( runnableTwo );
            threadList.add( threadTwo );
            Thread threadThree = new Thread( runnableThree );
            threadList.add( threadThree );
            Thread threadFour = new Thread( runnableFour );
            threadList.add( threadFour );
            if (threadList.size() > 1000000) {
                isReady = false;
            }
        }
        threadList.forEach(Thread::start);
    }

}
