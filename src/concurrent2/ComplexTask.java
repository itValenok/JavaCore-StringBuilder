package concurrent2;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ComplexTask implements Runnable {
    private final int taskId;
    private final CyclicBarrier barrier;
    private final Random random = new Random();

    public ComplexTask(int taskId, CyclicBarrier barrier) {
        this.taskId = taskId;
        this.barrier = barrier;
    }

    //Жесткая задача
    public int execute() throws InterruptedException {
        int workTime = random.nextInt(1000) + 500;
        Thread.sleep(workTime);
        return taskId;
    }

    @Override
    public void run() {
        try {
            int result = execute();
            System.out.println("Result of task " + taskId + " is " + result);

            barrier.await();

            System.out.printf("[%s] Task-%d passed the barrier!%n",
                    Thread.currentThread().getName(), taskId);
            System.out.println(Thread.currentThread().getName() + " Task" + taskId + " completed!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Task " + taskId + " was interrupted");
        } catch (BrokenBarrierException e) {
            System.out.println("Task " + taskId + " encountered a broken barrier");
        }
    }
}
