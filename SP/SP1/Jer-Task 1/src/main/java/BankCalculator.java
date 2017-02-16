/**
 * Created by scheldejonas on 03/02/17.
 */
public class BankCalculator {

    /**
     * <h2>Task 5</h2>
     *
     * https://github.com/scheldejonas/threadExDay1
     *
     * Execute the main code in either ``ex5.BankApp.java`` or ``ex5.BankAppExecutor.java``
     *
     * The program includes a method executeTransactions () which executes a series of transactions
     * on a shared account object and prints true / false depending on the expected outcome.

     * Identify places in the code where it "goes wrong".
     * What should the closing balance be?

     * Experiment: Increase the number of calls of executeTransactions () or the number of
     * threads and observe the effect.
     * Observe that there are two versions of the main() method, one that creates threads in a
     * traditional way, and one using an ``ExecutorService`` (the recommended way).
     *
     * Solve the problem in the Bank program:
     * 1) Using the traditional synchronization
     * 2) Using a java.util.concurrent.locks.ReentrantLock
     */
}
