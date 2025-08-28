package concurrent1;

public class TestBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> q = new BlockingQueue<>(3);

        Thread enqueue = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    q.enqueue(i);
                    System.out.println("Enqueue: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "enqueue");

        Thread dequeue = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    Integer v = q.dequeue();
                    System.out.println("Dequeue: " + v);
                    Thread.sleep(250);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "dequeue");

        enqueue.start();
        dequeue.start();

        enqueue.join();
        dequeue.join();

        System.out.println("Size = " + q.size());
    }
}
