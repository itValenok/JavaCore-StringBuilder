package concurrent1;

public class BlockingQueue<T> {

    private int tail = 0;
    private int head = 0;
    private int size = 0;
    private final Object lock = new Object();
    private final T[] elements;

    public BlockingQueue(int capacity) {
        elements = (T[]) new Object[capacity];
    }

    public void enqueue(T item) {
        synchronized (lock) {
            while (size == elements.length) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            elements[tail] = item;
            tail = (tail + 1) % elements.length;
            size++;
            lock.notifyAll();
        }
    }
    public T dequeue() {
        synchronized (lock) {
            while (size == 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            T item = elements[head];
            head = (head + 1) % elements.length;
            size--;
            lock.notifyAll();
            return item;
        }
    }

    public int size() {
        return this.size;
    }
}
