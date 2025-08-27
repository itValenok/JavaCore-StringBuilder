package concurrent2;

import java.util.concurrent.*;

public class ComplexTaskExecutor {

    private final int capacity;

    public ComplexTaskExecutor(int capacity) {
        this.capacity = capacity;
    }

    public void executeTasks(int numberOfTasks) {
        if (numberOfTasks <= 0 || numberOfTasks > capacity) {
            throw new IllegalArgumentException("Number of tasks must be between 1 and " + (capacity - 1));
        }

        Runnable barrierAction = () -> System.out.println("All tasks have completed!");
        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, barrierAction);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTasks);
        try {
            for (int i = 0; i < numberOfTasks; i++) {
                ComplexTask task = new ComplexTask(i, barrier);
                executorService.submit(task);
            }
        } finally {
            executorService.shutdown();
        }
    }
}
