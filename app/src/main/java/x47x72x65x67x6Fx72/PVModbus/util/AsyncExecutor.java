package x47x72x65x67x6Fx72.PVModbus.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Singleton executor for async operations like networking under android.
 * Uses a variable size of ThreadPool!
 */
public class AsyncExecutor {
    private static AsyncExecutor instance;
    private final ExecutorService executorService;

    private AsyncExecutor() {
        this.executorService = Executors.newCachedThreadPool();
    }

    public static AsyncExecutor getInstance() {
        if (instance == null) {
            instance = new AsyncExecutor();
        }
        return instance;
    }

    /**
     * Executes given task on it's thread-pool
     *
     * @param task that shall be executed
     * @return handle of execution
     */
    public Future<?> execute(Runnable task) {
        return executorService.submit(task);
    }

}
