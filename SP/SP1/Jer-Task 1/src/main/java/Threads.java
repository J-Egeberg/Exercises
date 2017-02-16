import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <h2>Task 1</h2>
 * <p>
 * Write three methods:
 * <ol>
 * <li>Compute and print the sum of all numbers from 1 to 1 billion.</li>
 * <li>
 * Print the numbers from 1 to 5. Pause for 2 seconds between each print.
 * </li>
 * <li>
 * Print all numbers from 10 and up to {@link Integer#MAX_VALUE}. Pause for 3
 * seconds between each print.
 * </li>
 * </ol>
 * Create three threads that run each of the above methods. Start them all
 * simultaneously from your main method. Stop / kill the thread running the
 * third method after waiting 10 seconds.
 * </p>
 */
public class Threads {

    /**
     * Starts three threads that execute three methods simultaneously.
     * Stop / kill the thread running the third method after waiting 10 seconds.
     *
     * @param args Input arguments to the main method. Unused.
     */
    public static void main(String[] args) {
        Thread threadSayHiAndSleepTenSec;
        Thread threadPrintFromOneToOneBillion;
        Thread threadPrintFromTenToIntMax = null;

        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Runnable runnableSayHiAndSleepTenSec = () -> {
                try {
                    sayHiAndSleepTenSec();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            threadList.add(new Thread(runnableSayHiAndSleepTenSec));

            Runnable runnablePrintFromOneToOneBillion = () -> {
                loopAndPrintComputedNumberFromOneToOneBillion();
            };
            threadList.add(new Thread(runnablePrintFromOneToOneBillion));

            Runnable runnablePrintFromTenToIntMax = () -> {
                try {
                    printAllNumbersFromTenUpToIntegerMax();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            threadList.add(new Thread(runnablePrintFromTenToIntMax));

        }
        threadList.forEach(Thread::start);

        for (Thread thread:threadList
                ) {
            System.out.printf("Nummer: %s \n", thread.getId() );
        }

        try {
            new Thread().sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 2; i < threadList.size(); i+=3) {
            System.out.printf("Interrupted Nummer: %s \n", threadList.get(i).getId() );
            threadList.get(i).interrupt();
        }

    }

    private static void printNumberToScreen(Integer number) {
        System.out.printf("%s \n",number);
    }

    private static void loopAndPrintComputedNumberFromOneToOneBillion() {
        boolean isCounterBelowOneBillion = true;
        Integer counter = 0;
        Integer computedNumber = 0;

        while (isCounterBelowOneBillion) {
            computedNumber = computeNumberWithCounter(computedNumber, counter);
            counter++;
            if (counter > 1000000) {
                isCounterBelowOneBillion = false;
            }
        }
        printNumberToScreen(computedNumber);
    }


    private static Integer computeNumberWithCounter(Integer computedNumber, Integer counter) {
        return computedNumber + counter;
    }

    private static void sayHiAndSleepTenSec() throws InterruptedException {
        System.out.printf("Hi start sleeping \n");
        new Thread().sleep(10*1000);
        System.out.printf("Done sleeping \n");
    }

    /**
     * Print all numbers from 10 and up to {@link Integer#MAX_VALUE}. Pause for 3 seconds between each sprint.
     * Working as a Thread.
     */
    private static void printAllNumbersFromTenUpToIntegerMax() throws InterruptedException {
        for (Integer integer = 10; integer < integer.MAX_VALUE; integer++) {
            printNumberToScreen(integer);
            new Thread().sleep(3*1000);
        }
    }
}
