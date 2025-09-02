package stream3;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {

    private final long start;
    private final long end;

    public FactorialTask(long n) {
        this.start = 1;
        this.end = n;
    }


    public FactorialTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end < start) {
            return 1L;
        }
        if (end == start) {
            return start;
        }

        long mid = (start + end) / 2;
        FactorialTask leftTask = new FactorialTask(start, mid);
        FactorialTask rightTask = new FactorialTask(mid + 1, end);
        leftTask.fork();
        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();
        return leftResult * rightResult;
    }
}
